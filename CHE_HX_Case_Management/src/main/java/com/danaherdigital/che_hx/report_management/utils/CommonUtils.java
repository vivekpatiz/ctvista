package com.danaherdigital.che_hx.report_management.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danaherdigital.che_hx.report_management.ApiSuccess;


public class CommonUtils {

	private CommonUtils() {}
	public static ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess) {

		return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
	}


	public static Pageable getPageable(int page, int size, String sort, String order) {
		Pageable sorted;
		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)
				&& order.equalsIgnoreCase(ApplicationConstants.DESCENDING)) {
			sorted = PageRequest.of(page, size, Sort.by(sort).descending());
		} else if (!StringUtils.isEmpty(sort)) {
			sorted = PageRequest.of(page, size, Sort.by(sort));
		} else {
			sorted = PageRequest.of(page, size);
		}
		return sorted;
	}
	
	
	public static List<String> getOrderAttr() {
		
		return new ArrayList<>(Arrays.asList("gross_load","condenser_back_pressure_1","condenser_back_pressure_2","condenser_back_pressure_3","condenser_back_pressure_4","condenser_back_pressure_5","condenser_back_pressure_6","condenser_back_pressure_7","condenser_back_pressure_8","boiler_steam_flow_1","boiler_steam_flow_2","boiler_steam_flow_3","boiler_steam_flow_4","boiler_steam_flow_5","boiler_steam_flow_6","boiler_steam_flow_7","boiler_steam_flow_8","boiler_steam_flow_9","boiler_steam_flow_10","boiler_steam_flow_11","boiler_steam_flow_12","condenser_cw_in_temps_1","condenser_cw_in_temps_2","condenser_cw_in_temps_3","condenser_cw_in_temps_4","condenser_cw_in_temps_5","condenser_cw_in_temps_6","condenser_cw_in_temps_7","condenser_cw_in_temps_8","condenser_cw_out_temps_1","condenser_cw_out_temps_2","condenser_cw_out_temps_3","condenser_cw_out_temps_4","condenser_cw_out_temps_5","condenser_cw_out_temps_6","condenser_cw_out_temps_7","condenser_cw_out_temps_8","net_load","gross_heat_rate","net_heat_rate","ambient_temp","humidity","barometric_pressure","condensate_sodium","condensate_cation_conductivity","condensate_do","attemperation_flow_1","attemperation_flow_2","attemperation_flow_3","attemperation_flow_4","attemperation_flow_5","cogen_steam_flow_1","cogen_steam_flow_2","steam_turbine_output_1","steam_turbine_output_2","steam_turbine_output_3","gas_turbine_output_1","gas_turbine_output_2","gas_turbine_output_3","gas_turbine_output_4","gas_turbine_output_5","gas_turbine_output_6","lp_exhaust_steam_temp_1","lp_exhaust_steam_temp_2","lp_exhaust_steam_temp_3","lp_exhaust_steam_temp_4","hotwell_temp_1","hotwell_temp_2","hotwell_temp_3","hotwell_temp_4","condenser_cw_inlet_pressure_1","condenser_cw_inlet_pressure_2","condenser_cw_inlet_pressure_3","condenser_cw_inlet_pressure_4","condenser_cw_outlet_pressure_1","condenser_cw_outlet_pressure_2","condenser_cw_outlet_pressure_3","condenser_cw_outlet_pressure_4","condenser_cw_delta_pressure_1","condenser_cw_delta_pressure_2","condenser_cw_delta_pressure_3","condenser_cw_delta_pressure_4","circ_pump_amps_1","circ_pump_amps_2","circ_pump_amps_3","circ_pump_amps_4","gas_fuel_flow_1","gas_fuel_flow_2","gas_fuel_flow_3","gas_fuel_flow_4","gas_fuel_flow_5","gas_fuel_flow_6","gas_fuel_flow_7","gas_fuel_flow_8","duct_burner_fuel_flow_1","duct_burner_fuel_flow_2","duct_burner_fuel_flow_3","duct_burner_fuel_flow_4","coal_fuel_flow_1","coal_fuel_flow_2","coal_fuel_flow_3","coal_fuel_flow_4","coal_fuel_flow_5","coal_fuel_flow_6","coal_fuel_flow_7","coal_fuel_flow_8","air_removal_1","air_removal_2","air_removal_3","air_removal_4"
));
	}
}
