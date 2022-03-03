package com.danaherdigital.che_hx.uom.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.uom.dto.UOMRespDTO;
import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.uom.repository.UnitOfMeasureRepository;
import com.danaherdigital.che_hx.uom.serviceimpl.UnitOfMeasureServiceImpl;
import com.danaherdigital.che_hx.uom.utils.UOMTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class UnitOfMeasureServiceImplTest {

	@InjectMocks
	UnitOfMeasureServiceImpl unitOfMeasureServiceImpl;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Test
	public void SuccessTest() throws ChemtreatException {
		UOMTestUtils.getUnitOfMeasure();
		UOMRespDTO resp = new UOMRespDTO();
		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate1());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate2());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate3());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate4());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate5());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate6());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate7());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate8());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate9());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate10());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate11());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate12());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate13());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate14());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate15());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate16());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate17());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate18());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate19());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate20());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate21());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate22());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate23());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate24());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate25());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate26());
		assertNotEquals(0, resp.getList().size());

		resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getReqTemplate27());
		assertNotEquals(0, resp.getList().size());

		when(unitOfMeasureRepository.getAllUOM()).thenReturn(Collections.emptyList());
		List<UOMdataDTO> list = unitOfMeasureServiceImpl.getUOMList();

	}

	@Test(expected = ChemtreatException.class)
	public void ExceptionTest() throws ChemtreatException {

		UOMRespDTO resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getExpReqTemplate());
		assertEquals(null, resp);

	}

	@Test(expected = ChemtreatException.class)
	public void ExceptionTest2() throws ChemtreatException {

		UOMRespDTO resp = unitOfMeasureServiceImpl.getUOMConversionData(UOMTestUtils.getExp2ReqTemplate());
		assertEquals(null, resp);

	}

}
