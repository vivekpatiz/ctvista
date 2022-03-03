import logging
import json
from src.montoringIngestion import MontoringAppIngester

logging.basicConfig(filename='logs/application.log', level=logging.DEBUG, format='%(asctime)s %(levelname)s %(name)s %(message)s')
logger = logging.getLogger(__name__)

# r'C:\Users\akanksha.shrivastava\Documents\python.xlsx'
# r'C:\Users\akanksha.shrivastava\Documents\dest_folder/python.xlsx'
def main():
    try:
        with open("config/config.json", "r") as read_file:
            data = json.load(read_file)
            print("data", data['pathOfxlsx'])
        # add_asset_data(path)
        mi = MontoringAppIngester()
        mi.ingest_data(data['pathOfxlsx'],
                       data['destofxlsx'])

    except Exception as ex:
        logger.error(ex)

# if __name__ == "__main__":
main()
