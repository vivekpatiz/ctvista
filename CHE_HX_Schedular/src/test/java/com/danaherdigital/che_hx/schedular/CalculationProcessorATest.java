package com.danaherdigital.che_hx.schedular;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.test.util.ReflectionTestUtils;

import com.danaherdigital.che_hx.schedular.dto.SliceDTO;
import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.job.CalculationProcessorA;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.AssetDataRespository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CalculationProcessorATest {
	private AssetDataLog assetDataLog;

	@Mock
	private AssetDataRespository assetDataRespository;

	@Mock
	private AssetDataLogRepository assetDataLogRepository;

	@Mock
	private CalcJobRepository calcJobRepository;

	@Mock
	protected StepExecution stepExecution;

	@Mock
	protected JobParameters jobParams;

	@InjectMocks
	private CalculationProcessorA calculationProcessorA;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Long chunk = 1000L;
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(calculationProcessorA, "chunk", chunk);

		assetDataLog = SchedularTestUtil.createMockAssetLog();

		SliceDTO res = new SliceDTO() {

			@Override
			public Long getMinTime() {
				// TODO Auto-generated method stub
				return System.currentTimeMillis();
			}

			@Override
			public Long getMaxTime() {
				// TODO Auto-generated method stub
				return System.currentTimeMillis();
			}
		};

		when(this.assetDataRespository.getDataTimeStamp(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(res);

	}

	@Test
	public void testCalculationProcessorASuccess1() throws Exception {
		SliceResponseDTO invoice = calculationProcessorA.process(assetDataLog);

		assertNotEquals(null, invoice);
		assertNotEquals(null, invoice.getJobs());

		// any necessary assertions to test processor logic
	}

	@Test
	public void testCalculationProcessorAFailed2() throws Exception {
		assetDataLog = SchedularTestUtil.createMockInavliidAssetLog();
		SliceResponseDTO invoice = calculationProcessorA.process(assetDataLog);

		assertEquals(null, invoice.getJobs());

	}

	@Test
	public void testCalculationProcessorASuccess2() throws Exception {
		Long chunk = 100L;
		ReflectionTestUtils.setField(calculationProcessorA, "chunk", chunk);

		SliceResponseDTO invoice = calculationProcessorA.process(assetDataLog);

		assertNotEquals(null, invoice);
		assertNotEquals(null, invoice.getJobs());

		// any necessary assertions to test processor logic
	}

}