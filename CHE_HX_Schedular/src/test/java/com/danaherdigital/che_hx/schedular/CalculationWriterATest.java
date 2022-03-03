
package com.danaherdigital.che_hx.schedular;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.launch.JobOperator;

import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.job.CalculationWriterA;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CalculationWriterATest {
	private SliceResponseDTO sliceResponseDTO;

	@Mock
	private CalcJobRepository jobRepository;

	@Mock
	private AssetDataLogRepository assetDataLogRepository;

	@Mock
	JobOperator jobOperator;

	@InjectMocks
	private CalculationWriterA calculationWriterA;

	@Before
	public void setUp() throws Exception {
		AssetDataLog adl = SchedularTestUtil.createMockAssetLog();
		Optional<AssetDataLog> op = Optional.of(adl);
		MockitoAnnotations.initMocks(this); //
		List<AssetDataLog> list = new ArrayList<>();
		list.add(adl);
		sliceResponseDTO = SchedularTestUtil.createMockSliceResponseDTO();
		when(this.jobRepository.saveAll(ArgumentMatchers.anyCollectionOf(CalcJob.class)))
				.thenReturn(new ArrayList<CalcJob>(Arrays.asList(SchedularTestUtil.createMockCalcJob())));

		when(this.assetDataLogRepository.findByAssetName(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(list);

		when(this.assetDataLogRepository.save(ArgumentMatchers.any(AssetDataLog.class))).thenReturn(adl);

	}

	@Test
	public void testCalculationWriterASuccess2() throws Exception {
		boolean b = false;
		sliceResponseDTO = SchedularTestUtil.createMockSliceResponseDTO2();
		when(this.assetDataLogRepository.findByAssetName(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenThrow(NullPointerException.class);
		calculationWriterA.write(sliceResponseDTO);

		assertEquals(false, b);
	}

	@Test
	public void testCalculationWriterASuccess5() throws Exception {
		boolean b = false;
		sliceResponseDTO = SchedularTestUtil.createMockSliceResponseDTO4();
		calculationWriterA.write(sliceResponseDTO);
		assertEquals(false, b);
	}

	@Test
	public void testCalculationWriterASuccess() throws Exception {
		boolean b = true;

		calculationWriterA.write(sliceResponseDTO);
		assertEquals(true, b);

	}

	@Test
	public void testCalculationWriterASuccess3() throws Exception {
		boolean b = false;

		sliceResponseDTO = SchedularTestUtil.createMockSliceResponseDTO3();

		calculationWriterA.write(sliceResponseDTO);
		assertEquals(false, b);

	}

}
