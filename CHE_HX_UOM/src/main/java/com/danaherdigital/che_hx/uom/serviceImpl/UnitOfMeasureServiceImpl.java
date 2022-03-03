package com.danaherdigital.che_hx.uom.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.uom.dto.UOMReqDTO;
import com.danaherdigital.che_hx.uom.dto.UOMReqData;
import com.danaherdigital.che_hx.uom.dto.UOMRespDTO;
import com.danaherdigital.che_hx.uom.dto.UOMResult;
import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.uom.repository.UnitOfMeasureRepository;
import com.danaherdigital.che_hx.uom.service.IUnitOfMeasureService;
import com.danaherdigital.che_hx.uom.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class UnitOfMeasureServiceImpl.
 */
@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements IUnitOfMeasureService{



	@Autowired
	private UnitOfMeasureRepository unitOfMeasureRepository;

	/**
	 * Gets the UOM conversion data.
	 *
	 * @param req the req
	 * @return the UOM conversion data
	 * @throws ChemtreatException the chemtreat exception
	 */
	@Override
	public UOMRespDTO getUOMConversionData(UOMReqDTO req) throws ChemtreatException {
		UOMRespDTO resp = new UOMRespDTO();
		List<UOMResult> resList = Collections.synchronizedList(new ArrayList<>());

		if (CollectionUtils.isEmpty(req.getUomReqList())) {
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}
		try {
			for (UOMReqData data : req.getUomReqList()) {

				UOMResult res = getUOMData(data);
				resList.add(res);
			}
			resp.setList(resList);
			return resp;
		} catch (ChemtreatException e) {
			log.error("error in UnitOfMeasureServiceImpl.getUOMConversionData->{}", e.getMessage());
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}
	}

	/**
	 * Gets the UOM data.
	 *
	 * @param req the req
	 * @return the UOM data
	 * @throws ChemtreatException the chemtreat exception
	 */
	private UOMResult getUOMData(UOMReqData req) throws ChemtreatException {
		UOMResult calcValue = new UOMResult();

		try {
			if (StringUtils.equalsIgnoreCase(req.getToUom(), ApplicationConstants.BTU_PER_KWHR)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(), ApplicationConstants.MJ_PER_KWHR)) {
					calcValue.setConvertedValue(req.getValue() / 0.001055056);
				}
			} else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.INHGA)
					|| StringUtils.equalsIgnoreCase(req.getToUom(), ApplicationConstants.INHG)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(), ApplicationConstants.KPA)) {
					calcValue.setConvertedValue(req.getValue() /3.386388);
				}
				else if (StringUtils.equalsIgnoreCase(req.getFromUom(), ApplicationConstants.PA)) {
					calcValue.setConvertedValue(req.getValue() /3386.388);
				}
				else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.MMBAR)) {
					calcValue.setConvertedValue(req.getValue() /33.86388);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.BAR)) {
					calcValue.setConvertedValue(req.getValue() /0.033864);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.MMHG)) {
					calcValue.setConvertedValue(req.getValue() /25.4);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.INHGV)) {
					calcValue.setConvertedValue(req.getValue());

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.PSIA)
						|| StringUtils.equalsIgnoreCase(req.getFromUom(), ApplicationConstants.PSIG)) {
					calcValue.setConvertedValue(req.getValue() /0.491154);

				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.KPPH)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.LBS_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 1000);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.MMLBS_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 0.001);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KG_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 453.592);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.TON_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 0.5);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.LB_PER_SEC)) {
					calcValue.setConvertedValue(req.getValue() / 277.778);

				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.PSI)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(), ApplicationConstants.KPA)) {
					calcValue.setConvertedValue(req.getValue() /6.894757);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.INH2O)) {
					calcValue.setConvertedValue(req.getValue() / 27.68071);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.FTH2O)) {
					calcValue.setConvertedValue(req.getValue() / 2.306726);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.BAR)) {
					calcValue.setConvertedValue(req.getValue() /0.068948);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.PSIG)) {
					calcValue.setConvertedValue(req.getValue());

				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.MW)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KW)) {
					calcValue.setConvertedValue(req.getValue() / 1000);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.W)) {
					calcValue.setConvertedValue(req.getValue() / 1000000);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.J_PER_SEC)) {
					calcValue.setConvertedValue(req.getValue() / 1000000);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.GW)) {
					calcValue.setConvertedValue(req.getValue() / 0.001);

				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.SCFM)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.MMBTU_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 0.00104);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.FT3_PER_MIN)) {
					calcValue.setConvertedValue(req.getValue() / 1);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.FT3_PER_SEC)) {
					calcValue.setConvertedValue(req.getValue() / 0.016666667);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.FT3_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 60);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_MIN )
						||StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_M)) {
					calcValue.setConvertedValue(req.getValue() / 0.28317);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_SEC)
						||StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_S)) {
					calcValue.setConvertedValue(req.getValue() / 0.0047195);

				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_HR)
						||StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_H)) {
					calcValue.setConvertedValue(req.getValue() / 16.9902);

				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.F)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.C)) {
					calcValue.setConvertedValue(req.getValue() * (-17.2222));
				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.PPB)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.PPT)) {
					calcValue.setConvertedValue(req.getValue() / 1000);
				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.TPH)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.LBS_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 2000);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KPPH)) {
					calcValue.setConvertedValue(req.getValue() / 2);
				}

				else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KLBS_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 2);
				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.BTU_PER_LB)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KJ_PER_KG)) {
					calcValue.setConvertedValue(req.getValue() / 2.234446);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.J_PER_G)) {
					calcValue.setConvertedValue(req.getValue() / 2.324446);
				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.FT3_PER_LB)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_KG)) {
					calcValue.setConvertedValue(req.getValue() / 16.01846);
				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.BTU_PER_HR)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.MMBTU_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 0.000001);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.W_PER_HR)) {
					calcValue.setConvertedValue(req.getValue() / 0.29307107);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.BTU_PER_LB_DOT_F)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.J_PER_KG_DOT_K)) {
					calcValue.setConvertedValue(req.getValue() / 4186.798);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KJ_KG_DOT_K)) {
					calcValue.setConvertedValue(req.getValue() / 4.186798);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.BTU_HR_DOT_F_DOT_FT)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.W_PER_M_DOT_K)) {
					calcValue.setConvertedValue(req.getValue() / 1.730736);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.LB_ER_F_DOT_HR)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.CP)) {
					calcValue.setConvertedValue(req.getValue() / 0.413379);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.N_DOT_S_PER_M2)) {
					calcValue.setConvertedValue(req.getValue() / 0.000413);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.BTU_PER_HR_DOT_F_DOT_FT2)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.W_PER_M2_DOT_K)) {
					calcValue.setConvertedValue(req.getValue() / 5.678269);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KW_PER_M2_DOT_K)) {
					calcValue.setConvertedValue(req.getValue() / 0.005678);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.GPM)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KG_PER_SEC)) {
					calcValue.setConvertedValue(req.getValue() / 0.0632911392405063);
				}

			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.GPH)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.GPM)) {
					calcValue.setConvertedValue(req.getValue() / 0.016666667);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.L_PER_H)) {
					calcValue.setConvertedValue(req.getValue() / 3.7854);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_SEC)
						||StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_S)) {
					calcValue.setConvertedValue(req.getValue() / 0.00000105);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_MIN)
						||StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_M)) {
					calcValue.setConvertedValue(req.getValue() / 0.00006312);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_HR)
						||StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3_PER_H)) {
					calcValue.setConvertedValue(req.getValue() / 0.0037872);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.FT2)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M2)) {
					calcValue.setConvertedValue(req.getValue() / 0.09290304);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.IN2)) {
					calcValue.setConvertedValue(req.getValue() / 144);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.FT)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.IN)) {
					calcValue.setConvertedValue(req.getValue() / 12);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M)) {
					calcValue.setConvertedValue(req.getValue() / 0.3048);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.MM)) {
					calcValue.setConvertedValue(req.getValue() / 304.8);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.LBS)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.KG)) {
					calcValue.setConvertedValue(req.getValue() / 0.45359237);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.TON)) {
					calcValue.setConvertedValue(req.getValue() / 0.0005);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.FT_PER_SEC)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M_PER_MIN)) {
					calcValue.setConvertedValue(req.getValue() / 18.288);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M_PER_SEC)) {
					calcValue.setConvertedValue(req.getValue() / 0.3048);
				}
			}

			else if (StringUtils.equalsIgnoreCase(req.getToUom(),ApplicationConstants.FT3)) {
				if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.M3)) {
					calcValue.setConvertedValue(req.getValue() / 0.0283);
				} else if (StringUtils.equalsIgnoreCase(req.getFromUom(),ApplicationConstants.GAL)) {
					calcValue.setConvertedValue(req.getValue() / 7.48);
				}
			}

			calcValue.setConvertedValue(String.format("%f",calcValue.getConvertedValue()));
			calcValue.setInput(req);
			return calcValue;
		} catch (Exception e) {
			log.error("error in UnitOfMeasureServiceImpl.getUOMData->{}", e.getMessage());
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}

	}

	@Override
	public List<UOMdataDTO> getUOMList() {

		return unitOfMeasureRepository.getAllUOM();
	}


}
