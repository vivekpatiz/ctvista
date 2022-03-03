package com.danaherdigital.che_hx.time_series.serviceimpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.apache.commons.math3.util.Precision;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.danaherdigital.che_hx.time_series.MultiThreadTS;
import com.danaherdigital.che_hx.time_series.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.time_series.dto.AssetDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO2;
import com.danaherdigital.che_hx.time_series.dto.ChartData;
import com.danaherdigital.che_hx.time_series.dto.ChartDataArray;
import com.danaherdigital.che_hx.time_series.dto.Data;
import com.danaherdigital.che_hx.time_series.dto.PersistResDTO;
import com.danaherdigital.che_hx.time_series.dto.ResDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisData;
import com.danaherdigital.che_hx.time_series.dto.YAxisReqDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisResponseDTO;
import com.danaherdigital.che_hx.time_series.model.AssetDataTimeSeries;
import com.danaherdigital.che_hx.time_series.model.CalculationConfigMaster;
import com.danaherdigital.che_hx.time_series.model.DerivedData;
import com.danaherdigital.che_hx.time_series.repository.AssetDataRepository;
import com.danaherdigital.che_hx.time_series.repository.AssetDataTimeSeriesRepository;
import com.danaherdigital.che_hx.time_series.repository.AssetParamRepository;
import com.danaherdigital.che_hx.time_series.repository.CalculationConfigMasterRepository;
import com.danaherdigital.che_hx.time_series.service.TelemetryService;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class TelemetryServiceImpl implements TelemetryService {

	@Autowired
	AssetDataRepository assetDataRepository;
	@Autowired
	AssetDataTimeSeriesRepository assetDataTimeSeriesRepository;
	/** The asset param repository. */
	@Autowired
	AssetParamRepository assetParamRepository;

	/** The calculation config master repository. */
	@Autowired
	CalculationConfigMasterRepository calculationConfigMasterRepository;
	@Autowired
	TelemetryService telemetryService;

	/** The asset id. */
	String assetId = null;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private EntityManagerFactory emf;

	static final Logger LOGGER = LoggerFactory.getLogger(TelemetryServiceImpl.class);

	@Value("${asset.mgmt.assetparam.id.url}")
	private String assetParamIdURL;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public JSONObject getChartsData(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad, Integer tenantId,
			String assetId, List<String> assetParamName, Boolean isKpi) {

		log.info("time before rest call  :{}", System.currentTimeMillis());
		if (Boolean.TRUE.equals(isKpi) && assetId != null) {
			LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
					ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getCondenserLoadById");
			List<String> result = null;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(assetParamIdURL).path(assetId);

			try {

				ResponseEntity<String[]> response = restTemplate.getForEntity(builder.toUriString(), String[].class,
						entity);

				if (response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
					String[] respList = response.getBody();
					result = new ArrayList<>(Arrays.asList(respList));
				}

			} catch (Exception e) {
				LOGGER.error("error in getCondenserLoadById->{}", e);
			}

			assetParamName = result;
		}
		log.info("time afetr rest :{}", System.currentTimeMillis());

		JSONObject resultObj = null;
		if (assetId != null && tenantId != null && assetParamName != null && !assetParamName.isEmpty()) {

			resultObj = new JSONObject();
			JSONObject jObj = new JSONObject();
			JSONArray jarry1 = new JSONArray();
			Map<Long, Double> loadMap = new HashMap<>();
			List<AssetLoadDataDTO> assetLoadDataDTOList = assetDataRepository.getLoadData(fromDate.getTime() / 1000,
					toDate.getTime() / 1000, tenantId, assetId, ApplicationConstants.LOAD, fromLoad * 1.0d,
					toLoad * 1.0d);
			log.info("time for load data:{}", System.currentTimeMillis());

			if (!assetLoadDataDTOList.isEmpty()) {

				for (AssetLoadDataDTO assetLoadDataDTO : assetLoadDataDTOList) {
					loadMap.put(assetLoadDataDTO.getDataTimestamp(), assetLoadDataDTO.getFloatValues());
				}

				for (String assetParamId : assetParamName) {
					List<AssetDataDTO> assetDataDTOlist = assetDataRepository.getChartsData(fromDate.getTime() / 1000,
							toDate.getTime() / 1000, tenantId, assetId, assetParamId);
					log.info("time for charts data:{}", System.currentTimeMillis());

					Double avg = 0d;
					Double min = 0d;
					Double max = 0d;

					if (!assetDataDTOlist.isEmpty()) {
						jObj = new JSONObject();
						avg = Precision.round(assetDataDTOlist.stream().mapToDouble(AssetDataDTO::getFloatValue)
								.average().getAsDouble(), 2);
						min = Precision.round(
								assetDataDTOlist.stream().mapToDouble(AssetDataDTO::getFloatValue).min().getAsDouble(),
								2);
						max = Precision.round(
								assetDataDTOlist.stream().mapToDouble(AssetDataDTO::getFloatValue).max().getAsDouble(),
								2);

						JSONArray jarry = new JSONArray();

						assetDataDTOlist.stream().forEach(e -> {
							synchronized (jarry) {
								JSONObject joBj = new JSONObject();

								try {

									if (loadMap.containsKey(e.getDataTimestamp())) {
										joBj.put("date", e.getEpochDate());
										joBj.put("value", e.getFloatValue());
										joBj.put("load",
												(double) (loadMap.containsKey(e.getDataTimestamp())
														? loadMap.get(e.getDataTimestamp())
														: 0.0d));
										jarry.put(joBj);
									}
								} catch (JSONException e1) {
									LOGGER.error("error in getChartsData {}", e1);
								}
							}
						});
						jObj.put("data", jarry);
						AssetParamDTO2 param = getParamNameById(assetParamId);
						if (!Objects.isNull(param)) {
							jObj.put("name", param.getName());
							jObj.put("display_name", param.getDisplayName());

						}
						jarry1.put(jObj);
					}
					jObj.put("avgKpi", avg);
					jObj.put("min", min);
					jObj.put("max", max);

				}
			}
			resultObj.put("chart_data", jarry1);

		}
		log.info("time after completes:{}", System.currentTimeMillis());

		return resultObj;
	}

	private AssetParamDTO2 getParamNameById(String id) {

		return assetDataRepository.getAssetParamName(id);

	}

	/**
	 * Persist calculated data.
	 *
	 * @param calcReqData the calc req data
	 */
	@Override
	public PersistResDTO persistCalculatedData(DerivedData calcReqData) {
		boolean b = false;
		PersistResDTO resp = new PersistResDTO();
		List<AssetDataTimeSeries> finalList = new LinkedList<>();
		try {
			if (!Objects.isNull(calcReqData) && !CollectionUtils.isEmpty(calcReqData.getInput())) {
				assetId = calcReqData.getAssetId();
				CalculationConfigMaster cm = calculationConfigMasterRepository.findByAssetTypeId(assetId);

				if (!(Objects.isNull(cm)) && !Objects.isNull(cm.getOutputParams())) {

					List<AssetParamDTO> assetParamList = assetParamRepository.findParams(assetId);
					List<Map<String, String>> outPutParams = new ObjectMapper().readValue(cm.getOutputParams(),
							new TypeReference<List<Map<String, String>>>() {
							});
					if (!(CollectionUtils.isEmpty(assetParamList) && CollectionUtils.isEmpty(outPutParams))) {

						for (Map<String, Object> dt : calcReqData.getInput()) {

							List<AssetDataTimeSeries> aList = createAssetDataTimeSeriesDOList(dt, outPutParams,
									assetParamList);

							finalList.addAll(aList);

						}

						if (!CollectionUtils.isEmpty(finalList)) {

							AssetDataTimeSeries minTimestamp =
									finalList.stream().min(Comparator.comparingLong(AssetDataTimeSeries::getTimeStamp)).orElse(null)
											;

							AssetDataTimeSeries maxTimestamp =
									finalList.stream().max(Comparator.comparingLong(AssetDataTimeSeries::getTimeStamp)).orElse(null);
							if (!Objects.isNull(minTimestamp) && !Objects.isNull(maxTimestamp)) {
								log.info("minTimesatmp {} maxTieStamp {}", minTimestamp.getTimeStamp(), maxTimestamp.getTimeStamp());
								Long countExists = assetDataRepository.countByTimeStamp(calcReqData.getTenantId(),
										calcReqData.getAssetId(), minTimestamp.getTimeStamp(), maxTimestamp.getTimeStamp());
								log.info("count to delete:{}", countExists);
								if (countExists > 0) {
									deleteOldData(calcReqData.getTenantId(), calcReqData.getAssetId(),
											minTimestamp.getTimeStamp(), maxTimestamp.getTimeStamp());
								}
								EntityManager em = null;
								SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
								b = persist(sessionFactory, finalList, em, resp);


							}
						}

					}
				}

			}
		} catch (PessimisticLockingFailureException e) {
			log.error("error in TelemetryServiceImpl.persistCalculatedData lock exception block->{}", e);

			resp.setDeadLock(true);

			b = false;
		}

		catch (Exception e) {
			log.error("error in TelemetryServiceImpl.persistCalculatedData->{}", e);
				
				resp.setDeadLock(true);
			
			b = false;
		}
		resp.setPersisted(b);
		return resp;

	}

	private void deleteOldData(Integer tenantId, String assetId, Long minTimestamp, Long maxTimestamp) {
		int l = assetDataTimeSeriesRepository.deleteOldComputed(tenantId, assetId, minTimestamp, maxTimestamp);
		log.info("is deleted :{}", l);

	}

	private boolean persist(SessionFactory sessionFactory, List<AssetDataTimeSeries> finalList, EntityManager em,
			PersistResDTO resp) {
		try (Session session = sessionFactory.openSession()) {
			em = session.getEntityManagerFactory().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			Iterator<AssetDataTimeSeries> iterator = finalList.iterator();
			tx.begin();
			int count = 0;
			while (iterator.hasNext()) {
				em.persist(iterator.next());

				count++;
				if (count % 1000 == 0) {
					em.flush();
					em.clear();
					log.info("{} rows persisted sucessfully", count);

					commitTransaction(tx);
				}
			}
			tx.commit();
			em.close();
		} catch (OptimisticLockException e) {
			log.error("error in TelemetryServiceImpl persist optimistic lock block->{}", e);
			resp.setDeadLock(true);

			if (em.isOpen()) {
				em.close();
			}
			return false;

		} catch (Exception e) {
			log.error("error in TelemetryServiceImpl persist->{}", e);

			if (em.isOpen()) {
				em.close();
			}
			return false;

		}

		return true;

	}

	private void commitTransaction(EntityTransaction tx) {
		try {
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			log.error("error while persisting :{}", e);
		}
		tx.begin();

	}

	/**
	 * Creates the asset data time series DO list.
	 *
	 * @param dt             the dt
	 * @param assetId        the asset id
	 * @param outPutParams   the out put params
	 * @param assetParamList the asset param list
	 * @return the list
	 */
	private List<AssetDataTimeSeries> createAssetDataTimeSeriesDOList(Map<String, Object> dt,
			List<Map<String, String>> outPutParams, List<AssetParamDTO> assetParamList) {

		List<AssetDataTimeSeries> ast = new ArrayList<>();
		Map<String, Object> newMap = new HashMap<>();

		for (Map<String, String> param : outPutParams) {
			for (Map.Entry<String, String> map : param.entrySet()) {

				newMap.put(map.getValue(),
						dt.get(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, map.getValue().trim())));

			}

		}

		if (!CollectionUtils.isEmpty(newMap)) {

			for (AssetParamDTO ap : assetParamList) {

				if (!Objects.isNull(newMap.get(ap.getParam()))) {
					ast.add(createAssetDataTimeSeriesDO(ap.getParam(), newMap.get(ap.getParam()), dt.get("timeStamp"),
							ap));
				}
			}

		}

		return ast;

	}

	/**
	 * Creates the asset data time series DO.
	 *
	 * @param param      the param
	 * @param floatValue the float value
	 * @param timeStamp  the time stamp
	 * @param ap         the ap
	 * @return the asset data time series
	 */
	private AssetDataTimeSeries createAssetDataTimeSeriesDO(String param, Object floatValue, Object timeStamp,
			AssetParamDTO ap) {
		AssetDataTimeSeries adts = new AssetDataTimeSeries();

		if (!(Objects.isNull(param) && Objects.isNull(timeStamp))) {

			adts.setAssetName(assetId);
			adts.setAssetParamsName(ap.getId());
			adts.setTimeStamp(Long.parseLong(timeStamp.toString()));
			if ((!Objects.isNull(floatValue) && !Double.isInfinite(Double.parseDouble(floatValue.toString()))
					&& !Double.isNaN(Double.parseDouble(floatValue.toString())))) {
				adts.setFloatValues(Double.parseDouble(floatValue.toString()));
			}
			adts.setTagName(param);
			adts.setTenantId(ap.getTenantId());
		}
		return adts;

	}

	@Override
	public AssetDTOLoadInt getMinMaxLoad(String assetId, String assetParamName) {
		AssetLoadDTO assetLoadDTO = null;
		AssetDTOLoadInt resultDto = new AssetDTOLoadInt();
		if (assetId != null && assetParamName != null) {
			assetLoadDTO = assetDataRepository.getMinMaxLoad(assetId, assetParamName);
			resultDto.setMinVal((int) (assetLoadDTO.getMinVal() != null ? Math.floor(assetLoadDTO.getMinVal()) : 0));
			resultDto.setMaxVal((int) (assetLoadDTO.getMaxVal() != null ? Math.ceil(assetLoadDTO.getMaxVal()) : 0));

		}

		return resultDto;
	}

	@Override
	public ResDTO getChartsData2(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad, Integer tenantId,
			String assetId, List<String> assetParamName, Boolean isKpi) {

		if (Boolean.TRUE.equals(isKpi) && assetId != null) {
			LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
					ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getCondenserLoadById");

			List<String> result = null;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>(headers);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(assetParamIdURL).path(assetId);
			UriComponents components = builder.build(true);
			URI uri = components.toUri();
			try {

				ResponseEntity<String[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String[].class);

				if (response.getStatusCode() == HttpStatus.OK) {
					String[] respList = response.getBody();
					result = new ArrayList<>(Arrays.asList(respList));
				}

			} catch (Exception e) {
				LOGGER.error("error in getCondenserLoadById");
			}

			assetParamName = result;

		}

		ResDTO resultObj = null;
		if (assetId != null && tenantId != null && assetParamName != null && !assetParamName.isEmpty()) {

			resultObj = new ResDTO();
			List<ChartData> jarry1 = new ArrayList<>();
			Map<Long, Double> loadMap = new HashMap<>();
			long from = fromDate.getTime() / 1000;
			long to = toDate.getTime() / 1000;
			double loadfrom = fromLoad * 1.0d;
			double loadto = toLoad * 1.0d;

			List<AssetLoadDataDTO> assetLoadDataDTOList = assetDataRepository.getLoadData(from, to, tenantId, assetId,
					ApplicationConstants.LOAD, loadfrom, loadto);
			List<AssetParamDTO2> apList = assetDataRepository.getAssetParam(assetId);

			if (!assetLoadDataDTOList.isEmpty()) {

				assetLoadDataDTOList.forEach(a -> loadMap.put(a.getDataTimestamp(), a.getFloatValues())

				);

			}

			ExecutorService executor = Executors.newCachedThreadPool();

			List<Future<List<ChartData>>> futureList = new ArrayList<>();
			for (String assetParamId : assetParamName) {
				futureList.add(executor.submit(new MultiThreadTS(fromDate, toDate, fromLoad, toLoad, tenantId, assetId,
						assetParamId, loadMap, apList, telemetryService)));

			}

			for (Iterator<Future<List<ChartData>>> it = futureList.iterator(); it.hasNext();) {
				Future<? extends List<ChartData>> f = it.next();
				try {
					jarry1.addAll(f.get());
				} catch (InterruptedException | ExecutionException e) {
					log.warn("Interrupted!{}", e);
					Thread.currentThread().interrupt();
				}
			}

			resultObj.setChartData(jarry1);
			executor.shutdown();

		}

		return resultObj;
	}

	private String getParamName(String assetParamId, List<AssetParamDTO2> apList) {

		return apList.stream().filter(x -> assetParamId.equals(x.getId())).map(AssetParamDTO2::getName).findAny()
				.orElse("");
	}

	@Override
	public synchronized List<ChartData> getOutput(Date fromDate, Date toDate, Integer fromLoad, Integer toLoad,
			Integer tenantId, String assetId, String assetParamId, Map<Long, Double> loadMap,
			List<AssetParamDTO2> apList) {

		ChartData jObj = new ChartData();
		List<ChartData> jarry1 = new CopyOnWriteArrayList<>();

		List<AssetDataDTO> assetDataDTOlist = assetDataRepository.getChartsData(fromDate.getTime() / 1000,
				toDate.getTime() / 1000, tenantId, assetId, assetParamId);
		Double avg = 0d;
		Double min = 0d;
		Double max = 0d;
		if (!assetDataDTOlist.isEmpty()) {
			jObj = new ChartData();
			avg = Precision.round(
					assetDataDTOlist.stream().mapToDouble(AssetDataDTO::getFloatValue).average().getAsDouble(), 2);
			min = Precision
					.round(assetDataDTOlist.stream().mapToDouble(AssetDataDTO::getFloatValue).min().getAsDouble(), 2);
			max = Precision
					.round(assetDataDTOlist.stream().mapToDouble(AssetDataDTO::getFloatValue).max().getAsDouble(), 2);

			List<Data> jarry = new ArrayList<>();// list of class

			assetDataDTOlist.stream().forEach(e -> {

				Data joBj = new Data();// class

				try {

					if (loadMap.containsKey(e.getDataTimestamp())) {
						joBj.setDate(e.getEpochDate());

						joBj.setValue(e.getFloatValue());
						joBj.setLoad(
								(double) (loadMap.containsKey(e.getDataTimestamp()) ? loadMap.get(e.getDataTimestamp())
										: 0.0d));
						jarry.add(joBj);
					}
				} catch (JSONException e1) {
					LOGGER.error("error in getChartsData:{}", e1);
				}

			});

			jObj.setData(jarry);
			jObj.setName(getParamName(assetParamId, apList));
			jarry1.add(jObj);
		}
		jObj.setAvgKpi(avg);
		jObj.setMin(min);
		jObj.setMax(max);
		return jarry1;

	}

	@Override
	public YAxisResponseDTO getYaxisChartsData(YAxisReqDTO req) {
		YAxisResponseDTO resp = new YAxisResponseDTO();
		if (!Objects.isNull(req)) {

			Map<Long, Double> loadMap = new HashMap<>();
			List<AssetLoadDataDTO> assetLoadDataDTOList = assetDataRepository.getLoadDataYaxis(req.getFromdate(),
					req.getTodate(), req.getTenantid(), req.getAssetId(), ApplicationConstants.LOAD,
					req.getMinLoadRange() * 1.0d, req.getMaxLoadRange() * 1.0d);
			Long fromTimeStamp = 0L;
			Long toTimeStamp = 0L;
			if (!assetLoadDataDTOList.isEmpty()) {

				for (AssetLoadDataDTO assetLoadDataDTO : assetLoadDataDTOList) {
					loadMap.put(assetLoadDataDTO.getDataTimestamp(), assetLoadDataDTO.getFloatValues());
				}

				fromTimeStamp = assetLoadDataDTOList.stream().mapToLong(AssetLoadDataDTO::getDataTimestamp).findFirst()
						.getAsLong();

				toTimeStamp = assetLoadDataDTOList.stream().mapToLong(AssetLoadDataDTO::getDataTimestamp)
						.skip(assetLoadDataDTOList.size() - 1L).reduce((first, second) -> second).getAsLong();

			}
			List<ChartDataArray> chartDataList = new ArrayList<>();
			for (YAxisData data : req.getData()) {
				List<AssetDataDTO> assetDataDTOlist = assetDataRepository.getYaxisChartsData(req.getTenantid(),
						req.getAssetId(), data.getParamId(), data.getMin(), data.getMax(), fromTimeStamp, toTimeStamp);

				if (!assetDataDTOlist.isEmpty()) {
					ChartDataArray cda = new ChartDataArray();

					List<Data> listData = new ArrayList<>();
					for (AssetDataDTO e : assetDataDTOlist) {
						Data data1 = createDataArray(e, loadMap);
						if (!Objects.isNull(data1)) {
							listData.add(data1);
						}

					}
					cda.setData(listData);
					cda.setAssetParamName(data.getParamId());
					cda.setName(data.getName());
					cda.setMin(data.getMin());
					cda.setMax(data.getMax());
					AssetParamDTO2 param = getParamNameById(data.getParamId());
					if (!Objects.isNull(param)) {
						cda.setDisplayName(param.getDisplayName());

					}
					chartDataList.add(cda);

				}

			}
			if (!CollectionUtils.isEmpty(chartDataList)) {
				resp.setChartData(chartDataList);
			}

		}
		return resp;
	}

	private Data createDataArray(AssetDataDTO e, Map<Long, Double> loadMap) {
		Data chartdata = null;
		try {

			if (loadMap.containsKey(e.getDataTimestamp())) {
				chartdata = new Data();
				chartdata.setDate(e.getEpochDate());
				chartdata.setValue(e.getFloatValue());
				chartdata
						.setLoad((double) (loadMap.containsKey(e.getDataTimestamp()) ? loadMap.get(e.getDataTimestamp())
								: 0.0d));

			}
		} catch (Exception e1) {
			LOGGER.error("error in getYaxisChartsData:{}", e1);
		}
		return chartdata;
	}

}
