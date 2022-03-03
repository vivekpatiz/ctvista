package com.danaherdigital.che_hx.asset_management.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class ApplicationConstants.
 */
public class ApplicationConstants {

	/** The Constant someErrorCode. */
	public final static String someErrorCode = "CHE101";



	/** The Constant someErrorCode. */
	public final static String LOGGER_PLACEHOLDER = "{}:{} ,{}:{}";

	/** The Constant CLASSNAME. */
	public static final String CLASSNAME = "Class Name :";

	/** The Constant METHODNAME. */
	public static final String METHODNAME = "Method Name :";

	/** The Constant ASSET_TYPE_ALREADY_EXISTS. */
	public static final String ASSET_TYPE_ALREADY_EXISTS = "Name Already Exists.";

	/** The Constant ASSET_FACILITY. */
	public static final String ASSET_FACILITY = "Facility";

	/** The Constant ASSET_FACILITY. */
	public static final String ASSET_Unit = "Unit";

	/** The Constant ASSET_TYPE_UPDATED. */
	public static final String ASSET_TYPE_UPDATED = "Asset type  edited successfully.";

	/** The Constant REQUESTPARAMETERS. */
	public static final String REQUESTPARAMETERS = "Request Parameters : ";

    /** The Constant ERROR. */
    public static final String ERROR = "Error : ";

    /** The Constant ASSET_TYPE_NOT_FOUND. */
    public static final String ASSET_TYPE_NOT_FOUND = "Asset type not found!";

    public static final String CONDENSOR_EXISTS = "Condenser Name already exist!";

    public static final String TENANT_NOT_FOUND ="Tenant Not found";

    public static final String TENANT_NOT_NULL ="Tenant Id cannot be null";
    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_TYPE_CONDENSER = "Condenser";

    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_CONDENSER_ID = "f6203559-73e2-454e-b059-e9a24e979b6c";

    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_FACILITY_NAME = "Test Station";

    public static final String ASSET_SYSTEM_NAME = "Test System";


    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_TYPE_CONDENSERS = "Condensers";

    /** The Constant ASSET_TYPE_ALREADY_EXISTS. */
	public static final String ASSET_TENANT_NULLS = "Tenant Id cannot be null";


	 /** The Constant ASSET_TENANT_ERROR. */
		public static final String ASSET_NO_TENANT_ERR = "Tenant Not found";

		/** The Constant ASSET_TENANT_ASSET_TYPE. */
		public static final String ASSET_TYPE_NOT_PRESENT = "Asset Type Not found";


		/** The Constant ASSET_TENANT_ASSET_TYPE. */
		public static final String ASSET_NOT_PRESENT = "Asset not found";

		/** The Constant ASSET_TENANT_ASSET_TYPE. */
		public static final String ASSET_CONDENSER_DUPLICATE = "Condenser Name already exist!";

		public static final String ASSET_DESIGN="DESIGN";

		public static final String FACILITY_NAME = "facilityName";

		public static final String SYSTEM_NAME = "systemName";

		public static final String CONDENSER_NAME = "condenserName";

		public static final String PLANT_DATA = "plantData";


		public static final String UNIT_DATA = "unitData";

		public static final String HISTORIAN_MAP = "historianMap";

		public static final String DESCRIPTOR = "descriptor";

		public static final String TAG_NAME = "tagName";


		public static final String ASSET_RAW_HEADER="RAW";

		public static final List<String> KPI_ATTR=new ArrayList<>(Arrays.asList("tr_deviation","ttd_deviation","bp_deviation_overall","production_deviation_overall",
				"mw_dollar_lost_per_day","heat_rate_deviation_overall","est_excess_fuel_burned_overall","est_excess_fuel_dollar_overall","est_excess_co2_emissions_overall"));

		public static final String ASSET_PERCENTAGE_LOAD ="percentage_of_full_load";



		public static final String DESCENDING = "DESCENDING";



}
