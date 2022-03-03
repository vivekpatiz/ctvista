package com.danaherdigital.che_hx.report_management.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The Class ApplicationConstants.
 */
public class ApplicationConstants {

	private ApplicationConstants() {}

	/** The Constant someErrorCode. */
	public static final  String SOMEERRORCODE = "CHE101";

	/** The Constant CLASSNAME. */
	public static final String CLASSNAME = "Class Name :";

	/** The Constant METHODNAME. */
	public static final String METHODNAME = "Method Name :";

	/** The Constant ASSET_TYPE_ALREADY_EXISTS. */
	public static final String ASSET_TYPE_ALREADY_EXISTS = "Name Already Exists.";

	/** The Constant ASSET_FACILITY. */
	public static final String ASSET_FACILITY = "Facility";

	public static final String TROUBLE_SHOOTING_GUIDE = "Condenser-Troubleshooting-Guide.pdf";

	/** The Constant ASSET_FACILITY. */
	public static final String ASSET_UNIT = "Unit";

	/** The Constant ASSET_TYPE_UPDATED. */
	public static final String ASSET_TYPE_UPDATED = "Asset type  edited successfully.";

	/** The Constant REQUESTPARAMETERS. */
	public static final String REQUESTPARAMETERS = "Request Parameters : ";

    /** The Constant ERROR. */
    public static final String ERROR = "Error : ";

    /** The Constant ASSET_TYPE_NOT_FOUND. */
    public static final String ASSET_TYPE_NOT_FOUND = "Asset type not found!";

    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_TYPE_CONDENSER = "Condenser";

    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_CONDENSER_ID = "f6203559-73e2-454e-b059-e9a24e979b6c";

    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_FACILITY_NAME = "Test Station";

    /** The Constant ASSET_TYPE_CONDENSER. */
    public static final String ASSET_TYPE_CONDENSERS = "Condensers";

    /** The Constant ASSET_TYPE_ALREADY_EXISTS. */
	public static final String ASSET_TENANT_NULLS = "Tenant Id cannot be null";

	public static final String MAPPING="mapping";

	public static final String FILE_OUTPUT_STREAM="/opt/CHE_HX_Case_Management/out.xlsx";

    public static final String UUID_PATTERN = "^[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{4}-[0-9A-Fa-f]{12}$";

	 /** The Constant ASSET_TENANT_ERROR. */
		public static final String ASSET_NO_TENANT_ERR = "Tenant Not found";

		/** The Constant ASSET_TENANT_ASSET_TYPE. */
		public static final String ASSET_TYPE_NOT_PRESENT = "Asset Type Not found";


		/** The Constant ASSET_TENANT_ASSET_TYPE. */
		public static final String REPORT_NOT_PRESENT = "Report not found";

		public static final String REPORT_PRESENT = "Report name already exists";

		/** The Constant ASSET_TENANT_ASSET_TYPE. */
		public static final String ASSET_CONDENSER_DUPLICATE = "Condenser Name already exist!";

		public static final String ASSET_DESIGN="DESIGN";

		public static final String ASSET_RAW_HEADER="RAW";

		protected static final List<String> KPI_ATTR=new ArrayList<>(Arrays.asList("tr_deviation","ttd_deviation","bp_deviation_overall","production_deviation_overall",
				"mw_dollar_lost_per_day","heat_rate_deviation_overall","est_excess_fuel_burned_overall","est_excess_fuel_dollar_overall","est_excess_co2_emissions_overall"));

		public static final String ASSET_PERCENTAGE_LOAD ="percentage_of_full_load";

		public static final String DESCENDING = "DESCENDING";

		public static final String LOGGER_PLACEHOLDER = "{}:{} ,{}:{}";



}
