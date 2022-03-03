package com.danaherdigital.che_hx.report_management.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.report_management.dto.HistorianMappingExcelDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.report_management.repository.DashboardRepository;
import com.danaherdigital.che_hx.report_management.service.AWSS3Service;
import com.danaherdigital.che_hx.report_management.service.DocumentService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.report_management.utils.CommonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	AWSS3Service aWSS3Service;

	@Autowired
	DashboardRepository dashboardRepository;
	

	@Value("${file.mapping.excel.path}")
	private String fileMappingExcelPath;

	@Value("${file.historian.excel.path}")
	private String fileHistorianExcelPath;

	@Override
	public File getExcelDocumentTemplate(String data, String type) throws IOException {
		File file = null;
		log.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getExcelDocumentTemplate");
		log.info("getExcelDocumentTemplate started");
		ObjectMapper objectMapper = new ObjectMapper();
		if (type != null && type.equalsIgnoreCase(ApplicationConstants.MAPPING)) {
			File mappingExcelFile = new File(fileMappingExcelPath);
			Path p1 = Paths.get(ApplicationConstants.FILE_OUTPUT_STREAM); 

			if (mappingExcelFile.exists()) {
				FileInputStream inputStream;

				if (!Files.deleteIfExists(p1)) {

					log.info("file not deleted");
				}
				try {

					JsonNode node = objectMapper.readTree(data);
					ObjectReader reader = objectMapper.readerFor(new TypeReference<List<HistorianMappingExcelDTO>>() {
					});
					// use it
					List<HistorianMappingExcelDTO> mappingList = reader.readValue(node.get(ApplicationConstants.MAPPING));

					List<HistorianMappingExcelDTO> mappingListSorted = attributeSorting(mappingList);

					inputStream = new FileInputStream(mappingExcelFile);
					try(Workbook workbook = WorkbookFactory.create(inputStream))
					{
					Sheet sheet = workbook.getSheetAt(1);
					int rowCount = 1;

					for (HistorianMappingExcelDTO histoList : mappingListSorted) {
						Row row = sheet.createRow(rowCount++);
						int columnCount = 0;

						CellStyle unlockedCellStyle = workbook.createCellStyle();
						// code to get the style for the cell goes here
						unlockedCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
						unlockedCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						unlockedCellStyle.setBorderBottom(BorderStyle.THIN);  
						unlockedCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setBorderLeft(BorderStyle.THIN);  
						unlockedCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setBorderTop(BorderStyle.THIN);  
						unlockedCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setBorderRight(BorderStyle.THIN); 
						unlockedCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setLocked(false); // true or false based on the cell.

						Cell cell = row.createCell(columnCount++);

						cell.setCellValue(histoList.getKey());
						cell.setCellStyle(unlockedCellStyle);

						unlockedCellStyle.setLocked(true);
						row.createCell(columnCount++).setCellValue(histoList.getUom());
						row.createCell(columnCount++).setCellValue(histoList.getTagName());
						row.createCell(columnCount++).setCellValue(histoList.getTagDescriptor());

					}
					inputStream.close();
					try(FileOutputStream outputStream = new FileOutputStream(ApplicationConstants.FILE_OUTPUT_STREAM))
							{
						workbook.write(outputStream);
							}
					}
					file = new File(ApplicationConstants.FILE_OUTPUT_STREAM);

				} catch (EncryptedDocumentException | IOException e) {
					log.error("error in DocumentServiceImpl.getExcelDocumentTemplate ->{}",e);
				}


			}
		} else if (type != null && type.equalsIgnoreCase("historian")) {

			File historianExcelFile = new File(fileHistorianExcelPath);
			Path p2 = Paths.get(ApplicationConstants.FILE_OUTPUT_STREAM); 
			if (historianExcelFile.exists()) {
				FileInputStream inputStream;

				if (!Files.deleteIfExists(p2)) {

					log.info("file not deleted");
				}
				try {

					JsonNode nodeHistorian = objectMapper.readTree(data);
					ObjectReader readerHistorian = objectMapper.readerFor(new TypeReference<List<HistorianMappingExcelDTO>>() {
					});
					// use it
					List<HistorianMappingExcelDTO> mappingList = readerHistorian.readValue(nodeHistorian.get(ApplicationConstants.MAPPING));
					List<HistorianMappingExcelDTO> mappingListSorted = attributeSorting(mappingList);

					inputStream = new FileInputStream(historianExcelFile);
					try(Workbook workbook = WorkbookFactory.create(inputStream))
					{
					Sheet sheet = workbook.getSheetAt(1);
					Sheet sheet3 = workbook.getSheetAt(2);
					Sheet sheet4 = workbook.getSheetAt(3);

					int columnCount = 1;
					for (HistorianMappingExcelDTO histoList : mappingListSorted) {
						Row row = sheet.getRow(4);

						CellStyle unlockedCellStyle = workbook.createCellStyle();
						// code to get the style for the cell goes here
						unlockedCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
						unlockedCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						unlockedCellStyle.setLocked(false);
						unlockedCellStyle.setBorderBottom(BorderStyle.THIN);  
						unlockedCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setBorderLeft(BorderStyle.THIN);  
						unlockedCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setBorderTop(BorderStyle.THIN);  
						unlockedCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); 
						unlockedCellStyle.setBorderRight(BorderStyle.THIN); 
						unlockedCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex()); 
						// true or false based on the cell.

						Cell cell = row.createCell(columnCount);
						cell.setCellValue(histoList.getTagName());
						cell.setCellStyle(unlockedCellStyle);

						Row historianTagDesRow = sheet.getRow(5);
						Cell cell2 = historianTagDesRow.createCell(columnCount);
						cell2.setCellValue(histoList.getTagDescriptor());
						cell2.setCellStyle(unlockedCellStyle);

						Row uomRow = sheet.getRow(6);
						Cell cell3 = uomRow.createCell(columnCount);
						cell3.setCellValue(histoList.getUom());
						cell3.setCellStyle(unlockedCellStyle);

						Row keyRow = sheet.getRow(7);
						Cell cell4 = keyRow.createCell(columnCount);
						cell4.setCellValue(histoList.getKey());
						cell4.setCellStyle(unlockedCellStyle);

						// sheet 3 pasting start

						Row sheet3Row = sheet3.getRow(4);

						Cell sheet3Cell = sheet3Row.createCell(columnCount);
						sheet3Cell.setCellValue(histoList.getTagName());
						sheet3Cell.setCellStyle(unlockedCellStyle);

						Row historianTagDesRowSheet3 = sheet3.getRow(5);
						Cell cell2sheet3 = historianTagDesRowSheet3.createCell(columnCount);
						cell2sheet3.setCellValue(histoList.getTagDescriptor());
						cell2sheet3.setCellStyle(unlockedCellStyle);

						Row uomRowSheet3 = sheet3.getRow(6);
						Cell cell3Sheet3 = uomRowSheet3.createCell(columnCount);
						cell3Sheet3.setCellValue(histoList.getUom());
						cell3Sheet3.setCellStyle(unlockedCellStyle);

						Row keyRowSheet3 = sheet3.getRow(7);
						Cell cell4Sheet3 = keyRowSheet3.createCell(columnCount);
						cell4Sheet3.setCellValue(histoList.getKey());
						cell4Sheet3.setCellStyle(unlockedCellStyle);

						columnCount++;

					}

					Row rowSheet4 = sheet4.getRow(1);

					CellStyle unlockedCellStyle = workbook.createCellStyle();
					// code to get the style for the cell goes here
					unlockedCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
					unlockedCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					unlockedCellStyle.setLocked(false); // true or false based on the cell.

					Cell cell = rowSheet4.createCell(0);
					cell.setCellValue(nodeHistorian.has("tenantId")?nodeHistorian.get("tenantId").asText():"");
					cell.setCellStyle(unlockedCellStyle);

					Cell cell1 = rowSheet4.createCell(1);
					cell1.setCellValue(nodeHistorian.has("assetId")?nodeHistorian.get("assetId").asText():"");
					cell1.setCellStyle(unlockedCellStyle);

					Cell cell2 = rowSheet4.createCell(2);
					cell2.setCellValue(nodeHistorian.has("assetName")?nodeHistorian.get("assetName").asText():"");
					cell2.setCellStyle(unlockedCellStyle);

					inputStream.close();
					try(FileOutputStream outputStream = new FileOutputStream(ApplicationConstants.FILE_OUTPUT_STREAM))
					{
					workbook.write(outputStream);
					}
					}
					file = new File(ApplicationConstants.FILE_OUTPUT_STREAM);

				} catch (EncryptedDocumentException | IOException e) {
					log.error("error in DocumentServiceImpl.getExcelDocumentTemplate ->{}",e);
				}


			}

		}
		return file;
	}

	private List<HistorianMappingExcelDTO> attributeSorting(List<HistorianMappingExcelDTO> mappingList) {
		List<HistorianMappingExcelDTO> sortedList = new ArrayList<>();

		for (String attr : CommonUtils.getOrderAttr()) {
			HistorianMappingExcelDTO historianMappingExceldto = mappingList.stream()
					.filter(attrMap -> attrMap.getKey().equals(attr)).findFirst().orElse(null);
			if (historianMappingExceldto != null) {
				sortedList.add(historianMappingExceldto);
			}

		}


		return sortedList;
	}

	@Override
	public File getS3Document(String docName) {
		if(Objects.isNull(docName))
		{
			log.error("file not found");
			throw new ResourceNotFoundException("File Not Found");

		}
		try {
			return aWSS3Service.downloadFile(docName);
		} catch (Exception e) {
			log.error("error while downloading a file->{}",e);
		}
		return null;
	}

}
