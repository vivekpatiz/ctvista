import pytest
import json
import mysql.connector

from src.montoringIngestion import MontoringAppIngester

def test_typeerror_exception():
    mi = MontoringAppIngester()
    with pytest.raises(TypeError):
        mi.ingest_data()

def test_name_converter():
    mi = MontoringAppIngester()
    actual_value = mi.name_converter("TBD condensor (inHG)")
    assert actual_value == 'tbd_condensor_inhg'

def test_ingest_data():
    mi = MontoringAppIngester()
    assert mi.ingest_data(path='laksjdflsakjfd', dest='lakdsjfljsdf') == None


def test_db_connection():
    mi = MontoringAppIngester()
    connection = mi.get_databaseConnection()

    assert connection.is_connected() == True
    connection.close()
    assert connection.is_connected() == False

def test_dbconnection_from_config():
    with open("tests/test_config.json", "r") as read_file:
        testconfigdata = json.load(read_file)
    connection = mysql.connector.connect(user=testconfigdata['dataBaseUser'], password=testconfigdata['dataBasePassword'],
                                               host=testconfigdata['dataBaseHost'], database=testconfigdata['dataBaseName'])

    assert connection.is_connected() == True
    connection.close()
    assert connection.is_connected() == False

def test_ingest_data_row_count():
    with open("tests/test_config.json", "r") as read_file:
        testconfigdata = json.load(read_file)
    connection = mysql.connector.connect(user=testconfigdata['dataBaseUser'], password=testconfigdata['dataBasePassword'],
                                               host=testconfigdata['dataBaseHost'], database=testconfigdata['dataBaseName'])
    connection_cursor = connection.cursor(buffered=True)
    query = ("SELECT count(*) FROM " + testconfigdata['dataBaseName'] + ".asset_data;")
    connection_cursor.execute(query)
    for c in connection_cursor:
        assert c[0] >= 0


def test_asset_data_info_insert_exception():
    mi = MontoringAppIngester()
    with pytest.raises(TypeError):
        mi.asset_data_info_insert()

def test_asset_data_info_insert():
    mi = MontoringAppIngester()
    mi.asset_data_info_insert('laksjdfl', 'laksdjfls', 'inprogress')
    assert mi.asset_data_info_insert('laksjdfl','laksdjfls','inprogress') == None

def test_assert_data_info_insert_config():
    with open("tests/test_config.json", "r") as read_file:
         testconfigdata = json.load(read_file)

    mi = MontoringAppIngester()
    assert mi.asset_data_info_insert(testconfigdata['pathOfxlsx'], testconfigdata['destofxlsx'], 'inprogress') == None

def test_ingest_data():
    with open("tests/test_config.json", "r") as read_file:
         testconfigdata = json.load(read_file)
    mi = MontoringAppIngester()
    mi.ingest_data(testconfigdata['pathOfxlsx'], testconfigdata['destofxlsx'])
    assert mi.ingest_data(testconfigdata['pathOfxlsx'], testconfigdata['destofxlsx']) == None

def test_asert_data_inflow_row_count():
    with open("tests/test_config.json", "r") as read_file:
        testconfigdata = json.load(read_file)
    connection = mysql.connector.connect(user=testconfigdata['dataBaseUser'], password=testconfigdata['dataBasePassword'],
                                               host=testconfigdata['dataBaseHost'], database=testconfigdata['dataBaseName'])
    connection_cursor = connection.cursor(buffered=True)
    query = ("SELECT count(*) FROM " + testconfigdata['dataBaseName'] + ".asset_data_inflow_log;")
    connection_cursor.execute(query)
    for c in connection_cursor:
        assert c[0] >= 0

