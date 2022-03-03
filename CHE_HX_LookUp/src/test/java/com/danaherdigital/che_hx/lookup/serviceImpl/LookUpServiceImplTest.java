package com.danaherdigital.che_hx.lookup.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.lookup.dto.ResponseDTO;
import com.danaherdigital.che_hx.lookup.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.lookup.model.HEIStdCFactor;
import com.danaherdigital.che_hx.lookup.model.HEIStdCoefficients;
import com.danaherdigital.che_hx.lookup.model.LookUpFuel;
import com.danaherdigital.che_hx.lookup.model.LookUpUnit;
import com.danaherdigital.che_hx.lookup.model.Steam;
import com.danaherdigital.che_hx.lookup.model.TubeData;
import com.danaherdigital.che_hx.lookup.model.WaterCFactor;
import com.danaherdigital.che_hx.lookup.repo.HEIStdCFactorRepository;
import com.danaherdigital.che_hx.lookup.repo.HEIStdCoefficientsRepository;
import com.danaherdigital.che_hx.lookup.repo.LookUpFuelRepository;
import com.danaherdigital.che_hx.lookup.repo.LookUpUnitRepository;
import com.danaherdigital.che_hx.lookup.repo.SteamRepository;
import com.danaherdigital.che_hx.lookup.repo.TubeDataRepository;
import com.danaherdigital.che_hx.lookup.repo.WaterCFactorRepository;
import com.danaherdigital.che_hx.lookup.utils.UOMTestUtils;



;

@RunWith(MockitoJUnitRunner.class)
public class LookUpServiceImplTest {

	@InjectMocks
	com.danaherdigital.che_hx.lookup.serviceimpl.LookUpServiceImpl LookUpServiceImpl;

	@Mock
	LookUpUnitRepository lookUpUnitRepository;

	@Mock
	LookUpFuelRepository lookUpFuelRepository;

	@Mock
	SteamRepository steamRepository;

	@Mock
	TubeDataRepository tubeDataRepository;

	@Mock
	HEIStdCFactorRepository heiStdCFactorRepository;

	@Mock
	HEIStdCoefficientsRepository heiStdCoefficientsRepository;

	@Mock
	WaterCFactorRepository waterCFactorRepository;

	@Test
	public void lookUpsuccessTest() throws ChemtreatException {
		LookUpUnit unit = new LookUpUnit();
		LookUpFuel fuel = new LookUpFuel();
		when(lookUpUnitRepository.findByTypeOfUnit(Mockito.any())).thenReturn(unit);
		when(lookUpFuelRepository.findByFuel(Mockito.any())).thenReturn(fuel);
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.getLookUpTables());
		assertNotEquals(0, resp.size());

	}

	@Test
	public void steamSuccessTest() throws ChemtreatException {
		Steam steam = new Steam();
		Optional<Steam> st = Optional.of(steam);
		when(steamRepository.findByTemp(Mockito.any())).thenReturn(steam);
		when(steamRepository.findByPressure(Mockito.any())).thenReturn(steam);
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.getSteamTable());
		assertNotEquals(0, resp.size());

	}

	@Test
	public void tubeDataSuccessTest() throws ChemtreatException {
		TubeData tube = new TubeData();
		when(tubeDataRepository.findByBwg(Mockito.anyInt())).thenReturn(tube);
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.getTubeDataTable());
		assertNotEquals(0, resp.size());

	}

	@Test
	public void stdFactorSuccessTest() throws ChemtreatException {
		HEIStdCFactor stdFactor = new HEIStdCFactor();
		HEIStdCoefficients stdCoeff = new HEIStdCoefficients();
		when(heiStdCoefficientsRepository.findByCoeff(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(stdCoeff);
		when(heiStdCFactorRepository.findByCFactor(Mockito.anyInt(), Mockito.anyString())).thenReturn(stdFactor);
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.getHEITable());
		assertNotEquals(0, resp.size());

	}

	@Test
	public void wcFactorSuccessTest() throws ChemtreatException {
		WaterCFactor wcf = new WaterCFactor();
		Optional<WaterCFactor> wcFact = Optional.of(wcf);
		when(waterCFactorRepository.findByTemp(Mockito.anyInt())).thenReturn(wcf);
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.getWcfTable());
		assertNotEquals(0, resp.size());

	}

	@Test
	public void InvalidTests() throws ChemtreatException {
		WaterCFactor wcf = new WaterCFactor();
		Optional<WaterCFactor> wcFact = Optional.of(wcf);
		when(waterCFactorRepository.findByTemp(Mockito.anyInt())).thenReturn(wcf);
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.getWcfTableInvalidFrom());
		assertNotEquals(0, resp.size());

		when(waterCFactorRepository.findByTemp(Mockito.anyInt())).thenReturn(null);
		List<ResponseDTO> resp1 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getWcfTable());
		assertNotEquals(0, resp1.size());

		when(heiStdCoefficientsRepository.findByCoeff(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(null);
		when(heiStdCFactorRepository.findByCFactor(Mockito.anyInt(), Mockito.anyString())).thenReturn(null);
		List<ResponseDTO> resp2 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getHEITable());
		assertNotEquals(0, resp2.size());

		List<ResponseDTO> resp3 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getHEIInvalidTable());
		assertNotEquals(0, resp3.size());

		List<ResponseDTO> resp4 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getInvalidTubeDataTable());
		assertNotEquals(0, resp4.size());

		when(tubeDataRepository.findByBwg(Mockito.anyInt())).thenReturn(null);
		List<ResponseDTO> resp5 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getTubeDataTable());
		assertNotEquals(0, resp5.size());

		when(steamRepository.findByPressure(Mockito.any())).thenReturn(null);
		List<ResponseDTO> resp6 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getSteamTable());
		assertNotEquals(0, resp6.size());

		List<ResponseDTO> resp7 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getInvalidSteamTable());
		assertNotEquals(0, resp7.size());

		when(lookUpFuelRepository.findByFuel(Mockito.any())).thenReturn(null);
		List<ResponseDTO> resp8 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getLookUpTables());
		assertNotEquals(0, resp8.size());

		List<ResponseDTO> resp9 = LookUpServiceImpl.getLookUpData(UOMTestUtils.getInvalidLookUpTables());
		assertNotEquals(0, resp9.size());
	}

	@Test
	public void conditionTest() throws ChemtreatException {
		List<ResponseDTO> resp = LookUpServiceImpl.getLookUpData(null);
		assertEquals(0, resp.size());
		List<ResponseDTO> resp2 = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockInvalidInput());
		assertEquals(0, resp2.size());
		List<ResponseDTO> resp3 = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockInvalidInput2());
		assertEquals(0, resp3.size());

	}

	@Test
	public void conditionTest2() throws ChemtreatException {
		List<ResponseDTO> resp = null;

		resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockInvalidTable());
		assertEquals(0, resp.size());

		resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockEmptyTable());
		assertEquals(0, resp.size());

		resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockInvalidFrom());
		assertEquals(0, resp.size());

		resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockEmptyFrom());
		assertEquals(0, resp.size());

		resp = LookUpServiceImpl.getLookUpData(UOMTestUtils.createMockInvalidValue());
		assertEquals(0, resp.size());

	}

}
