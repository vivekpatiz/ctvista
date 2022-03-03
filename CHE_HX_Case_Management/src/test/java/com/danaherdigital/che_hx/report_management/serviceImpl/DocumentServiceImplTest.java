package com.danaherdigital.che_hx.report_management.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.report_management.repository.DashboardRepository;
import com.danaherdigital.che_hx.report_management.service.AWSS3Service;
import com.danaherdigital.che_hx.report_management.serviceimpl.DocumentServiceImpl;
import com.danaherdigital.che_hx.report_management.utils.TestUtils;

@PrepareForTest({ DocumentServiceImpl.class, WorkbookFactory.class })
public class DocumentServiceImplTest {

	@InjectMocks
	DocumentServiceImpl documentServiceImpl;

	@Mock
	DashboardRepository dashboardRepository;
	@Mock
	AWSS3Service aWSS3Service;
	@Mock
	Workbook workbook;
	@Mock
	Sheet sheet;
	@Mock
	Row row;
	@Mock
	Cell cell;
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	@Rule
	public PowerMockRule rule = new PowerMockRule();
	static {
		PowerMockAgent.initializeIfNeeded();
	}

	@Before
	public void setup() throws Exception {
		final File mockFile = mock(File.class);
		Mockito.doReturn(true).when(mockFile).exists();

		PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(mockFile);
		final FileInputStream fileInputStreamMock = PowerMockito.mock(FileInputStream.class);
		PowerMockito.whenNew(FileInputStream.class).withAnyArguments().thenReturn(fileInputStreamMock);

		final FileOutputStream fileOutputStreamMock = PowerMockito.mock(FileOutputStream.class);
		PowerMockito.whenNew(FileOutputStream.class).withAnyArguments().thenReturn(fileOutputStreamMock);
		PowerMockito.mockStatic(WorkbookFactory.class);

		Sheet mockSheet = mock(Sheet.class);
		Row mockRow = mock(Row.class);
		Cell mockCell = mock(Cell.class);
		CellStyle mockCellStyle = mock(CellStyle.class);

		when(mockSheet.createRow(0)).thenReturn(mockRow);
		when(mockSheet.createRow(ArgumentMatchers.anyInt())).thenReturn(mockRow);
		when(mockRow.createCell(ArgumentMatchers.anyInt())).thenReturn(mockCell);

		when(WorkbookFactory.create(fileInputStreamMock)).thenReturn(workbook);
		when(workbook.getSheetAt(ArgumentMatchers.anyInt())).thenReturn(sheet);
		when(sheet.createRow(ArgumentMatchers.anyInt())).thenReturn(row);
		when(sheet.getRow(ArgumentMatchers.anyInt())).thenReturn(row);

		when(workbook.createCellStyle()).thenReturn(mockCellStyle);
		when(row.createCell(ArgumentMatchers.anyInt())).thenReturn(cell);

	}

	@Test
	public void getgetS3DocumentSuccessTest() throws ChemtreatException, IOException {
		final File file1 = folder.newFile("myfile1.txt");

		when(dashboardRepository.findByNameandType((Mockito.any()))).thenReturn(new String("test.pdf"));
		when(aWSS3Service.downloadFile(ArgumentMatchers.anyString())).thenReturn(file1);
		File file = documentServiceImpl.getS3Document("test.pdf");
		assertNotEquals(null, file);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getgetS3DocumentExceptionTest() throws ChemtreatException, IOException {
		File file = documentServiceImpl.getS3Document(null);
		assertEquals(null, file);
	}

	@Test
	public void getExcelDocumentTemplateHistorianTypeSuccessTest() throws IOException {
		when(row.createCell(ArgumentMatchers.anyInt())).thenReturn(cell);

		File file = documentServiceImpl.getExcelDocumentTemplate(TestUtils.createMockJsonData(), "historian");
		assertNotEquals(null, file);

	}

	@Test
	public void getExcelDocumentTemplateMappingTypeSuccessTest() throws Exception {

		File file1 = documentServiceImpl.getExcelDocumentTemplate(TestUtils.createMockJsonData(), "mapping");
		assertNotEquals(null, file1);
	}

}
