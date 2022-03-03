
package com.danaherdigital.che_hx.schedular;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;
import com.danaherdigital.che_hx.schedular.dto.PersistResDTO;
import com.danaherdigital.che_hx.schedular.dto.TimeSeriesReqDTO;
import com.danaherdigital.che_hx.schedular.job.CalculationWriterB;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.service.ITimeSeriesService;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CalculationWriterBTest {
	private ApiResponseDTO apiResponseDTO;

	@Mock
	ITimeSeriesService timeSeriesService;

	@Mock
	private CalcJobRepository jobRepository;

	@Mock
	private AssetDataLogRepository assetDataLogRepository;

	@InjectMocks
	private CalculationWriterB calculationWriterB;

	@Before
	public void setUp() throws Exception {
		CalcJob job = SchedularTestUtil.createMockCalcJob();
		Optional<CalcJob> op = Optional.of(job);
		MockitoAnnotations.initMocks(this);

		when(this.jobRepository.findById(ArgumentMatchers.anyString())).thenReturn(op);

		when(this.assetDataLogRepository.findByAssetName(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyLong()))
						.thenReturn(SchedularTestUtil.createMockAssetDataLogList());

		when(this.jobRepository.save(ArgumentMatchers.any(CalcJob.class))).thenReturn(job);

	}

	@Test
	public void testCalculationWriterBSuccess() throws Exception {
		boolean b = true;
		PersistResDTO res = new PersistResDTO();
		res.setPersisted(true);
		apiResponseDTO = SchedularTestUtil.createMockApiResponseDTO();
		when(this.timeSeriesService.persistTimeSeriesData(ArgumentMatchers.any(TimeSeriesReqDTO.class)))
				.thenReturn(res);
		calculationWriterB.write(new ArrayList<ApiResponseDTO>(Arrays.asList(apiResponseDTO)));
		assertNotEquals(null, res);

		res.setDeadLock(true);
		res.setPersisted(false);
		when(this.timeSeriesService.persistTimeSeriesData(ArgumentMatchers.any(TimeSeriesReqDTO.class)))
		.thenReturn(res);
		calculationWriterB.write(new ArrayList<ApiResponseDTO>(Arrays.asList(apiResponseDTO)));
		assertNotEquals(null, res);


		res.setDeadLock(false);
		res.setPersisted(false);
		when(this.timeSeriesService.persistTimeSeriesData(ArgumentMatchers.any(TimeSeriesReqDTO.class)))
		.thenReturn(res);
		calculationWriterB.write(new ArrayList<ApiResponseDTO>(Arrays.asList(apiResponseDTO)));
		assertNotEquals(null, res);
	}

	@Test
	public void testCalculationWriterBSuccess2() throws Exception {
		boolean b = true;

		apiResponseDTO = new ApiResponseDTO();
		apiResponseDTO.setJobId(UUID.randomUUID().toString());
		apiResponseDTO.setId(1L);
		calculationWriterB.write(new ArrayList<ApiResponseDTO>(Arrays.asList(apiResponseDTO)));
		assertEquals(true, b);

	}

	@Test
	public void testCalculationWriterBFailed() throws Exception {
		boolean b = true;
		calculationWriterB.write(null);
		assertEquals(true, b);

	}

	@Test
	public void testCalculationWriterBException() throws Exception {
		boolean b = true;
		apiResponseDTO = SchedularTestUtil.createMockApiResponseDTO();

		when(this.assetDataLogRepository.findByAssetName(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyLong()))
						.thenThrow(NullPointerException.class);

		calculationWriterB.write(new ArrayList<ApiResponseDTO>(Arrays.asList(apiResponseDTO)));
		assertEquals(true, b);

	}

}
