package com.danaherdigital.che_hx.uom.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.danaherdigital.che_hx.uom.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.uom.dto.RequestDTO;
import com.danaherdigital.che_hx.uom.dto.UOMReqDTO;
import com.danaherdigital.che_hx.uom.dto.UOMReqData;
import com.danaherdigital.che_hx.uom.dto.UOMResult;
import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.model.UnitOfMeasure;

public class UOMTestUtils {

	public static UOMReqDTO getReqTemplate() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.KPA);
		input.setToUom(ApplicationConstants.INHGA);
		input.setValue(234.54);

		UOMReqData input2 = new UOMReqData();
		input2.setFromUom(ApplicationConstants.J_PER_KG_DOT_K);
		input2.setToUom(ApplicationConstants.BTU_PER_LB_DOT_F);
		input2.setValue(21.43);

		UOMReqData input3 = new UOMReqData();
		input3.setFromUom(ApplicationConstants.MMBAR);
		input3.setToUom(ApplicationConstants.INHGA);
		input3.setValue(21.43);

		UOMReqData input4 = new UOMReqData();
		input4.setFromUom(ApplicationConstants.BAR);
		input4.setToUom(ApplicationConstants.INHGA);
		input4.setValue(21.43);

		UOMReqData input5 = new UOMReqData();
		input5.setFromUom(ApplicationConstants.MMHG);
		input5.setToUom(ApplicationConstants.INHGA);
		input5.setValue(21.43);

		UOMReqData input6 = new UOMReqData();
		input6.setFromUom(ApplicationConstants.PSI);
		input6.setToUom(ApplicationConstants.INHGA);
		input6.setValue(21.43);

		UOMReqData input7 = new UOMReqData();
		input7.setFromUom(ApplicationConstants.INHGV);
		input7.setToUom(ApplicationConstants.INHGA);
		input7.setValue(21.43);

		UOMReqData input8 = new UOMReqData();
		input2.setFromUom(ApplicationConstants.LBS_PER_HR);
		input8.setToUom(ApplicationConstants.KPPH);
		input8.setValue(21.43);

		UOMReqData input9 = new UOMReqData();
		input9.setFromUom(ApplicationConstants.MMLBS_PER_HR);
		input9.setToUom(ApplicationConstants.KPPH);
		input9.setValue(21.43);

		UOMReqData input10 = new UOMReqData();
		input10.setFromUom(ApplicationConstants.KG_PER_HR);
		input10.setToUom(ApplicationConstants.KPPH);
		input10.setValue(21.43);

		UOMReqData input11 = new UOMReqData();
		input11.setFromUom(ApplicationConstants.TON_PER_HR);
		input11.setToUom(ApplicationConstants.KPPH);
		input11.setValue(21.43);

		UOMReqData input12 = new UOMReqData();
		input12.setFromUom(ApplicationConstants.LB_PER_SEC);
		input12.setToUom(ApplicationConstants.KPPH);
		input12.setValue(21.43);

		UOMReqData input13 = new UOMReqData();
		input13.setFromUom(ApplicationConstants.KPA);
		input13.setToUom(ApplicationConstants.PSI);
		input13.setValue(21.43);

		UOMReqData input14 = new UOMReqData();
		input14.setFromUom(ApplicationConstants.INH2O);
		input14.setToUom(ApplicationConstants.PSI);
		input14.setValue(21.43);

		UOMReqData input15 = new UOMReqData();
		input15.setFromUom(ApplicationConstants.FTH2O);
		input15.setToUom(ApplicationConstants.PSI);
		input15.setValue(21.43);

		UOMReqData input16 = new UOMReqData();
		input16.setFromUom(ApplicationConstants.BAR);
		input16.setToUom(ApplicationConstants.PSI);
		input16.setValue(21.43);

		UOMReqData input17 = new UOMReqData();
		input17.setFromUom(ApplicationConstants.PSIG);
		input17.setToUom(ApplicationConstants.PSI);
		input17.setValue(21.43);

		UOMReqData input18 = new UOMReqData();
		input18.setFromUom(ApplicationConstants.KW);
		input18.setToUom(ApplicationConstants.MW);
		input18.setValue(21.43);

		UOMReqData input19 = new UOMReqData();
		input19.setFromUom(ApplicationConstants.W);
		input19.setToUom(ApplicationConstants.MW);
		input19.setValue(21.43);

		UOMReqData input20 = new UOMReqData();
		input20.setFromUom(ApplicationConstants.J_PER_SEC);
		input20.setToUom(ApplicationConstants.MW);
		input20.setValue(21.43);

		UOMReqData input21 = new UOMReqData();
		input21.setFromUom(ApplicationConstants.GW);
		input21.setToUom(ApplicationConstants.MW);
		input21.setValue(21.43);

		UOMReqData input22 = new UOMReqData();
		input22.setFromUom(ApplicationConstants.MMBTU_PER_HR);
		input22.setToUom(ApplicationConstants.SCFM);
		input22.setValue(21.43);
		UOMReqData input23 = new UOMReqData();
		input23.setFromUom(ApplicationConstants.FT3_PER_MIN);
		input23.setToUom(ApplicationConstants.SCFM);
		input23.setValue(21.43);
		UOMReqData input24 = new UOMReqData();
		input24.setFromUom(ApplicationConstants.FT3_PER_SEC);
		input24.setToUom(ApplicationConstants.SCFM);
		input24.setValue(21.43);
		UOMReqData input25 = new UOMReqData();
		input25.setFromUom(ApplicationConstants.FT3_PER_HR);
		input25.setToUom(ApplicationConstants.SCFM);
		input25.setValue(21.43);
		UOMReqData input26 = new UOMReqData();
		input26.setFromUom(ApplicationConstants.M3_PER_MIN);
		input26.setToUom(ApplicationConstants.SCFM);
		input26.setValue(21.43);
		UOMReqData input27 = new UOMReqData();
		input27.setFromUom(ApplicationConstants.M3_PER_SEC);
		input27.setToUom(ApplicationConstants.SCFM);
		input27.setValue(21.43);
		UOMReqData input28 = new UOMReqData();
		input28.setFromUom(ApplicationConstants.M3_PER_HR);
		input28.setToUom(ApplicationConstants.SCFM);
		input28.setValue(21.43);

		UOMReqData input29 = new UOMReqData();
		input29.setFromUom(ApplicationConstants.C);
		input29.setToUom(ApplicationConstants.F);
		input29.setValue(21.43);

		UOMReqData input30 = new UOMReqData();
		input30.setFromUom(ApplicationConstants.PPT);
		input30.setToUom(ApplicationConstants.PPB);
		input30.setValue(21.43);

		UOMReqData input31 = new UOMReqData();
		input31.setFromUom(ApplicationConstants.LBS_PER_HR);
		input31.setToUom(ApplicationConstants.TPH);
		input31.setValue(21.43);

		UOMReqData input32 = new UOMReqData();
		input32.setFromUom(ApplicationConstants.KPPH);
		input32.setToUom(ApplicationConstants.TPH);
		input32.setValue(21.43);

		UOMReqData input33 = new UOMReqData();
		input33.setFromUom(ApplicationConstants.KLBS_PER_HR);
		input33.setToUom(ApplicationConstants.TPH);
		input33.setValue(21.43);

		UOMReqData input34 = new UOMReqData();
		input34.setFromUom(ApplicationConstants.KJ_PER_KG);
		input34.setToUom(ApplicationConstants.BTU_PER_LB);
		input34.setValue(21.43);

		UOMReqData input35 = new UOMReqData();
		input35.setFromUom(ApplicationConstants.J_PER_G);
		input35.setToUom(ApplicationConstants.BTU_PER_LB);
		input35.setValue(21.43);

		UOMReqData input36 = new UOMReqData();
		input36.setFromUom(ApplicationConstants.M3_PER_KG);
		input36.setToUom(ApplicationConstants.FT3_PER_LB);
		input36.setValue(21.43);

		UOMReqData input37 = new UOMReqData();
		input37.setFromUom(ApplicationConstants.MMBTU_PER_HR);
		input37.setToUom(ApplicationConstants.BTU_PER_HR);
		input37.setValue(21.43);

		UOMReqData input38 = new UOMReqData();
		input38.setFromUom(ApplicationConstants.W_PER_HR);
		input38.setToUom(ApplicationConstants.BTU_PER_HR);
		input38.setValue(21.43);

		UOMReqData input39 = new UOMReqData();
		input39.setFromUom(ApplicationConstants.J_PER_KG_DOT_K);
		input39.setToUom(ApplicationConstants.BTU_PER_LB_DOT_F);
		input39.setValue(21.43);

		UOMReqData input40 = new UOMReqData();
		input40.setFromUom(ApplicationConstants.KJ_KG_DOT_K);
		input40.setToUom(ApplicationConstants.BTU_PER_LB_DOT_F);
		input40.setValue(21.43);

		UOMReqData input41 = new UOMReqData();
		input41.setFromUom(ApplicationConstants.W_PER_M_DOT_K);
		input41.setToUom(ApplicationConstants.BTU_HR_DOT_F_DOT_FT);
		input41.setValue(21.43);

		UOMReqData input42 = new UOMReqData();
		input42.setFromUom(ApplicationConstants.CP);
		input42.setToUom(ApplicationConstants.LB_ER_F_DOT_HR);
		input42.setValue(21.43);

		UOMReqData input43 = new UOMReqData();
		input43.setFromUom(ApplicationConstants.N_DOT_S_PER_M2);
		input43.setToUom(ApplicationConstants.LB_ER_F_DOT_HR);
		input43.setValue(21.43);

		UOMReqData input44 = new UOMReqData();
		input44.setFromUom(ApplicationConstants.W_PER_M2_DOT_K);
		input44.setToUom(ApplicationConstants.BTU_PER_HR_DOT_F_DOT_FT2);
		input44.setValue(21.43);

		UOMReqData input45 = new UOMReqData();
		input45.setFromUom(ApplicationConstants.KW_PER_M2_DOT_K);
		input45.setToUom(ApplicationConstants.BTU_PER_HR_DOT_F_DOT_FT2);
		input45.setValue(21.43);

		UOMReqData input46 = new UOMReqData();
		input46.setFromUom(ApplicationConstants.KG_PER_SEC);
		input46.setToUom(ApplicationConstants.GPM);
		input46.setValue(21.43);

		UOMReqData input47 = new UOMReqData();
		input47.setFromUom(ApplicationConstants.L_PER_H);
		input47.setToUom(ApplicationConstants.GPH);
		input47.setValue(21.43);

		UOMReqData input48 = new UOMReqData();
		input48.setFromUom(ApplicationConstants.M3_PER_SEC);
		input48.setToUom(ApplicationConstants.GPH);
		input48.setValue(21.43);

		UOMReqData input49 = new UOMReqData();
		input49.setFromUom(ApplicationConstants.M3_PER_MIN);
		input49.setToUom(ApplicationConstants.GPH);
		input49.setValue(21.43);

		UOMReqData input50 = new UOMReqData();
		input50.setFromUom(ApplicationConstants.M3_PER_HR);
		input50.setToUom(ApplicationConstants.GPH);
		input50.setValue(21.43);

		UOMReqData input51 = new UOMReqData();
		input51.setFromUom(ApplicationConstants.M2);
		input51.setToUom(ApplicationConstants.FT2);
		input51.setValue(21.43);

		UOMReqData input52 = new UOMReqData();
		input52.setFromUom(ApplicationConstants.IN2);
		input52.setToUom(ApplicationConstants.FT2);
		input52.setValue(21.43);

		UOMReqData input53 = new UOMReqData();
		input53.setFromUom(ApplicationConstants.N_DOT_S_PER_M2);
		input53.setToUom(ApplicationConstants.FT2);
		input53.setValue(21.43);

		UOMReqData input54 = new UOMReqData();
		input54.setFromUom(ApplicationConstants.IN);
		input54.setToUom(ApplicationConstants.FT);
		input54.setValue(21.43);

		UOMReqData input55 = new UOMReqData();
		input55.setFromUom(ApplicationConstants.M);
		input55.setToUom(ApplicationConstants.FT);
		input55.setValue(21.43);

		UOMReqData input56 = new UOMReqData();
		input56.setFromUom(ApplicationConstants.MM);
		input56.setToUom(ApplicationConstants.FT);
		input56.setValue(21.43);

		UOMReqData input57 = new UOMReqData();
		input57.setFromUom(ApplicationConstants.KG);
		input57.setToUom(ApplicationConstants.LBS);
		input57.setValue(21.43);

		UOMReqData input58 = new UOMReqData();
		input58.setFromUom(ApplicationConstants.TON);
		input58.setToUom(ApplicationConstants.LBS);
		input58.setValue(21.43);

		UOMReqData input59 = new UOMReqData();
		input59.setFromUom(ApplicationConstants.M_PER_MIN);
		input59.setToUom(ApplicationConstants.FT_PER_SEC);
		input59.setValue(21.43);

		UOMReqData input60 = new UOMReqData();
		input60.setFromUom(ApplicationConstants.M_PER_SEC);
		input60.setToUom(ApplicationConstants.FT_PER_SEC);
		input60.setValue(21.43);

		UOMReqData input61 = new UOMReqData();
		input61.setFromUom(ApplicationConstants.M3);
		input61.setToUom(ApplicationConstants.FT3);
		input61.setValue(21.43);

		UOMReqData input62 = new UOMReqData();
		input62.setFromUom(ApplicationConstants.GAL);
		input62.setToUom(ApplicationConstants.FT3);
		input62.setValue(21.43);

		UOMReqData input63 = new UOMReqData();
		input63.setFromUom(ApplicationConstants.MJ_PER_KWHR);
		input63.setToUom(ApplicationConstants.BTU_PER_KWHR);
		input63.setValue(21.43);

		UOMReqData input64 = new UOMReqData();
		input64.setFromUom(ApplicationConstants.PA);
		input64.setToUom(ApplicationConstants.INHGA);
		input64.setValue(21.43);

		UOMReqData input65 = new UOMReqData();
		input65.setFromUom(ApplicationConstants.PSIA);
		input65.setToUom(ApplicationConstants.INHGA);
		input65.setValue(21.43);

		UOMReqData input66 = new UOMReqData();
		input66.setFromUom(ApplicationConstants.PSIG);
		input66.setToUom(ApplicationConstants.INHGA);
		input66.setValue(21.43);

		UOMReqData input67 = new UOMReqData();
		input67.setFromUom(ApplicationConstants.LBS_PER_HR);
		input67.setToUom(ApplicationConstants.KPPH);
		input67.setValue(21.43);

		UOMReqData input68 = new UOMReqData();
		input68.setFromUom(ApplicationConstants.M3_PER_HR);
		input68.setToUom(ApplicationConstants.KPPH);
		input68.setValue(21.43);

		UOMReqData input69 = new UOMReqData();
		input69.setFromUom(ApplicationConstants.M3_PER_H);
		input69.setToUom(ApplicationConstants.KPPH);
		input69.setValue(21.43);

		UOMReqData input70 = new UOMReqData();
		input70.setFromUom(ApplicationConstants.GPM);
		input70.setToUom(ApplicationConstants.GPH);
		input70.setValue(21.43);

		UOMReqData input71 = new UOMReqData();
		input71.setFromUom(ApplicationConstants.M3_PER_HR);
		input71.setToUom(ApplicationConstants.GPH);
		input71.setValue(21.43);

		UOMReqData input72 = new UOMReqData();
		input72.setFromUom(ApplicationConstants.M3_PER_H);
		input72.setToUom(ApplicationConstants.GPH);
		input72.setValue(21.43);

		list.add(input63);
		list.add(input);
		list.add(input2);
		list.add(input3);
		list.add(input4);
		list.add(input5);
		list.add(input6);
		list.add(input7);
		list.add(input8);
		list.add(input9);
		list.add(input10);
		list.add(input11);
		list.add(input12);
		list.add(input13);
		list.add(input14);
		list.add(input15);
		list.add(input16);
		list.add(input17);
		list.add(input18);
		list.add(input19);
		list.add(input20);
		list.add(input21);
		list.add(input22);
		list.add(input23);
		list.add(input24);
		list.add(input25);
		list.add(input26);
		list.add(input27);
		list.add(input28);
		list.add(input29);
		list.add(input30);
		list.add(input31);
		list.add(input32);
		list.add(input33);
		list.add(input34);
		list.add(input35);
		list.add(input36);
		list.add(input37);
		list.add(input38);
		list.add(input39);
		list.add(input40);
		list.add(input41);
		list.add(input42);
		list.add(input43);
		list.add(input44);
		list.add(input45);
		list.add(input46);
		list.add(input47);
		list.add(input48);
		list.add(input49);
		list.add(input50);
		list.add(input51);
		list.add(input52);
		list.add(input53);
		list.add(input54);
		list.add(input55);
		list.add(input56);
		list.add(input57);
		list.add(input58);
		list.add(input59);
		list.add(input60);
		list.add(input61);
		list.add(input62);
		list.add(input63);
		list.add(input64);
		list.add(input65);
		list.add(input66);
		list.add(input67);
		list.add(input68);
		list.add(input69);
		list.add(input70);
		list.add(input71);
		list.add(input72);

		req.setUomReqList(list);

		return req;

	}

	public static UOMReqDTO getReqTemplate1() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.BTU_PER_KWHR);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate2() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.INHG);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate3() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.PSI);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate4() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.MW);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate5() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.M3_PER_M);
		input.setToUom(ApplicationConstants.SCFM);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate6() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.M3_PER_S);
		input.setToUom(ApplicationConstants.SCFM);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate7() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.M3_PER_H);
		input.setToUom(ApplicationConstants.SCFM);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate8() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.SCFM);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate9() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.F);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate10() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.PPB);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate11() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.TPH);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate12() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.BTU_PER_LB);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate13() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.FT3_PER_LB);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate14() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.BTU_PER_HR);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate15() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.BTU_HR_DOT_F_DOT_FT);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate16() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.LB_ER_F_DOT_HR);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate17() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.BTU_PER_HR_DOT_F_DOT_FT2);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate18() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.GPM);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate19() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("m3/s");
		input.setToUom(ApplicationConstants.GPH);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate20() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.M3_PER_M);
		input.setToUom(ApplicationConstants.GPH);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate21() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.M3_PER_H);
		input.setToUom(ApplicationConstants.GPH);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate22() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("m3/s");
		input.setToUom(ApplicationConstants.FT);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate23() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("m3/s");
		input.setToUom(ApplicationConstants.LBS);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate24() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("m3/s");
		input.setToUom(ApplicationConstants.FT_PER_SEC);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate25() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("m3/s");
		input.setToUom(ApplicationConstants.FT3);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate26() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("m3/s");
		input.setToUom("abc");
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getReqTemplate27() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom("abc");
		input.setToUom(ApplicationConstants.GPH);
		input.setValue(234.54);
		list.add(input);
		req.setUomReqList(list);

		return req;
	}

	public static UOMReqDTO getInvalidReqTemplate() {
		UOMReqDTO req = new UOMReqDTO();
		return req;

	}

	public static UOMReqDTO getExpReqTemplate() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		list.add(null);
		req.setUomReqList(list);
		return req;
	}

	public static UOMReqDTO getExp2ReqTemplate() {
		UOMReqDTO req = new UOMReqDTO();
		List<UOMReqData> list = new ArrayList<>();
		UOMReqData input = new UOMReqData();
		input.setFromUom(ApplicationConstants.KPA);
		input.setToUom(ApplicationConstants.INHGA);
		req.setUomReqList(list);
		return req;
	}

	public static UnitOfMeasure getUnitOfMeasure() {
		UnitOfMeasure u = new UnitOfMeasure();
		u.setDimension("");
		u.setDisplayName("");
		u.setGroup("");
		u.setId("");
		u.setName("");
		u.setSymbol("");
		u.getDimension();
		u.getDisplayName();
		u.getGroup();
		u.getId();
		u.getName();
		u.getSymbol();
		UOMResult res = new UOMResult();
		res.setInput(null);
		res.getInput();

		UOMdataDTO dt = new UOMdataDTO();
		dt.setId("");
		dt.setName("");
		dt.getId();
		dt.getName();
		return u;

	}

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
