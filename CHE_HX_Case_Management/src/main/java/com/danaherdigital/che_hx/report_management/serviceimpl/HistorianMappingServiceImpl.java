package com.danaherdigital.che_hx.report_management.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.report_management.dto.TagMappingDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.service.HistorianMappingService;
import com.danaherdigital.che_hx.report_management.utils.JSONConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class HistorianMappingServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j
public class HistorianMappingServiceImpl implements HistorianMappingService {

	/**
	 * Convert excel to json string.
	 *
	 * @param file the file
	 * @return the string
	 * @throws ChemtreatException
	 */
	@Override
	public String convertExcelToJsonString(InputStream file) throws ChemtreatException {
		// Step 1: Read Excel File into Java List Objects
		List<TagMappingDTO> tagMappingList = null;
		// Step 2: Convert Java Objects to JSON String
		String jsonString = null;
			tagMappingList = readExcelFile(file);

			jsonString =JSONConverter.asJsonString(tagMappingList);



		return jsonString;
	}

	/**
	 * Read Excel File into Java List Objects.
	 *
	 * @param file the file
	 * @return the list
	 * @throws ChemtreatException
	 */
	private static List<TagMappingDTO> readExcelFile(InputStream file) throws ChemtreatException {
		try {
			Workbook workbook = new XSSFWorkbook(file);

			Sheet sheet = workbook.getSheet(workbook.getSheetName(1));
			Iterator<Row> rows = sheet.iterator();

			List<TagMappingDTO> lstTagMapping = new ArrayList<>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				TagMappingDTO tagMapping = new TagMappingDTO();

				int cellIndex = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					if (cellIndex == 0) { // tagName
						tagMapping.setCtVistaTagName(currentCell.getStringCellValue());
					} else if (cellIndex == 1) { // UOM
						tagMapping.setHistorianTagUnitOfMeasure(currentCell.getStringCellValue());
					} else if (cellIndex == 2) { // historianTagName
						tagMapping.setHistorianTagName(currentCell.getStringCellValue());
					} else if (cellIndex == 3) { // discriptor
						tagMapping.setHistorianDiscriptor(currentCell.getStringCellValue());
					} else if (cellIndex == 4) { // technical name
						tagMapping.setTechnicalName(currentCell.getStringCellValue());
					}

					cellIndex++;
				}

				lstTagMapping.add(tagMapping);
			}

			// Close WorkBook
			workbook.close();

			return lstTagMapping;
		} catch (IOException e) {
			throw new ChemtreatException("FAIL! -> message = " + e.getMessage());
		}
	}



}
