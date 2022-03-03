package com.danaherdigital.che_hx.report_management.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import com.danaherdigital.che_hx.report_management.dto.ReportDTO;
import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.dto.UserPreferenceDTO;
import com.danaherdigital.che_hx.report_management.model.DashBoard;
import com.danaherdigital.che_hx.report_management.model.Report;
import com.danaherdigital.che_hx.report_management.model.UserPreferences;

public class TestUtils {


	public static String excelString() {


		return "{" +
				"        \"mapping\": [" +
				"            {" +
				"                \"uom\": \"393\"," +
				"                \"tagDescriptor\": \"inHg(a)\"," +
				"                \"tagName\": \"barometric_pressure\"," +
				"                \"key\": \"barometric_pressure\"" +
				"            }," +
				"            {" +
				"                \"uom\": \"398\"," +
				"                \"tagDescriptor\": \"kpph\"," +
				"                \"tagName\": \"attemperation_flow_1\"," +
				"                \"key\": \"attemperation_flow_1\"" +
				"            }," +
				"            {" +
				"                \"uom\": \"405\"," +
				"                \"tagDescriptor\": \"amps\"," +
				"                \"tagName\": \"circ_pump_amps_1\"," +
				"                \"key\": \"circ_pump_amps_1\"" +
				"            }," +
				"            {" +
				"                \"uom\": \"124\"," +
				"                \"tagDescriptor\": \"�F\"," +
				"                \"tagName\": \"condenser_cw_in_temps_2\"," +
				"                \"key\": \"condenser_cw_in_temps_2\"" +
				"            }" +
				"        ]" +
				"    }";

	}

	public static String mockJsonString()
	{
		return "[\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Gross Load\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"MW\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Condenser Back Pressure #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"inHg(a)\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Boiler Steam Flow #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"kpph\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Attemperation Flow #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"kpph\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Cogen Steam Flow #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"kpph\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Condenser CW In Temps #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"°F\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Condenser CW Out Temps #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"°F\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Condenser CW Inlet Pressure #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"psi\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"Condenser CW Outlet Pressure #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"psi\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"        \"ctVistaTagName\": \"LP Exhaust Steam Temp #1\",\r\n" +
				"        \"historianTagUnitOfMeasure\": \"°F\",\r\n" +
				"        \"historianTagName\": \"xyz\",\r\n" +
				"        \"historianDiscriptor\": \"xyz\",\r\n" +
				"        \"technicalName\": \"abc\"\r\n" +
				"    }\r\n" +
				"]";
	}

	public static Report getReportTemplate() {
		Report report = null;
		{

			report = new Report("12312", 22.33, 44.55, new Date(), new Date(), "1232", "333", "422", new HashMap<>(), 2,
					true,"tenaska","unit1");
		}

		return report;

	}

	public static ReportListDTO getReportListDtoTemplate() {
		ReportListDTO report = null;
		{

			report = new ReportListDTO("12312", new Date(), new Date(),  "422", 2, "422",
					true,"tenaska","unit1","Kevin",new Date());
		}

		return report;

	}

	public static ReportDTO getReportDTOTemplate() {
		ReportDTO report = null;
		{

			report = new ReportDTO("12312", null, 44.55, new Date(), new Date(), "1232", "333", "422", new HashMap<>(), 2,
					true,"tenaska","unit1","","");

		}

		return report;

	}

	public static Report getReportTemplate2() {
		Report report = null;
		{

			report = new Report("123212", 22.33, 44.55, new Date(), new Date(), "1232", "333", "422", new HashMap<>(), 2,
					true,"tenaska","unit1");
		}

		return report;

	}


	public static Optional<UserPreferences> getUserPreferenceTemplate() {
		UserPreferences userPreferenceDTO = null;
		{

			 userPreferenceDTO = new UserPreferences("12312", 22.33, 44.55, new Date(), new Date(), "1232", "333", new HashMap<>(), 2,"Kevin");
		}

		return Optional.of(userPreferenceDTO);

	}


	public static UserPreferenceDTO getUserPreferenceDTOTemplate() {
		UserPreferenceDTO userPreferenceDTO = null;
		{

			 userPreferenceDTO = new UserPreferenceDTO("12312", 22.33, 44.55, new Date(), new Date(), "1232", "333", new HashMap<>(), 2,"Kevin");
		}

		return userPreferenceDTO;

	}

	public static DashBoard createMockDashBoardDO()
	{

		DashBoard d=new DashBoard();
		d.setAssetId("123456");
		d.setAssetType("condenser");
		d.setConfigJson(null);
		d.setId(1L);
		d.setName("kpi overview");
		d.setTenantId("2");
		d.setType("kpi");
		return d;
	}


	public static String createMockJsonData()
	{

		return "{\r\n" +
				"        \"mapping\": [\r\n" +
				"            {\r\n" +
				"                \"uom\": \"393\",\r\n" +
				"                \"tagDescriptor\": \"inHg(a)\",\r\n" +
				"                \"tagName\": \"barometric_pressure\",\r\n" +
				"                \"key\": \"barometric_pressure\"\r\n" +
				"            }\r\n" +
				"        ],\r\n" +
				"        \r\n" +
				"         \"assetId\": \"231212\",\r\n" +
				"                \"assetName\": \"condenserTest\",\r\n" +
				"                \"tenantId\": \"2\"\r\n" +
				"    }";
	}


}
