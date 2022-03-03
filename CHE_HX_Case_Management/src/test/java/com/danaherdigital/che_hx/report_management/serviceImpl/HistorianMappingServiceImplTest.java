package com.danaherdigital.che_hx.report_management.serviceImpl;

import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.serviceimpl.HistorianMappingServiceImpl;
import com.danaherdigital.che_hx.report_management.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class HistorianMappingServiceImplTest {

	@InjectMocks
	HistorianMappingServiceImpl historianMappingServiceImpl;

	@Test
	public void convertExcelToJsonStringSuccessTest() throws FileNotFoundException, ChemtreatException
	{		//ClassLoader classLoader = getClass().getClassLoader();
		File interfaceDirectory = new File("src/test/java/testExcel.xlsx");

		InputStream  fi2 = new FileInputStream(interfaceDirectory);
		String s=historianMappingServiceImpl.convertExcelToJsonString(fi2);
		assertNotEquals(s,TestUtils.mockJsonString());
	}

	@Test(expected = NullPointerException.class)
	public void convertExcelToJsonStringExceptionTest() throws ChemtreatException
	{
		String s=historianMappingServiceImpl.convertExcelToJsonString(null);
		Assert.assertEquals(null,s);
	}

}
