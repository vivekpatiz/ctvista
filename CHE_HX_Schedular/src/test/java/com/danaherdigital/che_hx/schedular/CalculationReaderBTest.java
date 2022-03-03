package com.danaherdigital.che_hx.schedular;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.batch.core.StepExecution;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.job.CalculationProcessorA;
import com.danaherdigital.che_hx.schedular.job.CalculationReaderB;
import com.danaherdigital.che_hx.schedular.job.CalculationWriterA;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
public class CalculationReaderBTest {

	@Mock
	 private CalcJobRepository jobRepository;

	@Mock
	private AssetDataLogRepository assetDataLogRepository;

	 @InjectMocks
	    private CalculationReaderB calculationReaderB ;
	 @Mock
	    protected StepExecution stepExecution;
	 @Mock
		CalculationProcessorA calculationProcessorA;
@Mock
CalculationWriterA calculationWriterA;
	 @Before
	    public void setUp() throws Exception {
		 CalcJob job=SchedularTestUtil.createMockCalcJob();
		 Optional<CalcJob> jobData=Optional.of(job);

			ReflectionTestUtils.setField(calculationReaderB, "restrictCount", 2);

		 	when(this.assetDataLogRepository.findByworkFlowStatus(ArgumentMatchers.anyString(), ArgumentMatchers.any(Pageable.class))).thenReturn(SchedularTestUtil.createMockAssetDataLogList());
	        when(this.jobRepository.findById(ArgumentMatchers.anyString()))
            .thenReturn(jobData);
	        when(this.jobRepository.save(ArgumentMatchers.any(CalcJob.class)))
            .thenReturn(SchedularTestUtil.createMockCalcJob());

	    }

	 @Test
	    public void testCalculationProcessorBSuccess() throws Exception {
		 SliceResponseDTO sDto=SchedularTestUtil.createMockSliceResponseDTO();
		 when(this.calculationProcessorA.process(ArgumentMatchers.any(AssetDataLog.class))).thenReturn(sDto);
		 doNothing().when(this.calculationWriterA).write(sDto);
		 when(this.jobRepository.findByStatus(ArgumentMatchers.anyString()))
         .thenReturn(Arrays.asList(SchedularTestUtil.createMockCalcJob()));
		 calculationReaderB.before(stepExecution);
		 CalcJob caclJob=new CalcJob();
	         caclJob = calculationReaderB.read();
	        assertNotEquals(null, caclJob);
	        assertNotEquals(null, caclJob.getAssetName());

	        when(this.calculationProcessorA.process(ArgumentMatchers.any(AssetDataLog.class))).thenReturn(null);
	        calculationReaderB.before(stepExecution);
	         caclJob = calculationReaderB.read();
	        assertNotEquals(null, caclJob);
	        assertNotEquals(null, caclJob.getAssetName());
	        // any necessary assertions to test processor logic
	    }

	 @Test
	    public void calculationReaderBTestFailed() throws Exception {
			ReflectionTestUtils.setField(calculationReaderB, "restrictCount", 0);


		 calculationReaderB.before(stepExecution);
		 CalcJob caclJob = calculationReaderB.read();
	        assertEquals(null, caclJob);


	        // any necessary assertions to test processor logic
	    }

}
