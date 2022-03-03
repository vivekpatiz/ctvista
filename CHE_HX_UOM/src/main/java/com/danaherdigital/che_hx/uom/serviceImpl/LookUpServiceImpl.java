package com.danaherdigital.che_hx.uom.serviceimpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.uom.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.uom.dto.RequestDTO;
import com.danaherdigital.che_hx.uom.dto.ResponseDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.uom.model.HEIStdCFactor;
import com.danaherdigital.che_hx.uom.model.HEIStdCoefficients;
import com.danaherdigital.che_hx.uom.model.LookUpFuel;
import com.danaherdigital.che_hx.uom.model.LookUpUnit;
import com.danaherdigital.che_hx.uom.model.Steam;
import com.danaherdigital.che_hx.uom.model.TubeData;
import com.danaherdigital.che_hx.uom.model.WaterCFactor;
import com.danaherdigital.che_hx.uom.repository.HEIStdCFactorRepository;
import com.danaherdigital.che_hx.uom.repository.HEIStdCoefficientsRepository;
import com.danaherdigital.che_hx.uom.repository.LookUpFuelRepository;
import com.danaherdigital.che_hx.uom.repository.LookUpUnitRepository;
import com.danaherdigital.che_hx.uom.repository.SteamRepository;
import com.danaherdigital.che_hx.uom.repository.TubeDataRepository;
import com.danaherdigital.che_hx.uom.repository.WaterCFactorRepository;
import com.danaherdigital.che_hx.uom.service.ILookUpService;
import com.danaherdigital.che_hx.uom.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class LookUpServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j
public class LookUpServiceImpl implements ILookUpService {

	/** The look up unit repository. */
	@Autowired
	LookUpUnitRepository lookUpUnitRepository;

	/** The look up fuel repository. */
	@Autowired
	LookUpFuelRepository lookUpFuelRepository;

	/** The steam repository. */
	@Autowired
	SteamRepository steamRepository;

	/** The tube data repository. */
	@Autowired
	TubeDataRepository tubeDataRepository;

	/** The hei std C factor repository. */
	@Autowired
	HEIStdCFactorRepository heiStdCFactorRepository;

	/** The hei std coefficients repository. */
	@Autowired
	HEIStdCoefficientsRepository heiStdCoefficientsRepository;

	/** The water C factor repository. */
	@Autowired
	WaterCFactorRepository waterCFactorRepository;

	/**
	 * Gets the look up data.
	 *
	 * @param req the req
	 * @return the look up data
	 * @throws ChemtreatException the chemtreat exception
	 */
	@Override
	public List<ResponseDTO> getLookUpData(LookUpReqDTO req) throws ChemtreatException {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getLookUpData");
		if (Objects.isNull(req) || Objects.isNull(req.getInput())
				|| CollectionUtils.isEmpty(req.getInput())) {
			return Collections.emptyList();
		}

		List<ResponseDTO> response = new ArrayList<>();
		ResponseDTO res = null;
		try {
			for (RequestDTO inp : req.getInput()) {
				if(Objects.isNull(inp.getTableName())||inp.getTableName().isEmpty()
						||Objects.isNull(inp.getFrom())||inp.getFrom().isEmpty()
								||Objects.isNull(inp.getValue()))
				{
					throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
				}
				res = new ResponseDTO();
				Map<String, Double> calValues = new HashMap<>();
				if (StringUtils.equalsIgnoreCase(inp.getTableName(), ApplicationConstants.LOOK_UP)) {
					calValues = findLookUp(inp);
				} else if (StringUtils.equalsIgnoreCase(inp.getTableName(), ApplicationConstants.STEAM)) {
					calValues = findSteam(inp);
				} else if (StringUtils.equalsIgnoreCase(inp.getTableName(), ApplicationConstants.TUBE_DATA)) {
					calValues = findTubeData(inp);
				} else if (StringUtils.equalsIgnoreCase(inp.getTableName(), ApplicationConstants.HEI_STANDARDS)) {
					calValues = findHEIStandards(inp);
				} else if (StringUtils.equalsIgnoreCase(inp.getTableName(), ApplicationConstants.WATER_CORRECTION_FACTOR)) {
					calValues = findWaterCorrectionFactor(inp);
				}

				res.setReq(inp);
				res.setCalculatedValues(calValues);
				response.add(res);

			}
		} catch (Exception e) {
			log.error("error in LookUpServiceImpl.getLookUpData->{}",e);
		}
		return response;

	}

	/**
	 * Find water correction factor.
	 *
	 * @param inp the inp
	 * @return the map
	 */
	private Map<String, Double> findWaterCorrectionFactor(RequestDTO inp) {
		Map<String, Double> map = new HashMap<>();

		double cFactor=0;
		WaterCFactor wcf = null;
		if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.STEAM_TEMP)) {

			wcf = waterCFactorRepository.findByTemp(Integer.parseInt(inp.getValue().toString()));
			if (!Objects.isNull(wcf)) {
				cFactor=wcf.getFW();

			}
			map.put(ApplicationConstants.CORRECTION_FACTOR, cFactor);


		}
		return map;

	}

	/**
	 * Find HEI standards.
	 *
	 * @param inp the inp
	 * @return the map
	 */
	private Map<String, Double> findHEIStandards(RequestDTO inp) {
		Map<String, Double> map = new HashMap<>();
log.info("req input->{}",inp);
		HEIStdCoefficients stdCoeff = null;
		HEIStdCFactor stdCFactor = null;
		double coeff=0;
		double cFactor=0;
		if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.DIA)) {
			stdCoeff = heiStdCoefficientsRepository.findByCoeff(Double.parseDouble(inp.getValue().toString()),
					Double.parseDouble(inp.getValue2().toString()));

			if (!Objects.isNull(stdCoeff)) {
				coeff= stdCoeff.getHtTxCoef();

			}
			map.put(ApplicationConstants.COEFFICIENT, coeff);
		} else if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.BWG)) {
			stdCFactor = heiStdCFactorRepository.findByCFactor(Integer.parseInt(inp.getValue().toString()),
					inp.getValue2().toString());

			if (!Objects.isNull(stdCFactor)) {
				cFactor=stdCFactor.getCorrectionFactor();

			}
			map.put(ApplicationConstants.C_FACTOR, cFactor);
		}

		return map;
	}

	/**
	 * Find tube data.
	 *
	 * @param inp the inp
	 * @return the map
	 */
	private Map<String, Double> findTubeData(RequestDTO inp) {
		Map<String, Double> map = new HashMap<>();
		TubeData tubeData = null;
		double thickness=0;
		double tubeIdInches=0;
		double tubeOdInches=0;
		if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.BWG)) {

			tubeData = tubeDataRepository.findByBwg(Integer.parseInt(inp.getValue().toString()));
			if (!Objects.isNull(tubeData)) {
				thickness=tubeData.getWallThicknessInches();
				tubeIdInches=tubeData.getTubeIdInches();
				tubeOdInches=tubeData.getTubeOdInches();
			}
			map.put(ApplicationConstants.WALL_THCKNESS, thickness);
			map.put(ApplicationConstants.TUBE_ID_INCHES, tubeIdInches);
			map.put(ApplicationConstants.TUBE_OD_INCHES, tubeOdInches);

		}
		return map;
	}

	/**
	 * Find steam.
	 *
	 * @param inp the inp
	 * @return the map
	 */
	private Map<String, Double> findSteam(RequestDTO inp) {
		Map<String, Double> map = new HashMap<>();
		double enthalpy=0;
		double temp=0;
		double totalSteam=0;
		double pressure=0;

		Steam steamData = null;
		if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.PRESSURE)) {
		double round=Double.parseDouble(inp.getValue().toString());
			steamData = steamRepository.findByPressure(round);
			if (!Objects.isNull(steamData)) {
				enthalpy=steamData.getEnthalpy();
				temp=steamData.getTemp();
				totalSteam=steamData.getTotalSteam();
			}
			map.put(ApplicationConstants.HEAT_ENTHALPY, enthalpy);
			map.put(ApplicationConstants.STEAM_TEMP, temp);
			map.put(ApplicationConstants.TOTAL_STEAM, totalSteam);
		}

		else if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.STEAM_TEMP)) {

			steamData = steamRepository.findByTemp(Double.parseDouble(inp.getValue().toString()));
			if (!Objects.isNull(steamData)) {
				enthalpy=steamData.getEnthalpy();
				pressure=steamData.getPressureInHg();
				totalSteam=steamData.getTotalSteam();

			}

			map.put(ApplicationConstants.PRESSURE, pressure);
			map.put(ApplicationConstants.HEAT_ENTHALPY, enthalpy);
			map.put(ApplicationConstants.TOTAL_STEAM,totalSteam);

		}
		return map;
	}

	/**
	 * Find look up.
	 *
	 * @param inp the inp
	 * @return the map
	 */
	private Map<String, Double> findLookUp(RequestDTO inp) {
		Map<String, Double> map = new HashMap<>();
		double fuelCostPerUnit=0;
		double fuelCostPerMmbtus=0;
		double co2Emission=0;
		double heatRate=0;
		double penalty=0;
		if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.LOOK_UP_FUEL)) {
			LookUpFuel lookUpFuel = lookUpFuelRepository.findByFuel(inp.getValue().toString());
			if (!Objects.isNull(lookUpFuel)) {
				fuelCostPerUnit=lookUpFuel.getFuelCost();
				fuelCostPerMmbtus= lookUpFuel.getFuelCostPer10Pw6Btu();
				co2Emission=lookUpFuel.getCo2LbPer10Pw6Btu();
			}
			map.put(ApplicationConstants.COST_PER_UNIT, fuelCostPerUnit);
			map.put(ApplicationConstants.COST_PER_MMBTUS, fuelCostPerMmbtus);
			map.put(ApplicationConstants.CO2_EMISSION, co2Emission);
		} else if (StringUtils.equalsIgnoreCase(inp.getFrom(), ApplicationConstants.LOOK_UP_UNIT)) {
			LookUpUnit lookUpUnit = lookUpUnitRepository.findByTypeOfUnit(inp.getValue().toString());
			if (!Objects.isNull(lookUpUnit)) {
				heatRate= lookUpUnit.getExpectedHeatRate();
				penalty=lookUpUnit.getEpriHrPenalty();
			}
			map.put(ApplicationConstants.HEAT_RATE, heatRate);
			map.put(ApplicationConstants.PENALTY, penalty);
		}
		return map;
	}

}
