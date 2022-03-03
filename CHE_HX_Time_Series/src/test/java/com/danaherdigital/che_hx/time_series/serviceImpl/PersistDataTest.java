package com.danaherdigital.che_hx.time_series.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.PessimisticLockingFailureException;

import com.danaherdigital.che_hx.time_series.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.time_series.dto.AssetDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO2;
import com.danaherdigital.che_hx.time_series.dto.ChartData;
import com.danaherdigital.che_hx.time_series.dto.PersistResDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisResponseDTO;
import com.danaherdigital.che_hx.time_series.model.CalculationConfigMaster;
import com.danaherdigital.che_hx.time_series.model.DerivedData;
import com.danaherdigital.che_hx.time_series.repository.AssetDataRepository;
import com.danaherdigital.che_hx.time_series.repository.AssetDataTimeSeriesRepository;
import com.danaherdigital.che_hx.time_series.repository.AssetParamRepository;
import com.danaherdigital.che_hx.time_series.repository.CalculationConfigMasterRepository;
import com.danaherdigital.che_hx.time_series.serviceimpl.TelemetryServiceImpl;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.danaherdigital.che_hx.time_series.utils.AssetDataTestUtil;



@RunWith(MockitoJUnitRunner.class)
public class PersistDataTest {
	@InjectMocks
	TelemetryServiceImpl telemetryServiceImpl;

	@Mock
	AssetDataRepository assetDataRepository;
	@Mock
	CalculationConfigMasterRepository calculationConfigMasterRepository;
	@Mock
	AssetDataTimeSeriesRepository assetDataTimeSeriesRepository;

	@Mock
	AssetParamRepository assetParamRepository;

	@Mock
	private EntityManagerFactory emf;
	@Mock
	private SessionFactory mockedSessionFactory;

	@Mock
	private EntityTransaction mockedTransaction;

	@Mock
	private EntityManager em;

	@Mock
	private Session session;

	@Test
	public void persistDataTest() {

		//EntityManagerFactory emf=Mockito.mock(EntityManagerFactory.class);
		Mockito.when(emf.unwrap(SessionFactory.class)).thenReturn(mockedSessionFactory);
		Mockito.when(mockedSessionFactory.openSession()).thenReturn(session);
		Mockito.when(session.getEntityManagerFactory()).thenReturn(emf);
		Mockito.when(emf.createEntityManager()).thenReturn(em);

		Mockito.when(em.getTransaction()).thenReturn(mockedTransaction);
		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString()))
				.thenReturn(AssetDataTestUtil.createMockCalculationConfigMasterDO());

		when(assetParamRepository.findParams(Mockito.anyString()))
				.thenReturn(new ArrayList<>(Arrays.asList(AssetDataTestUtil.createMockAssetParamDTO())));


		when(assetDataRepository.countByTimeStamp(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1L);


		when(assetDataTimeSeriesRepository.deleteOldComputed(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1);
			telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());

			when(assetDataRepository.countByTimeStamp(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
			.thenThrow(PessimisticLockingFailureException.class);
			telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());



		telemetryServiceImpl.persistCalculatedData(null);
		verify(em, times(1)).getTransaction();

		DerivedData d = new DerivedData();
		d.setInput(Collections.emptyList());

		telemetryServiceImpl.persistCalculatedData(d);
		verify(em, times(1)).getTransaction();

		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString())).thenReturn(null);
		telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		verify(em, times(1)).getTransaction();
		CalculationConfigMaster cm = new CalculationConfigMaster();
		cm.setOutputParams(null);
		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString())).thenReturn(cm);

		telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		verify(em, times(1)).getTransaction();

		when(assetParamRepository.findParams(Mockito.anyString())).thenReturn(Collections.emptyList());
		telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		verify(em, times(1)).getTransaction();
		CalculationConfigMaster cm2 = new CalculationConfigMaster();
		cm2.setOutputParams("");
		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString())).thenReturn(cm2);
		when(assetParamRepository.findParams(Mockito.anyString()))
				.thenReturn(new ArrayList<>(Arrays.asList(AssetDataTestUtil.createMockAssetParamDTO())));
		telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		verify(em, times(1)).getTransaction();

		telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		verify(em, times(1)).getTransaction();

	}

	@Test
	public void exceptionTest()
	{


		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString()))
		.thenThrow(NullPointerException.class);


	PersistResDTO resp=	telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		assertEquals(false, resp.isPersisted());

	}

	@Test
	public void exceptionTest2()
	{


		Mockito.when(emf.unwrap(SessionFactory.class)).thenReturn(mockedSessionFactory);
		Mockito.when(mockedSessionFactory.openSession()).thenReturn(session);
		Mockito.when(session.getEntityManagerFactory()).thenReturn(emf);
		Mockito.when(emf.createEntityManager()).thenReturn(em);

		Mockito.when(em.getTransaction()).thenThrow(OptimisticLockException.class);
		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString()))
				.thenReturn(AssetDataTestUtil.createMockCalculationConfigMasterDO());

		when(assetParamRepository.findParams(Mockito.anyString()))
				.thenReturn(new ArrayList<>(Arrays.asList(AssetDataTestUtil.createMockAssetParamDTO())));


		when(assetDataRepository.countByTimeStamp(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1L);


		when(assetDataTimeSeriesRepository.deleteOldComputed(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1);


	PersistResDTO resp=	telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		assertEquals(false, resp.isPersisted());



	}

	@Test
	public void exceptionTest3()
	{


		Mockito.when(emf.unwrap(SessionFactory.class)).thenReturn(mockedSessionFactory);
		Mockito.when(mockedSessionFactory.openSession()).thenReturn(session);
		Mockito.when(session.getEntityManagerFactory()).thenReturn(emf);
		Mockito.when(emf.createEntityManager()).thenReturn(em);

		Mockito.when(em.getTransaction()).thenThrow(NullPointerException.class);
		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString()))
				.thenReturn(AssetDataTestUtil.createMockCalculationConfigMasterDO());

		when(assetParamRepository.findParams(Mockito.anyString()))
				.thenReturn(new ArrayList<>(Arrays.asList(AssetDataTestUtil.createMockAssetParamDTO())));


		when(assetDataRepository.countByTimeStamp(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1L);


		when(assetDataTimeSeriesRepository.deleteOldComputed(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1);


	PersistResDTO resp=	telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		assertEquals(false, resp.isPersisted());
	}


	@Test
	public void exceptionTest4()
	{


		Mockito.when(emf.unwrap(SessionFactory.class)).thenReturn(mockedSessionFactory);
		Mockito.when(mockedSessionFactory.openSession()).thenReturn(session);
		Mockito.when(session.getEntityManagerFactory()).thenReturn(emf);
		Mockito.when(emf.createEntityManager()).thenReturn(em);

		Mockito.when(em.getTransaction()).thenReturn(mockedTransaction);
		Mockito.doThrow(NullPointerException.class).when(mockedTransaction).commit();
		when(calculationConfigMasterRepository.findByAssetTypeId(Mockito.anyString()))
				.thenReturn(AssetDataTestUtil.createMockCalculationConfigMasterDO());

		when(assetParamRepository.findParams(Mockito.anyString()))
				.thenReturn(new ArrayList<>(Arrays.asList(AssetDataTestUtil.createMockAssetParamDTO())));


		when(assetDataRepository.countByTimeStamp(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1L);


		when(assetDataTimeSeriesRepository.deleteOldComputed(Mockito.anyInt(),Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong()))
				.thenReturn(1);


	PersistResDTO resp=	telemetryServiceImpl.persistCalculatedData(AssetDataTestUtil.createMockDerivedDataDTO());
		assertEquals(false, resp.isPersisted());
	}

	@Test
	public void getMinMaxLoadTest() {
		AssetLoadDTO a = new AssetLoadDTO();
		a.setMaxVal(100d);
		a.setMinVal(0d);
		when(assetDataRepository.getMinMaxLoad(Mockito.anyString(), Mockito.anyString())).thenReturn(a);
		AssetDTOLoadInt load = telemetryServiceImpl.getMinMaxLoad("32a7862f-dba5-11ea-b09d-025041000001", "gross_load");
		assertNotEquals(null, load);
		when(assetDataRepository.getMinMaxLoad(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		AssetDTOLoadInt load2 = telemetryServiceImpl.getMinMaxLoad(null, "gross_load");
		assertEquals(0, load2.getMaxVal());
		AssetDTOLoadInt load3 = telemetryServiceImpl.getMinMaxLoad("32a7862f-dba5-11ea-b09d-025041000001", null);
		assertEquals(0, load3.getMaxVal());
		AssetLoadDTO a1 = new AssetLoadDTO();
		a1.setMaxVal(null);
		a1.setMinVal(null);
		when(assetDataRepository.getMinMaxLoad(Mockito.anyString(), Mockito.anyString())).thenReturn(a1);
		AssetDTOLoadInt load4 = telemetryServiceImpl.getMinMaxLoad("32a7862f-dba5-11ea-b09d-025041000001",
				"gross_load");
		assertEquals(0, load4.getMaxVal());

	}

	@Test
	public void getOutputTest() {

		List<AssetDataDTO> assetDataDTO = new ArrayList<>();
	assetDataDTO.add(AssetDataTestUtil.getAssetDataDTOTemplate()); //
	when(assetDataRepository.getChartsData(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt(),
			Mockito.anyString(), Mockito.anyString())).thenReturn(assetDataDTO); // //

	List<AssetParamDTO2> apList=new ArrayList<>();
		Map<Long, Double> loadMap =new HashMap<>();
		loadMap.put(123456899L, 1.23);
		List<ChartData> lcd=telemetryServiceImpl.getOutput(new Date(), new Date(), 0, 0,2, "32a7862f-dba5-11ea-b09d-025041000001", "32a7862f-dba5-11ea-b09d-025041000001", loadMap,apList);
		assertEquals(1, lcd.size());
	}

	@Test
	public void getYaxisChartsDataSuccessTest() {
		List<AssetLoadDataDTO> assetLoadDataDTO = new ArrayList<>();
		assetLoadDataDTO.add(AssetDataTestUtil.getassetLoadDataDTOTemplate());
		when(assetDataRepository.getLoadDataYaxis(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(assetLoadDataDTO);
		List<AssetDataDTO> assetDataDTO = new ArrayList<>();
		assetDataDTO.add(AssetDataTestUtil.getAssetDataDTOTemplate()); //
		when(assetDataRepository.getYaxisChartsData(Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString(),  Mockito.anyDouble(),
				 Mockito.anyDouble(),Mockito.anyLong(),Mockito.anyLong())).thenReturn(assetDataDTO);
		YAxisResponseDTO resp= telemetryServiceImpl.getYaxisChartsData(AssetDataTestUtil.createMockYAxisReqDTO());
		assertNotEquals(null, resp);

	}

}
