#!/usr/bin/python3
#-*-coding:utf-8-*-
import os
import logging
import json
import time
import argparse
import sys
import pandas as pd
import mysql.connector
from time import strftime
import datetime
from calendar import timegm
import shutil
import sys
import subprocess

#from montoringIngestion import MontoringAppIngester

current_dir = os.path.dirname(os.path.abspath(__file__))
parent_dir = current_dir+"/../"
log_dir = parent_dir+"/logs/"
conf_dir = parent_dir+"/config/"
dest_dir = parent_dir+"/processed/"

logging.basicConfig(filename=log_dir+'/application.log', level=logging.DEBUG, format='%(asctime)s %(levelname)s %(name)s %(message)s')
logger = logging.getLogger(__name__)

class MontoringAppIngester:
        infoId = 0
        start = time.time()

        with open(conf_dir+"/config.json", "r") as read_file:
            data = json.load(read_file)
        logging.basicConfig(filename=data['logFileName'], level=logging.DEBUG, format='%(asctime)s %(levelname)s %(name)s %(message)s')
        logger = logging.getLogger(__name__)

        def ingest_data(self, path, dest):
            try:
                self.asset_data_info_insert(path, dest, 'inprogress', 0)
                logging.info('json  ' + self.data['dataBaseUser'] + "," + self.data['dataBasePassword'] + "," + self.data['dataBaseName']
                             + "," + self.data['dataBaseHost'] + "," + self.data['logFileName'] + "," + self.data['systemMetadata'])
                df_asset_data = pd.read_excel(path, sheet_name=self.data['dataSheetName'], skiprows=7)
                df_data_info = pd.read_excel(path, sheet_name=self.data['systemMetadata'], header=None)
                print("after reading exls %f" % (time.time() - self.start))
                # print(df_asset_data)
                # connection for database
                cncx = self.get_databaseConnection()
                cursor = cncx.cursor(buffered=True)
                cursor2 = cncx.cursor(buffered=True)
                print("after curser %f" % (time.time() - self.start))
                # select query for asset param
                assetPdata = (df_data_info[1][1])
                query = ("SELECT id, display_name, asset_id, name FROM " + self.data['dataBaseName'] + ".dhrd_asset_params WHERE asset_id='"+ df_data_info[1][1]+"'")
                cursor.execute(query)
                print("after dhrd_asset_params query %f" % (time.time() - self.start))
                duplicate_cursor = []
                for c in cursor:
                    cursorObj = {}
                    cursorObj['id'] = c[0]
                    cursorObj['display_name'] = c[1]
                    cursorObj['asset_id'] = c[2]
                    cursorObj['name'] = c[3]
                    duplicate_cursor.append(cursorObj)

                count_Of_Data = 0
                # df_asset_data loop on coloumns ignor 1
                for v in range(1, len(df_asset_data.columns)):
                    for key in duplicate_cursor:
                        if df_asset_data.columns[v] == key['name'] and df_data_info[1][1] == key['asset_id']:
                            # match
                            logging.info("matched")
                            value_to_insert = df_asset_data[key['name'].strip()]
                            date_to_insert = df_asset_data['CTVista+ Tag name']

                            for i in range(1, len(value_to_insert)):
                                object_to_insert = {}
                                if type(value_to_insert[i]) != str and str(value_to_insert[i]) != 'nan':
                                    object_to_insert['float_values'] = float(value_to_insert[i])
                                else:
                                    object_to_insert['float_values'] = 0
                                object_to_insert['timestamp'] = str(date_to_insert[i])
                                object_to_insert['string_values'] = None
                                object_to_insert['asset_params_name'] = key['id']
                                object_to_insert['asset_name'] = key['asset_id']
                                # read from 2nd seet
                                object_to_insert['tenant_id'] = df_data_info[0][1]
                                object_to_insert['created_by'] = 'DHRD_ADMIN'
                                object_to_insert['updated_by'] = 'DHRD_ADMIN'
                                object_to_insert['tag_name'] = 'Condensor-1.'+key['name']
                                object_to_insert['updated_at'] = str(strftime("%Y-%m-%d %H:%M:%S"))


                                round_the_excel_value = round(object_to_insert['float_values'],5)
                                time_stemp_list =object_to_insert['timestamp'].split(".", 1)
                                utc_time = time.strptime(time_stemp_list[0], "%Y-%m-%d %H:%M:%S")
                                epoch_value = timegm(utc_time)
                                #print(object_to_insert['timestamp'])

                                object_to_insert['timestamp'] = epoch_value
                                try:
                                    query3 = ("replace INTO "  + self.data['dataBaseName'] + ".dhrd_asset_data (id, data_timestamp,"
                                          "float_values,string_values,asset_params_name,asset_name,tenant_id,created_by,"
                                           "updated_by,tag_name,updated_at) "
                                            "VALUES (uuid(),%(timestamp)s,%(float_values)s,%(string_values)s,"
                                            "%(asset_params_name)s,%(asset_name)s,%(tenant_id)s,%(created_by)s,"
                                             "%(updated_by)s,%(tag_name)s,%(updated_at)s)")
                                    cursor2.execute(query3, object_to_insert)
                                    cncx.commit()
                                    logging.info("query executed %f" % (time.time() - self.start))
                                    print(time.time() - self.start)
                                    count_Of_Data = count_Of_Data + 1
                                except Exception as e:
                                    self.logger.error(e)
                                    self.logger.error('Error on line {}%s', sys.exc_info()[2].tb_lineno)
                    print("after for loop 2 end %f" % (time.time() - self.start))
                    logging.info("after for loop 2 end %f" % (time.time() - self.start));
                cursor2.close()
                cursor.close()
                cncx.close()
                self.asset_data_info_insert(path, dest, 'done', count_Of_Data)
                logging.info(count_Of_Data)
                print("after for loop 1 end %f" % (time.time()))
            except Exception as ex:
                self.logger.error(ex)
                self.logger.error('Error on line {}%s', sys.exc_info()[2].tb_lineno)
                self.asset_data_info_insert(path, dest, 'failed', count_Of_Data)

        def asset_data_info_insert(self, path, dest, status, count_Of_Data):
            try:

             if(os.path.exists(path)):
                df_data_info = pd.read_excel(path, sheet_name=self.data['systemMetadata'], header=None)
                df_start_end_date = pd.read_excel(path, sheet_name=self.data['dataSheetName'],nrows=2,header=None)

                # print(df_data_info)
                cncx = self.get_databaseConnection()
                cursor = cncx.cursor(buffered=True)
                object_to_insert = {}
                object_to_insert['asset_name'] = str(df_data_info[1][1])
                start_date_list = str(df_start_end_date[1][0]).split(".", 1)
                end_date_list = str(df_start_end_date[1][1]).split(".", 1)
                object_to_insert['start_date'] = str(datetime.datetime.strptime(start_date_list[0], '%Y-%m-%d %H:%M:%S'))
                object_to_insert['end_date'] = str(datetime.datetime.strptime(end_date_list[0], '%Y-%m-%d %H:%M:%S'))
                object_to_insert['file_name'] = str(df_data_info[1][1])
                object_to_insert['tenant_id'] = df_data_info[0][1]
                object_to_insert['timestamp'] = str(strftime("%Y-%m-%d %H:%M:%S"))
                object_to_insert['workflow_status'] = status
                object_to_insert['count_Of_Data'] = count_Of_Data
                if(status == 'inprogress'):
                    print("inprogress")
                    logging.info('inprogress')
                    query = ("insert into " + self.data['dataBaseName'] + ".dhrd_asset_data_inflow_log (asset_name, timestamp, start_date,"
                         "end_date, file_name, tenant_id, workflow_status, row_count)"
                         " values ( %(asset_name)s, %(timestamp)s, %(start_date)s, %(end_date)s, %(file_name)s, %(tenant_id)s,"
                         "%(workflow_status)s, %(count_Of_Data)s)")
                    logging.info('update:%s', query)
                    cursor.execute(query, object_to_insert)
                else:
                    # global infoId
                    print(status)
                    logging.info(self.infoId)
                    # query = ("UPDATE " + self.data['dataBaseName'] + ".asset_data_inflow_log SET workflow_status='" +
                    #          status + "' and recordCount='" +
                    #          count_Of_Data + "' WHERE id=" + str(self.infoId))
                    data = (status, count_Of_Data, str(strftime("%Y-%m-%d %H:%M:%S")), self.infoId)
                    query = ("UPDATE " + self.data[
                        'dataBaseName'] + ".dhrd_asset_data_inflow_log SET workflow_status=%s, row_count=%s, ingestion_end_time=%s WHERE id=%s")
                    logging.info('update:%s', query)
                    cursor.execute(query, data)
                    if status == 'done':
                        os.makedirs(os.path.dirname(dest), exist_ok=True)
                        shutil.move(path, dest)


                logging.info("curser %s", cursor.lastrowid)
                self.infoId = cursor.lastrowid
                print(self.infoId)
                cncx.commit()

                cursor.close()
                # cursor1.close()
                cncx.close()
                self.logger.debug('file have moved from '+path + 'to ' + dest)
             else:
                 logging.error("ex")

            except Exception as ex:
                self.logger.error(ex)
                self.logger.error('Error on line {}%s', sys.exc_info()[2].tb_lineno)
                #logger.error('Error in on line {}'.format(sys.exc_info()[-1].tb_lineno), type(ex).__name__, ex)

        def name_converter(self, item):
            return item.lower().strip().replace(' ', '_').replace('#', '').replace('(', '').replace(')', '').replace('/', '_')

        def get_databaseConnection(self):
            return mysql.connector.connect(user=self.data['dataBaseUser'], password=self.data['dataBasePassword'],
                                               host=self.data['dataBaseHost'], database=self.data['dataBaseName']);

#parameters
parser = argparse.ArgumentParser(formatter_class=argparse.RawTextHelpFormatter)
parser.add_argument('-f','-file', required=True, help='Please appoint data source path.')

if __name__ == '__main__':
    args = parser.parse_args()
    logger.info('**********  START PARSING HeatExchange Prod V2 **********')
    logtime = time.strftime('%Y-%m-%d %H:%M:%S %p', time.localtime())
    logger.info('Start at: '+logtime)
    isExists = os.path.exists(args.f)
    if isExists:
        try:
            with open(conf_dir+"/config.json", "r") as read_file:
                data = json.load(read_file)

            srcFile = args.f
            destFile = dest_dir+os.path.basename(srcFile)
            mi = MontoringAppIngester()
            mi.ingest_data(srcFile, destFile)
        except Exception as ex:
            logger.error(ex)
    else:
        logger.error('File '+args.f+' doesn\'t exist.')

    logger.info('**********   END PARSING HeatExchange Prod V2 **********')
    logtime = time.strftime('%Y-%m-%d %H:%M:%S %p', time.localtime())
    logger.info('End at: '+logtime)

    subprocess.call("/opt/controllers/HeatExchangeV2Prod/src/copylog.sh")
