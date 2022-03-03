
package com.danaherdigital.che_hx.schedular;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;
import com.danaherdigital.che_hx.schedular.dto.CalcEngineDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMReqDTO;
import com.danaherdigital.che_hx.schedular.job.CalculationProcessorB;
import com.danaherdigital.che_hx.schedular.model.Asset;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataRespository;
import com.danaherdigital.che_hx.schedular.repo.AssetParamRepository;
import com.danaherdigital.che_hx.schedular.repo.AssetRepository;
import com.danaherdigital.che_hx.schedular.repo.CalculationConfigMasterRepository;
import com.danaherdigital.che_hx.schedular.repo.UnitOfMeasureRepository;
import com.danaherdigital.che_hx.schedular.service.ICalculationService;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CalculationProcessorBTest {
	private CalcJob calcJob;

	@Mock
	private AssetDataRespository assetDataRespository;

	@Mock
	ICalculationService calculationService;

	@Mock
	com.danaherdigital.che_hx.schedular.service.IUOMService IUOMService;

	@Mock
	private AssetParamRepository assetParamRepository;

	@Mock
	CalculationConfigMasterRepository calculationConfigMasterRepository;

	@Mock
	AssetRepository assetRepository;

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	@InjectMocks
	private CalculationProcessorB calculationProcessorB;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(this.unitOfMeasureRepository.findAllUom()).thenReturn(SchedularTestUtil.createMockUnitOfMeasureDOList());
		this.calculationProcessorB.init();
		calcJob = SchedularTestUtil.createMockCalcJob();

	}

	@Test public void testCalculationProcessorBSuccess() throws Exception {
  when(this.assetDataRespository.findAllByDataTimestampBetween(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(),
  ArgumentMatchers.anyString(),ArgumentMatchers.anyLong())).thenReturn(
  SchedularTestUtil.createMockAssetDataTimestamp());

		when(this.calculationConfigMasterRepository.findByAssetTypeId(ArgumentMatchers.anyString()))
				.thenReturn(SchedularTestUtil.createMockCalculationConfigMasterDO());
		when(this.assetDataRespository.findAllByAssetNameTimestamp(ArgumentMatchers.anyString(),
				ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
						.thenReturn(SchedularTestUtil.createMockAssetDataParamDTOList());
		Asset a = new Asset();
		a.setAssetTypeId("aks");
		a.setParentAssetId(a);
		a.setId("akxsjxs");
		a.setName("dummy");
		Optional<Asset> asset = Optional.of(a);
		when(this.IUOMService.getUOMConversionData(ArgumentMatchers.any(UOMReqDTO.class)))
				.thenReturn(SchedularTestUtil.createMockUOMRespDTO());
		when(this.assetRepository.findById(ArgumentMatchers.anyString())).thenReturn(asset);

		when(this.assetParamRepository.findByUnit(ArgumentMatchers.anyString()))
				.thenReturn(SchedularTestUtil.createMockAssetParams());

		when(this.calculationService.getCalculatedData(ArgumentMatchers.any(CalcEngineDTO.class)))
				.thenReturn(new ArrayList<>(Arrays.asList(SchedularTestUtil.createMockInput())));
		ApiResponseDTO apiResponse = calculationProcessorB.process(calcJob);
		assertNotEquals(null, apiResponse);

	}

	@Test
	public void testCalculationProcessorBSuccess2() throws Exception { //
		when(this.assetDataRespository.findAllByDataTimestampBetween(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyLong(), //
				ArgumentMatchers.anyString(), ArgumentMatchers.anyLong()))
						.thenReturn(SchedularTestUtil.createMockAssetDataTimestamp()); //
		when(this.calculationConfigMasterRepository.findByAssetTypeId(ArgumentMatchers.anyString()))
				.thenReturn(SchedularTestUtil.createMockCalculationConfigMasterDO());
		when(this.assetDataRespository.findAllByAssetNameTimestamp(ArgumentMatchers.anyString(),
				ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())) //
						.thenReturn(SchedularTestUtil.createMockAssetDataParamDTOList2());
		Asset a = new Asset();
		a.setAssetTypeId("aks");
		a.setParentAssetId(a);
		a.setId("akxsjxs");
		a.setName("dummy");
		Optional<Asset> asset = Optional.of(a);

		when(this.assetRepository.findById(ArgumentMatchers.anyString())).thenReturn(asset);
		when(this.assetParamRepository.findByUnit(ArgumentMatchers.anyString()))
				.thenReturn(SchedularTestUtil.createMockAssetParams());

		ApiResponseDTO apiResponse = new ApiResponseDTO();

		apiResponse = calculationProcessorB.process(calcJob);
		assertNotEquals(null, apiResponse);

		when(this.unitOfMeasureRepository.findAllUom()).thenReturn(SchedularTestUtil.createMockUnitOfMeasureDOList2());
		this.calculationProcessorB.init();
		apiResponse = calculationProcessorB.process(calcJob);
		assertNotEquals(null, apiResponse);

	}

	@Test
	public void testCalculationProcessorBException() throws Exception {
		CalcJob c = new CalcJob();
		c.setId(null);
		c.setFromDate(null);
		ApiResponseDTO apiResponse = calculationProcessorB.process(c);
		assertNotEquals(null, apiResponse);

		ApiResponseDTO apiResponse2 = calculationProcessorB.process(null);
		assertEquals(null, apiResponse2.getAssetId());

		when(this.assetDataRespository.findAllByAssetNameTimestamp(ArgumentMatchers.anyString(),
				ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(Collections.emptyList());
		when(this.assetDataRespository.findAllByDataTimestampBetween(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(), ArgumentMatchers.anyLong()))
						.thenReturn(SchedularTestUtil.createMockAssetDataTimestamp());
		when(this.calculationConfigMasterRepository.findByAssetTypeId(ArgumentMatchers.anyString()))
				.thenReturn(SchedularTestUtil.createMockCalculationConfigMasterDO());
		ApiResponseDTO apiResponse3 = calculationProcessorB.process(calcJob);
		assertEquals(null, apiResponse3.getAssetId());

		when(this.assetDataRespository.findAllByAssetNameTimestamp(ArgumentMatchers.anyString(),
				ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
						.thenReturn(SchedularTestUtil.createMockAssetDataParamDTOList());

		when(this.assetParamRepository.findByUnit(ArgumentMatchers.anyString())).thenReturn(null);
		ApiResponseDTO apiResponse4 = calculationProcessorB.process(calcJob);
		assertEquals(null, apiResponse4.getAssetId());
		Asset a = new Asset();
		a.setAssetTypeId("aks");
		a.setParentAssetId(a);
		a.setId("akxsjxs");
		a.setName("dummy");
		Optional<Asset> asset = Optional.of(a);

		when(this.assetRepository.findById(ArgumentMatchers.anyString())).thenReturn(asset);
		when(this.assetParamRepository.findByUnit(ArgumentMatchers.anyString()))
				.thenReturn(SchedularTestUtil.createMockAssetParams());

		ApiResponseDTO apiResponse5 = calculationProcessorB.process(calcJob);
		assertEquals(null, apiResponse5.getAssetId());

	}

}
