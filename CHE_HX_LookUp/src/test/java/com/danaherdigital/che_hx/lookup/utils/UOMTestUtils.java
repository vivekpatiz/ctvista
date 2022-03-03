package com.danaherdigital.che_hx.lookup.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.danaherdigital.che_hx.lookup.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.lookup.dto.RequestDTO;



public class UOMTestUtils {


	public static LookUpReqDTO getLookUpTables() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp7 = new RequestDTO();
		inp7.setFrom("unit");
		inp7.setTableName("lookup");
		inp7.setValue("Sub-Critical (Press <=2400psi)");

		RequestDTO inp8 = new RequestDTO();
		inp8.setFrom("fuel");
		inp8.setTableName("lookup");
		inp8.setValue("Lignite");

		list.add(inp7);
		list.add(inp8);

		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getInvalidLookUpTables() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp7 = new RequestDTO();
		inp7.setFrom("unit1");
		inp7.setTableName("lookup");
		inp7.setValue("Sub-Critical (Press <=2400psi)");

		RequestDTO inp8 = new RequestDTO();
		inp8.setFrom("fuel");
		inp8.setTableName("lookup");
		inp8.setValue("Lignite");

		list.add(inp7);
		list.add(inp8);

		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO createMockInvalidTable() {
		LookUpReqDTO l = new LookUpReqDTO();
		RequestDTO dt = new RequestDTO();
		dt.setTableName(null);
		l.setInput(new ArrayList<>(java.util.Arrays.asList(dt)));
		return l;

	}

	public static LookUpReqDTO createMockEmptyTable() {
		LookUpReqDTO l = new LookUpReqDTO();
		RequestDTO dt = new RequestDTO();
		dt.setTableName("");
		l.setInput(new ArrayList<>(java.util.Arrays.asList(dt)));
		return l;

	}

	public static LookUpReqDTO createMockInvalidFrom() {
		LookUpReqDTO l = new LookUpReqDTO();
		RequestDTO dt = new RequestDTO();
		dt.setTableName("table");
		dt.setFrom(null);
		l.setInput(new ArrayList<>(java.util.Arrays.asList(dt)));
		return l;

	}

	public static LookUpReqDTO createMockEmptyFrom() {
		LookUpReqDTO l = new LookUpReqDTO();
		RequestDTO dt = new RequestDTO();
		dt.setTableName("table");
		dt.setFrom("");
		l.setInput(new ArrayList<>(java.util.Arrays.asList(dt)));
		return l;

	}

	public static LookUpReqDTO createMockInvalidValue() {
		LookUpReqDTO l = new LookUpReqDTO();
		RequestDTO dt = new RequestDTO();
		dt.setTableName("table");
		dt.setFrom("pressure");
		dt.setValue(null);
		l.setInput(new ArrayList<>(java.util.Arrays.asList(dt)));
		return l;

	}

	public static LookUpReqDTO createMockInvalidInput() {
		LookUpReqDTO l = new LookUpReqDTO();
		l.setInput(null);
		return l;

	}

	public static LookUpReqDTO createMockInvalidInput2() {
		LookUpReqDTO l = new LookUpReqDTO();
		List<RequestDTO> list = Collections.emptyList();
		l.setInput(list);
		return l;

	}

	public static LookUpReqDTO getSteamTable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp4 = new RequestDTO();
		inp4.setFrom("pressure");
		inp4.setTableName("steam");
		inp4.setValue(2.8);

		RequestDTO inp5 = new RequestDTO();
		inp5.setFrom("temp");
		inp5.setTableName("steam");
		inp5.setValue(109.7);
		list.add(inp4);
		list.add(inp5);

		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getInvalidSteamTable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp4 = new RequestDTO();
		inp4.setFrom("pressure");
		inp4.setTableName("steam");
		inp4.setValue(2.8);

		RequestDTO inp5 = new RequestDTO();
		inp5.setFrom("temp1");
		inp5.setTableName("steam");
		inp5.setValue(109.7);
		list.add(inp4);
		list.add(inp5);

		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getTubeDataTable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp3 = new RequestDTO();
		inp3.setFrom("bwg");
		inp3.setTableName("tubedata");
		inp3.setValue(22);
		list.add(inp3);
		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getInvalidTubeDataTable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp3 = new RequestDTO();
		inp3.setFrom("bwg1");
		inp3.setTableName("tubedata");
		inp3.setValue(22);
		list.add(inp3);
		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getWcfTable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp6 = new RequestDTO();
		inp6.setFrom("temp");
		inp6.setTableName("wcf");
		inp6.setValue(84);
		list.add(inp6);
		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getWcfTableInvalidFrom() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp6 = new RequestDTO();
		inp6.setFrom("temp2");
		inp6.setTableName("wcf");
		inp6.setValue(84);
		list.add(inp6);
		req.setInput(list);

		return req;
	}

	public static LookUpReqDTO getHEITable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp = new RequestDTO();
		inp.setFrom("bwg");
		inp.setTableName("HEIStandards");
		inp.setValue(22);
		inp.setValue2("Type 316/317 SS");

		RequestDTO inp2 = new RequestDTO();
		inp2.setFrom("dia");
		inp2.setTableName("HEIStandards");
		inp2.setValue(1.250);
		inp2.setValue2(8.0);

		list.add(inp);
		list.add(inp2);
		req.setInput(list);
		return req;
	}

	public static LookUpReqDTO getHEIInvalidTable() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();

		RequestDTO inp = new RequestDTO();
		inp.setFrom("bwg1");
		inp.setTableName("HEIStandards");
		inp.setValue(22);
		inp.setValue2("Type 316/317 SS");

		RequestDTO inp2 = new RequestDTO();
		inp2.setFrom("dia");
		inp2.setTableName("HEIStandards");
		inp2.setValue(1.250);
		inp2.setValue2(8.0);

		list.add(inp);
		list.add(inp2);
		req.setInput(list);
		return req;
	}

	public static LookUpReqDTO getLookUpReq() {
		LookUpReqDTO req = new LookUpReqDTO();
		List<RequestDTO> list = new ArrayList<>();
		RequestDTO inp = new RequestDTO();
		inp.setFrom("bwg");
		inp.setTableName("HEIStandards");
		inp.setValue(22);
		inp.setValue2("Type 316/317 SS");

		RequestDTO inp2 = new RequestDTO();
		inp2.setFrom("dia");
		inp2.setTableName("HEIStandards");
		inp2.setValue(1.250);
		inp2.setValue2(8.0);

		RequestDTO inp3 = new RequestDTO();
		inp3.setFrom("bwg");
		inp3.setTableName("tubedata");
		inp3.setValue(22);

		RequestDTO inp4 = new RequestDTO();
		inp4.setFrom("pressure");
		inp4.setTableName("steam");
		inp4.setValue(2.8);

		RequestDTO inp5 = new RequestDTO();
		inp5.setFrom("temp");
		inp5.setTableName("steam");
		inp5.setValue(109.7);

		RequestDTO inp6 = new RequestDTO();
		inp6.setFrom("temp");
		inp6.setTableName("wcf");
		inp6.setValue(84);

		RequestDTO inp7 = new RequestDTO();
		inp7.setFrom("unit");
		inp7.setTableName("lookup");
		inp7.setValue("Sub-Critical (Press <=2400psi)");

		RequestDTO inp8 = new RequestDTO();
		inp8.setFrom("fuel");
		inp8.setTableName("lookup");
		inp8.setValue("Lignite");

		list.add(inp);
		list.add(inp2);
		list.add(inp3);
		list.add(inp4);
		list.add(inp5);
		list.add(inp6);
		list.add(inp7);
		list.add(inp8);

		req.setInput(list);

		return req;

	}

	public static LookUpReqDTO getLookUpInvalidReq() {
		LookUpReqDTO req = new LookUpReqDTO();
		return req;

	}
}
