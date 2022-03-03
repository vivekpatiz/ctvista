import { async, ComponentFixture, fakeAsync, TestBed } from '@angular/core/testing';

import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';

import { CreateCondenserRoutingModule } from '../create-condenser.routing';
import { CreateCondenserComponent } from '../component/create-condenser.component';
import { MessageService } from 'primeng/api';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { RouterTestingModule } from '@angular/router/testing';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { By } from '@angular/platform-browser';
import { NgxCurrencyModule } from "ngx-currency";
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatStepperModule } from '@angular/material/stepper';
import { InputStepperComponent } from './input-stepper/input-stepper.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import {of} from 'rxjs/observable/of';
import { CreateCondenserService } from '../service/create-condenser.service';
import { Router } from '@angular/router';

describe('CreateCondenserComponent', () => {
  let component: CreateCondenserComponent;
  let fixture: ComponentFixture<CreateCondenserComponent>;
  const formBuilder: FormBuilder = new FormBuilder();
  const excelUploadData = [
    {
      "CT Vista+ Tag Name": "gross_load",
      "Historian Tag Unit of Measure": "MW"
    },
    {
      "CT Vista+ Tag Name": "condenser_back_pressure_1",
      "Historian Tag Unit of Measure": "inHg(a)"
    },
    {
      "CT Vista+ Tag Name": "boiler_steam_flow_1",
      "Historian Tag Unit of Measure": "kpph"
    },
    {
      "CT Vista+ Tag Name": "condenser_cw_in_temps_1",
      "Historian Tag Unit of Measure": "°F"
    },
    {
      "CT Vista+ Tag Name": "condenser_cw_out_temps_1",
      "Historian Tag Unit of Measure": "°F"
    },
    {
      "CT Vista+ Tag Name": "net_load",
      "Historian Tag Unit of Measure": "MW"
    },
    {
      "CT Vista+ Tag Name": "gross_heat_rate",
      "Historian Tag Unit of Measure": "btu/kWhr"
    },
    {
      "CT Vista+ Tag Name": "net_heat_rate",
      "Historian Tag Unit of Measure": "btu/kWhr"
    },
    {
      "CT Vista+ Tag Name": "ambient_temp",
      "Historian Tag Unit of Measure": "°F"
    },
    {
      "CT Vista+ Tag Name": "humidity",
      "Historian Tag Unit of Measure": "%"
    },
    {
      "CT Vista+ Tag Name": "barometric_pressure",
      "Historian Tag Unit of Measure": "inHg(a)"
    },
    {
      "CT Vista+ Tag Name": "condensate_sodium",
      "Historian Tag Unit of Measure": "ppb"
    },
    {
      "CT Vista+ Tag Name": "condensate_cation_conductivity",
      "Historian Tag Unit of Measure": "umhos"
    },
    {
      "CT Vista+ Tag Name": "condensate_do",
      "Historian Tag Unit of Measure": "ppb"
    },
    {
      "CT Vista+ Tag Name": "attemperation_flow_1",
      "Historian Tag Unit of Measure": "kpph"
    },
    {
      "CT Vista+ Tag Name": "cogen_steam_flow_1",
      "Historian Tag Unit of Measure": "kpph"
    },
    {
      "CT Vista+ Tag Name": "steam_turbine_output_1",
      "Historian Tag Unit of Measure": "MW"
    },
    {
      "CT Vista+ Tag Name": "gas_turbine_output_1",
      "Historian Tag Unit of Measure": "MW"
    },
    {
      "CT Vista+ Tag Name": "lp_exhaust_steam_temp_1",
      "Historian Tag Unit of Measure": "°F"
    },
    {
      "CT Vista+ Tag Name": "hotwell_temp_1",
      "Historian Tag Unit of Measure": "°F"
    },
    {
      "CT Vista+ Tag Name": "condenser_cw_inlet_pressure_1",
      "Historian Tag Unit of Measure": "psi"
    },
    {
      "CT Vista+ Tag Name": "condenser_cw_outlet_pressure_1",
      "Historian Tag Unit of Measure": "psi"
    },
    {
      "CT Vista+ Tag Name": "condenser_cw_delta_pressure_1",
      "Historian Tag Unit of Measure": "psi"
    },
    {
      "CT Vista+ Tag Name": "circ_pump_amps_1",
      "Historian Tag Unit of Measure": "amps"
    },
    {
      "CT Vista+ Tag Name": "gas_fuel_flow_1",
      "Historian Tag Unit of Measure": "scfm"
    },
    {
      "CT Vista+ Tag Name": "duct_burner_fuel_flow_1",
      "Historian Tag Unit of Measure": "scfm"
    },
    {
      "CT Vista+ Tag Name": "coal_fuel_flow_1",
      "Historian Tag Unit of Measure": "tph"
    },
    {
      "CT Vista+ Tag Name": "air_removal_1",
      "Historian Tag Unit of Measure": "scfm"
    }
  ]
  const data = {
    "historianMap": [
      {
        "uom": "393",
        "uomName": "inHg(a)",
        "key": "barometric_pressure"
      },
      {
        "uom": "124",
        "uomName": "°F",
        "descriptor": "12",
        "tagName": "5",
        "key": "condenser_cw_in_temps_1"
      },
      {
        "uom": "409",
        "uomName": "scfm ",
        "key": "duct_burner_fuel_flow_1"
      },
      {
        "uom": "393",
        "uomName": "inHg(a)",
        "key": "condenser_back_pressure_1"
      },
      {
        "uom": "390",
        "uomName": "btu/kWhr",
        "key": "net_heat_rate"
      },
      {
        "uom": "256",
        "uomName": "MW",
        "key": "gas_turbine_output_1"
      },
      {
        "uom": "392",
        "uomName": "%",
        "key": "humidity"
      },
      {
        "uom": "408",
        "uomName": "umhos",
        "key": "condensate_cation_conductivity"
      },
      {
        "uom": "417",
        "uomName": "tph",
        "key": "coal_fuel_flow_1"
      },
      {
        "uom": "409",
        "uomName": "scfm ",
        "key": "air_removal_1"
      },
      {
        "uom": "124",
        "uomName": "°F",
        "descriptor": "13",
        "tagName": "6",
        "key": "condenser_cw_out_temps_1"
      },
      {
        "uom": "115",
        "uomName": "psi",
        "key": "condenser_cw_delta_pressure_1"
      },
      {
        "uom": "406",
        "uomName": "ppb",
        "key": "condensate_do"
      },
      {
        "uom": "124",
        "uomName": "°F",
        "key": "lp_exhaust_steam_temp_1"
      },
      {
        "uom": "124",
        "uomName": "°F",
        "key": "hotwell_temp_1"
      },
      {
        "uom": "398",
        "uomName": "kpph",
        "key": "cogen_steam_flow_1"
      },
      {
        "uom": "398",
        "uomName": "kpph",
        "descriptor": "11",
        "tagName": "4",
        "key": "boiler_steam_flow_1"
      },
      {
        "uom": "406",
        "uomName": "ppb",
        "key": "condensate_sodium"
      },
      {
        "uom": "256",
        "uomName": "MW",
        "key": "steam_turbine_output_1"
      },
      {
        "uom": "124",
        "uomName": "°F",
        "key": "ambient_temp"
      },
      {
        "uom": "256",
        "uomName": "MW",
        "descriptor": "8",
        "tagName": "1",
        "key": "gross_load"
      },
      {
        "uom": "390",
        "uomName": "btu/kWhr",
        "key": "gross_heat_rate"
      },
      {
        "uom": "115",
        "uomName": "psi",
        "key": "condenser_cw_outlet_pressure_1"
      },
      {
        "uom": "115",
        "uomName": "psi",
        "key": "condenser_cw_inlet_pressure_1"
      },
      {
        "uom": "256",
        "uomName": "MW",
        "key": "net_load"
      },
      {
        "uom": "405",
        "uomName": "amps",
        "key": "circ_pump_amps_1"
      },
      {
        "uom": "409",
        "uomName": "scfm ",
        "key": "gas_fuel_flow_1"
      }
    ],
    "Condenser": {
      "id": "67b7be5d-a90e-44c8-9616-b94d3278a4f6",
      "createdAt": "2021-12-31 03:46:48",
      "updatedAt": "2021-12-31 03:46:48",
      "createdBy": "vinayk",
      "updatedBy": "vinayk",
      "condenserName": "Condenser_3112",
      "status": true,
      "facilityName": "Demo Facility",
      "accountName": "Tenaska",
      "systemName": "Management Monitoring",
      "tenantId": 1207
    },
    "unitData": {
      "condenser_effective_area": "2323",
      "steam_flow_based_on_actual_latent_heat_and_duty": "6210.741678509735",
      "design_steam_flow": "511",
      "design_cw_temp_in": "4.5",
      "typical_heat_rate_penalty": "0.02",
      "calculated_desing_steam_latent_heat": "991.8300130425147",
      "design_water_flow": "66",
      "tube_metallurgy": "Admiralty Metal",
      "expected_co2_emmissions": "213.0",
      "design_cf": "33",
      "no_of_total_tubes": "226",
      "calculated_design_bp": "19.34",
      "tube_wall_gauge": "31",
      "tube_metal_and_wall_corr_factor": "1.03",
      "no_of_shells": "66",
      "calculated_moisture": "-1115.4093304324333",
      "design_tr": "186.66666666666666",
      "condensing_surface_area": "694737.9498502059",
      "tube_od_surface_areatube": "25.714454118666666",
      "no_of_passes": "82",
      "ntus": "1989.813551662922",
      "design_uactual": "94.51599300000001",
      "design_uideal": "427.8",
      "no_of_unplugged_tubes": "2231",
      "total_tube_id_flow_area": "1430.4634513744015",
      "design_temp_corr_factor": "0.65",
      "design_duty": "6.16",
      "average_fuel_costs_per_unit": "4.0",
      "tube_wall_thickness": "0.02",
      "average_fuel_costs_per_mmbtus": "1.25",
      "design_udesign": "286.4121",
      "design_cw_temp_out": "191.16666666666666",
      "tube_effective_length": "12.11",
      "plant_max_load": "23",
      "typical_mwhr_sale_price": "0.55",
      "plant_type": "Subcritical (Press <2400 psi)",
      "surface_area_diff_from_design": "-29806.92853423185",
      "calculated_design_steam_temp": "191.16666666666666",
      "design_cw_pressure_drop": "0.056",
      "plant_fuel_source": "PRB Coal",
      "plant_capacity_factor": "98",
      "extraction_steam": "66",
      "number_of_pressure_sample_points": "565",
      "typical_heat_rate": "12500.0",
      "tube_od": "98.222",
      "calculated_velocity": "1.0280501986130187E-4",
      "tube_id_flow_areatube": "7571.005124979351"
    }
  }
  
  const umo =  [
    { value: '', label: 'Select Unit' },
    { value: '35', label: 'W' },
    { value: '37', label: 'Pa' },
    { value: '41', label: 'bar' },
    { value: '85', label: 'mbar' },
    { value: '86', label: 'mmHg' },
    { value: '115', label: 'psi' },
    { value: '123', label: 'C' },
    { value: '124', label: '°F' },
    { value: '256', label: 'MW' },
    { value: '257', label: 'KW' },
    { value: '259', label: 'lbs/hr' },
    { value: '388', label: 'J/s' },
    { value: '389', label: 'GW' },
    { value: '390', label: 'btu/kWhr' },
    { value: '391', label: 'MJ/kWhr' },
    { value: '392', label: '%' },
    { value: '393', label: 'inHg(a)' },
    { value: '394', label: 'inHg(v)' },
    { value: '395', label: 'kPa' },
    { value: '396', label: 'psia' },
    { value: '397', label: 'psig' },
    { value: '398', label: 'kpph' },
    { value: '399', label: 'MMlbs/hr' },
    { value: '400', label: 'kg/hr' },
    { value: '401', label: 'ton/hr' },
    { value: '402', label: 'lb/sec' },
    { value: '403', label: 'ft H2O' },
    { value: '404', label: 'in H2O' },
    { value: '405', label: 'amps' },
    { value: '406', label: 'ppb' },
    { value: '407', label: 'ppt' },
    { value: '408', label: 'umhos' },
    { value: '409', label: 'scfm' },
    { value: '410', label: 'MMbtus/hr' },
    { value: '411', label: 'ft3/min' },
    { value: '412', label: 'ft3/sec' },
    { value: '413', label: 'ft3/hr' },
    { value: '414', label: 'm3/min' },
    { value: '415', label: 'm3/sec' },
    { value: '416', label: 'm3/hr' },
    { value: '417', label: 'tph' },
    { value: '418', label: 'klbs/min' },
  ];

  const facilitydd = [
    {
      "name": "Demo Company",
      "id": 1207,
      "level": 1,
      "owner": 1,
      "type": "account",
      "access_mode": "master",
      "children": [
        {
          "name": "0822 Test Facility",
          "id": 2864,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Pond Water 60%/40%",
              "id": 5752,
              "status": 0,
              "owner": 2864,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test03-phosphate",
              "id": 5751,
              "status": 0,
              "owner": 2864,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test02-coord-template",
              "id": 5750,
              "status": 0,
              "owner": 2864,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test02-coord",
              "id": 5749,
              "status": 0,
              "owner": 2864,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test01-normal",
              "id": 5748,
              "status": 0,
              "owner": 2864,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "ATLANTA BRAVES",
          "id": 2874,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "BARTON BRANDS OF KENTUCKY",
          "id": 2888,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "Boiler System",
          "id": 1495,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "ttest highppr",
              "id": 5741,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "high_pressure_boiler",
              "id": 5743,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test high pressure",
              "id": 5740,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "boiler",
              "id": 5040,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Well 2",
              "id": 4628,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Berner Foods",
              "id": 4952,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Boiler",
              "id": 3322,
              "status": 0,
              "owner": 1495,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Brian",
          "id": 2363,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Tower",
              "id": 4718,
              "status": 0,
              "owner": 2363,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "CC Tex Surf tcn",
          "id": 2867,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "Chicago Plant",
          "id": 2427,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "Demo Facility",
          "id": 1208,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Skybitz",
              "id": 5648,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "RO Test 7-9",
              "id": 5722,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "RO-Test-9-12",
              "id": 5728,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "RO Building",
              "id": 4002,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Sentinel",
              "id": 5644,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "HP Boiler Test - Samir",
              "id": 5747,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Paint Spray Booth",
              "id": 4637,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "SkyBitz System",
              "id": 5587,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Sentinel",
              "id": 5563,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Management Monitoring",
              "id": 5574,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 1,
              "name": "RO3",
              "id": 3433,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 4,
              "name": "Softener",
              "id": 3117,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 5,
              "name": "Cooling Tower",
              "id": 3156,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 6,
              "name": "Demo RO System",
              "id": 2754,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 7,
              "name": "Demo RO1",
              "id": 2759,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 8,
              "name": "Boiler System",
              "id": 2908,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 9,
              "name": "Demo Calcs",
              "id": 2729,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 10,
              "name": "copy",
              "id": 4957,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 10,
              "name": "test",
              "id": 4956,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 10,
              "name": "Demo System",
              "id": 2669,
              "status": 0,
              "owner": 1208,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Demo Jokiet DOC",
          "id": 2508,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Test March 28",
              "id": 5209,
              "status": 0,
              "owner": 2508,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "South Cell",
              "id": 5035,
              "status": 0,
              "owner": 2508,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "North Cell",
              "id": 5034,
              "status": 0,
              "owner": 2508,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Demo PDK AG",
          "id": 2511,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "Duke Univ 60_40",
          "id": 2868,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Chiller Cooling System",
              "id": 5764,
              "status": 0,
              "owner": 2868,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "EQ Playroom  WebMaster",
          "id": 729,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "W600",
              "id": 3584,
              "status": 0,
              "owner": 729,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "WebMaster",
              "id": 1056,
              "status": 0,
              "owner": 729,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Lewis Inventory Facility",
          "id": 2695,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "LEWIS RO Test",
              "id": 5731,
              "status": 0,
              "owner": 2695,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Lewis Inventory System",
              "id": 5414,
              "status": 0,
              "owner": 2695,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "NAU Demo",
          "id": 2444,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Utilities",
              "id": 4912,
              "status": 0,
              "owner": 2444,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Nigel Company",
          "id": 2509,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Nigel Boiler",
              "id": 5037,
              "status": 0,
              "owner": 2509,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Nigel Tower",
              "id": 5036,
              "status": 0,
              "owner": 2509,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "PEI POWER",
          "id": 2883,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "Production",
          "id": 2304,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Area 1",
              "id": 4629,
              "status": 0,
              "owner": 2304,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Samir Test Clone",
          "id": 2885,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Pond Water 60%/40%",
              "id": 5770,
              "status": 0,
              "owner": 2885,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test03-phosphate",
              "id": 5769,
              "status": 0,
              "owner": 2885,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test02-coord-template",
              "id": 5768,
              "status": 0,
              "owner": 2885,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test02-coord",
              "id": 5767,
              "status": 0,
              "owner": 2885,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test01-normal",
              "id": 5766,
              "status": 0,
              "owner": 2885,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Stanford University Energy Center",
          "id": 2890,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Hot Water Loops",
              "id": 5774,
              "status": 0,
              "owner": 2890,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Chilled Water Loops",
              "id": 5773,
              "status": 0,
              "owner": 2890,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Cooling Towers",
              "id": 5772,
              "status": 0,
              "owner": 2890,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Boilers",
              "id": 5771,
              "status": 0,
              "owner": 2890,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Test 5",
          "id": 2510,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Test Aug 26 HP Blrs",
              "id": 5753,
              "status": 0,
              "owner": 2510,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "PO4 Continuum HP Blr Test",
              "id": 5746,
              "status": 0,
              "owner": 2510,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Coord",
              "id": 5742,
              "status": 0,
              "owner": 2510,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "South",
              "id": 5039,
              "status": 0,
              "owner": 2510,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "North",
              "id": 5038,
              "status": 0,
              "owner": 2510,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "TEST ABC CONDO",
          "id": 2507,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "BOILERS",
              "id": 5032,
              "status": 0,
              "owner": 2507,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Test Facility John Lewis",
          "id": 2841,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Test Cooling System _ John Lewis",
              "id": 5701,
              "status": 0,
              "owner": 2841,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "TROPICANA RESORT & CASINO  DIP",
          "id": 2891,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "VANDERBILT UNIVERSITY",
          "id": 2886,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master"
        },
        {
          "name": "Water Purification Facility",
          "id": 1516,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "monday",
              "id": 5726,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test910",
              "id": 5725,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "911",
              "id": 5727,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "912",
              "id": 5729,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "ROtestMulti",
              "id": 5765,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "913",
              "id": 5730,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Pretreatment System",
              "id": 3757,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Test972",
              "id": 5724,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Boiler Example",
              "id": 5653,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "TSwitzer RO test",
              "id": 5282,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test97",
              "id": 5723,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "ROtest9218",
              "id": 5718,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "test111",
              "id": 5719,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "ROTest96",
              "id": 5720,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 1,
              "name": "Reverse Osmosis System",
              "id": 3759,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 2,
              "name": "Distribution System",
              "id": 3758,
              "status": 0,
              "owner": 1516,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "Water Purification Facility #2",
          "id": 1810,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Pretreatment System #2",
              "id": 3785,
              "status": 0,
              "owner": 1810,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 2,
              "name": "Distribution System #2",
              "id": 3787,
              "status": 0,
              "owner": 1810,
              "access_mode": "master",
              "type": "system"
            }
          ]
        },
        {
          "name": "William Rainey Harper College",
          "id": 2617,
          "level": 2,
          "owner": 1207,
          "type": "facility",
          "access_mode": "master",
          "children": [
            {
              "displayorder": 0,
              "name": "Hot Water Loops",
              "id": 5233,
              "status": 0,
              "owner": 2617,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Chilled Water Loops",
              "id": 5232,
              "status": 0,
              "owner": 2617,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Cooling Towers",
              "id": 5231,
              "status": 0,
              "owner": 2617,
              "access_mode": "master",
              "type": "system"
            },
            {
              "displayorder": 0,
              "name": "Boilers",
              "id": 5230,
              "status": 0,
              "owner": 2617,
              "access_mode": "master",
              "type": "system"
            }
          ]
        }
      ]
    },
    {
      "name": "Vinay Kumar Personal Account",
      "id": -90012669,
      "access_mode": "master",
      "type": "account",
      "children": [
        {
          "name": "Vinay Kumar Personal Facility",
          "id": -91012669,
          "type": "facility",
          "readonly": false
        }
      ],
      "readonly": false
    }
  ]
  const matDialogStub = () => ({
    open: (dataDrivinModalComponent, object) => ({
      afterClosed: () => of({})
    }),
  });

  const toastMessgaeServiceStub = () => ({
    showSuccess: toastSuccessMesage => ({})
  });

  const routerStub = () => ({
    navigate: (obj) => {},
    getCurrentNavigation: () => ({
      extras: {
        state: {
          cID: 'xyz'
        }
      }
    })
  })

  const activatedRouteStub = () => ({})

  const createCondenserServicestub = () => ({
    getJSON: () => of({
      "baseURL": "http://172.31.3.250:8082/",
      "getAPICondenser" : "v1/AssetManagement/assets/",
      "deleteAPICondenser" : "v1/AssetManagement/asset/",
      "baseChartURL": "http://172.31.3.250:8083/",
      "getChartAPI": "v1/TimeSeries/charts",
      "postCondenserAPI" : "v1/AssetManagement/asset",
      "baseURLCalc": "http://172.31.3.250:8085",
      "postCustomCalcAPI": "api/v1/runCustomDesignCalculation",
      "getCondenserByIsAPI" : "v1/AssetManagement/condenser-asset/",
      "patchCondenserAPI": "v1/AssetManagement/asset/",
      "chartKey":"v1/AssetManagement/condenser-chart-key/",
      "condenserLoad":"v1/AssetManagement/condenser-load/",
      "UOMBaseURL":"http://172.31.3.250:8092/UOM/",
      "UOMGetURL":"api/v1/uom",
      "getFacilities": "https://develop.ctvistaplus.com/service/accounts",
      "getPlantDataByFacility": "v1/AssetManagement/asset-data",
      "getUserProfile":"https://develop.ctvistaplus.com/service/profile",
      "baseReportURL":"http://172.31.3.250:8084/",
      "getReportList":"api/v1/ReportManagement/report-all/2?size=500&page=0&sort=createdAt&order=ASCENDING",
      "getLogo":"https://develop.ctvistaplus.com//logo/",
      "deleteReport":"api/v1/ReportManagement/reports/",
      "createReport":"api/v1/ReportManagement/report/",
      "getReportById":"api/v1/ReportManagement/report-id/",
      "reportUrl":"http://172.31.3.250:8086/condenser/report/preview",
      "downloadReport":"http://172.31.3.250:8091/pdf",
      "getAPICondenser_v2" : "v2/AssetManagement/assets/",
      "getReportList_v2" : "api/v1/ReportManagement/report-all/",
      "downloadMappingExcel": "api/v1/document/excel/mapping",
      "downloadHistorianExcel": "api/v1/document/excel/historian",
      "UpdatedYAxisLimits" : "v1/TimeSeries/charts/yaxis",
      "accountUrl": "https://develop.ctvistaplus.com/service/account",
      "getTroubleShootingGuide":"api/v1/document/doc",
      "getPopupContentForKpiOverview" : "api/v1/dashboard/design"
    }),
    patchCondenserDataById: (url, id, arr) => of({}),
    setStatusFlag: (url) => of({}),
    downloadFinalHistorianExcel: (url, data) => of({}),
    downloadHistorianExcel: (url, data) => of({}),
    getFacilityDropdown: () => of(facilitydd),
    getCondenserDataById: () => of({data}),
    getUserProfile: (url) => of({}),
    postCustomCalcData: (url, obj) => of({input:  {'typical_heat_rate': 10}}),
    postCondenserData:(url, data) => of({}) 
  })


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        CommonModule,
        FormsModule,
        BrowserAnimationsModule,
        MatButtonModule,
        MatCheckboxModule,
        ReactiveFormsModule,
        MatCardModule,
        MatTabsModule,
        MatIconModule,
        ButtonModule,
        MatInputModule,
        DropdownModule,
        MatStepperModule,
        InputTextModule,
        MatFormFieldModule,
        MatGridListModule,
        NgxCurrencyModule,
        CreateCondenserRoutingModule
      ],
      declarations: [CreateCondenserComponent, InputStepperComponent],
      providers: [MessageService, ToastMessgaeService, 
        { provide: MatDialog, useFactory: matDialogStub }, 
        { provide: FormBuilder, useValue: formBuilder },
        { provide: ToastMessgaeService, useFactory: toastMessgaeServiceStub },
        {provide: Router, useFactory: routerStub},
        {provide: CreateCondenserService, useFactory:createCondenserServicestub }
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCondenserComponent);
    component = fixture.componentInstance;
     fixture.detectChanges();
     (component.formGroup.get('historianMap') as FormGroup).addControl(
      'attemperation_flow', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'cogen_steam_flow', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'steam_turbine_output', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'gas_turbine_output', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'lp_exhaust_steam_temp', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'hotwell_temp', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condenser_cw_inlet_pressure', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condenser_cw_outlet_pressure', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condenser_cw_delta_pressure', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'circ_pump_amps', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'gas_fuel_flow', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'duct_burner_fuel_flow', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'coal_fuel_flow', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'air_removal', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condenser_back_pressure', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'net_load', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'gross_heat_rate', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'net_heat_rate', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'ambient_temp', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'humidity', formBuilder.array([])
    );

    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'barometric_pressure', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condensate_sodium', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condensate_cation_conductivity', formBuilder.array([])
    );
    (component.formGroup.get('historianMap') as FormGroup).addControl(
      'condensate_do', formBuilder.array([])
    );
    
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should call save menthod', fakeAsync(() => {
    component.ngOnInit();
    component.f;
    component.formGroup.controls.condenserName.setValue('xyz');
    component.formGroup.controls.facilityName.setValue('abc');
    component.formGroup.controls.systemName.setValue('pqr');
    component.onSave({});
    spyOn(component, 'onSave');
    const button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();
    fixture.whenStable().then(() => {
      expect(component.onSave).toHaveBeenCalled();
    });
  }));
  it('should throw error message for condenser name field', () => {
    const condensername = component.formGroup.get('condenserName');
    expect(condensername.valid).toBeFalsy();
    condensername.setValue(null);
    expect(condensername.hasError('required')).toBeTruthy();
    condensername.setValue('PB12345');
    expect(condensername.hasError('required')).toBeFalsy();
  });
  it('should throw error message for facility field', () => {
    const facility = component.formGroup.get('facilityName');
    expect(facility.valid).toBeFalsy();
    facility.setValue(null);
    expect(facility.hasError('required')).toBeTruthy();
    facility.setValue('facility1');
    expect(facility.hasError('required')).toBeFalsy();
  });
  it('should throw error message for system field', () => {
    const system = component.formGroup.get('systemName');
    expect(system.valid).toBeFalsy();
    system.setValue(null);
    expect(system.hasError('required')).toBeTruthy();
    system.setValue('system1');
    expect(system.hasError('required')).toBeFalsy();
  });
  it('should call save method explicitly', () => {
    component.userDelats = {username : '' };
    component.onSave('e');
  });
  it('should call numberFltr method with value',()=>{
    const e = 'e'
    component.numberOnlyFltr(e);
     fixture.detectChanges();
    spyOn(component,'numberOnlyFltr');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.numberOnlyFltr).toHaveBeenCalled();
    });
  });
  it('should enable Attemperation Flow fields onclick of check box',()=>{
    const lbl = 'Attemperation Flow';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
    //  fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'kpph','398');
    });
  });
  it('should enable Cogen Steam Flow fields onclick of check box',()=>{
    const lbl = 'Cogen Steam Flow';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
    //  fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'kpph','398');
    });
  });
  it('should enable Steam Turbine Output fields onclick of check box',()=>{
    const lbl = 'Steam Turbine Output';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Gas Turbine Output fields onclick of check box',()=>{
    const lbl = 'Gas Turbine Output';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enableLP Exhaust Steam Temp fields onclick of check box',()=>{
    const lbl = 'LP Exhaust Steam Temp';
    component.checkCheckBoxvalue('true',lbl,);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Hotwell Temp fields onclick of check box',()=>{
    const lbl = 'Hotwell Temp';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Condenser CW Inlet Pressure fields onclick of check box',()=>{
    const lbl = 'Condenser CW Inlet Pressure';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Condenser CW Outlet Pressure fields onclick of check box',()=>{
    const lbl = 'Condenser CW Outlet Pressure';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Condenser CW Delta Pressure fields onclick of check box',()=>{
    const lbl = 'Condenser CW Delta Pressure';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Circ Pump Amps fields onclick of check box',()=>{
    const lbl = 'Circ Pump Amps';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Gas Fuel Flow fields onclick of check box',()=>{
    const lbl = 'Gas Fuel Flow';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Duct Burner Fuel Flow fields onclick of check box',()=>{
    const lbl = 'Duct Burner Fuel Flow';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Coal Fuel Flow fields onclick of check box',()=>{
    const lbl = 'Coal Fuel Flow';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(true,lbl,'mw','256');
    });
  });
  it('should enable Air Removal fields onclick of check box',()=>{
    const lbl = 'Air Removal';
    component.checkCheckBoxvalue('true',lbl);
    spyOn(component,'addDynamicFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicFC).toHaveBeenCalledWith(false,lbl,'mw','256');
    });
  });
  it('should enable Net Load fields onclick of check box',()=>{
    const lbl = 'Net Load';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'mw','256');
    });
  });
  it('should not enable Net Load fields onclick of check box',()=>{
    const lbl = 'Net Load';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'mw','256');
    });
  });
  it('should enable Gross Heat Rate fields onclick of check box',()=>{
    const lbl = 'Gross Heat Rate';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'mw','256');
    });
  });
  it('should not enable Gross Heat Rate fields onclick of check box',()=>{
    const lbl = 'Gross Heat Rate';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'mw','256');
    });
  });
  it('should enable Net Heat Rate fields onclick of check box',()=>{
    const lbl = 'Net Heat Rate';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'btu/kWhr','390');
    });
  });
  it('should not enable Net Heat Rate fields onclick of check box',()=>{
    const lbl = 'Net Heat Rate';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'btu/kWhr','390');
    });
  });
  it('should enable Ambient Temp fields onclick of check box',()=>{
    const lbl = 'Ambient Temp';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'°F','124');
    });
  });
  it('should not enable Ambient Temp fields onclick of check box',()=>{
    const lbl = 'Ambient Temp';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'°F','124');
    });
  });
  it('should enable Humidity fields onclick of check box',()=>{
    const lbl = 'Humidity';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'%','392');
    });
  });
  it('should not enable Humidity fields onclick of check box',()=>{
    const lbl = 'Humidity';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'%','392');
    });
  });
  it('should enable Barometric Pressure fields onclick of check box',()=>{
    const lbl = 'Barometric Pressure';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'inHg(a)','393');
    });
  });
  it('should not enable Barometric Pressure fields onclick of check box',()=>{
    const lbl = 'Barometric Pressure';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'inHg(a)','393');
    });
  });
  it('should enable Condensate Sodium fields onclick of check box',()=>{
    const lbl = 'Condensate Sodium';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'ppb','ppb');
    });
  });
  it('should not enable Condensate Sodium fields onclick of check box',()=>{
    const lbl = 'Condensate Sodium';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'ppb','ppb');
    });
  });
  it('should enable Condensate Cation Conductivity fields onclick of check box',()=>{
    const lbl = 'Condensate Cation Conductivity';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'umhos','umhos');
    });
  });
  it('should not enable Condensate Cation Conductivity fields onclick of check box',()=>{
    const lbl = 'Condensate Cation Conductivity';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'umhos','umhos');
    });
  });
  it('should enable Condensate DO fields onclick of check box',()=>{
    const lbl = 'Condensate DO';
    const eve = {checked: true};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.addDynamicSingleFC).toHaveBeenCalledWith(true,'ppb','ppb');
    });
  });
  it('should not enable Condensate DO fields onclick of check box',()=>{
    const lbl = 'Condensate DO';
    const eve = {checked: false};
    component.checkCheckBoxvalue(eve,lbl);
    spyOn(component,'addDynamicSingleFC');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect((component.formGroup.get('historianMap') as FormGroup).removeControl('condensate_do')).toBeUndefined();
    });
  });
  it('should getFieldAndLabel to generate Condenser Back Pressure field',()=>{
    const eve = [,'Condenser Back Pressure'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('condenser_back_pressure', 'inHg(a)', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate Boiler Steam Flow field',()=>{
    const eve = [2,'Boiler Steam Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('boiler_steam_flow', 'kpph', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate attemperation Flow field',()=>{
    const eve = [2,'Attemperation Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('attemperation_flow', 'kpph', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate Cogen Steam Flow field',()=>{
    const eve = [2,'Cogen Steam Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('cogen_team_low', 'kpph', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate Condenser CW In Temps field',()=>{
    const eve = [2,'Condenser CW In Temps'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('condenser_cw_in_temps', 'F', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate Condenser CW Out Temps field',()=>{
    const eve = [2,'Condenser CW Out Temps'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('condenser_cw_out_temps', 'F', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate Steam Turbine Output field',()=>{
    const eve = [2,'Steam Turbine Output'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.generateDynamicArray).toHaveBeenCalledWith('steam_turbine_output', 'MW', eve[0]);
    });
  });
  it('should getFieldAndLabel to generate attemperation Flow field',()=>{
    const eve = [2,'Attemperation Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'kpph', 'attemperation_flow');
    });
  });
  it('should getFieldAndLabel to generate Gas Turbine Output field',()=>{
    const eve = [2,'Gas Turbine Output'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'MW', 'gas_turbine_output');
    });
  });
  it('should getFieldAndLabel to generate LP Exhaust Steam Temp field',()=>{
    const eve = [2,'LP Exhaust Steam Temp'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'F', 'lp_exhaust_steam_temp');
    });
  });
  it('should getFieldAndLabel to generate Hotwell Temp field',()=>{
    const eve = [2,'Hotwell Temp'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'F', 'hotwell_temp');
    });
  });
  it('should getFieldAndLabel to generate Condenser CW Inlet Pressure field',()=>{
    const eve = [2,'Condenser CW Inlet Pressure'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'psi', 'condenser_cw_inlet_pressure');
    });
  });
  it('should getFieldAndLabel to generate Condenser CW Outlet Pressure field',()=>{
    const eve = [2,'Condenser CW Outlet Pressure'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'psi', 'condenser_cw_outlet_pressure');
    });
  });
  it('should getFieldAndLabel to generate Condenser CW Delta Pressure field',()=>{
    const eve = [2,'Condenser CW Delta Pressure'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'psi', 'condenser_cw_delta_pressur');
    });
  });
  it('should getFieldAndLabel to generate Circ Pump Amps field',()=>{
    const eve = [2,'Circ Pump Amps'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'amps', 'circ_pump_amps');
    });
  });
  it('should getFieldAndLabel to generate Gas Fuel Flow field',()=>{
    const eve = [2,'Gas Fuel Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'scfm', 'gas_fuel_flow');
    });
  });
  it('should getFieldAndLabel to generate Duct Burner Fuel Flow field',()=>{
    const eve = [2,'Duct Burner Fuel Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'scfm', 'duct_burner_fuel_flow');
    });
  });
  it('should getFieldAndLabel to generate Coal Fuel Flow field',()=>{
    const eve = [2,'Coal Fuel Flow'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'tph', 'coal_fuel_flow');
    });
  });
  it('should getFieldAndLabel to generate Air Removal field',()=>{
    const eve = [2,'Air Removal'];
    component.getFieldAndLabel(eve);
    spyOn(component,'getFieldAndLabel');
     fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(component.genDynamicNonMandtryMultplFld).toHaveBeenCalledWith(eve[0], 'scfm', 'air_removal');
    });
  });
  // it('should call patchToSimilar - fields generated using (-,+) should have same value',()=>{
  //   const control = new FormGroup({
  //     'key': new FormControl('field' + '_'+1),
  //     'uom': new FormControl('val'),
  //     'tagName': new FormControl(null),
  //     'descriptor': new FormControl(null),
  //     'uomName': new FormControl(null)
  //   });
  //   (component.formGroup.get('historianMap').get('air_removal') as FormArray).push(control)
  //   component.patchToSimilar('eve','air_removal');
  //   spyOn(component,'getFieldAndLabel');
  //    fixture.detectChanges();
  //   fixture.whenStable().then(() => {
  //     expect(component.formGroup.get('historianMap').get('air_removal')).toBeTruthy();
  //   });
  // });
  it('should call mapHistorianData', () => {
    component.fileData = excelUploadData;
    component.mapHistorianData();
  });

  it('should call patchExcelValues', () => {
    const control = new FormGroup({
          'key': new FormControl('air_removal'),
          'uom': new FormControl('scfm'),
          'tagName': new FormControl(null),
          'descriptor': new FormControl(null),
          'uomName': new FormControl(null)
        });
    (component.formGroup.get('historianMap').get('air_removal') as FormArray).push(control)
    component.patchExcelValues('air_removal');
  });

  
  it('should call unitIdExists', () => {
    component.unitIdExists('W');
  });

  it('should call getUnitNameById', () => {
    component.getUnitNameById('35');
  });

  it('should call getUnitName', () => {
    component.getUnitName('35');
    
  });

  it('should call unitExists', () => {
    const ccwoutUom = [
      { value: '', label: 'Select Unit' },
      { value: '124', label: '°F' },
      { value: '123', label: 'C' },
    ];
    component.unitExists(ccwoutUom,'C');
  });

  it('should call downloadTagExcelTemplate', () => {
    const tempArr = [];
    let tempHistryArr = [];
    let tagData: any;
    tagData = data;
    tempHistryArr = tagData;
    component.downloadTagExcelTemplate();
  });

  it('should call clearExcel', () => {
    component.clearExcel();
    expect(component.fileInput.nativeElement.value).toBe('');
    expect(component.showUploadExcel).toBe(false);
    expect(component.excelName).toBe('Historian Tag Mapping');
    expect(component.disableReview ).toBe(false);
  });

  it('should call saveTemplate', () => {
    component.saveTemplate('air_removal');
    expect(component.fileInput.nativeElement.value).toBe('');
    expect(component.showUploadExcel).toBe(false);
    expect(component.fileName ).toBe('Historian Tag Mapping');
    expect(component.excelName).toBe('Historian Tag Mapping');
    expect(component.saveHistData).toBe(true);
     
  });

  it('should call saveTemplate', () => {
    component.saveTemplate('air_removal');
    expect(component.fileInput.nativeElement.value).toBe('');
    expect(component.showUploadExcel).toBe(false);
    expect(component.fileName ).toBe('Historian Tag Mapping');
    expect(component.excelName).toBe('Historian Tag Mapping');
    expect(component.saveHistData).toBe(true);
     
  });

  it('should call dynaimc func', () => {
    const lbl = 'Condensate DO';
    component.addDynamicFC(true,lbl,'kpph','398')
  })

  it('should be able to download template', () => {
    component.formGroup.controls.condenserName.setValue('xyz');
    component.formGroup.controls.facilityName.setValue('abc');
    component.formGroup.controls.systemName.setValue('pqr');
    component.allUom = umo;
    component.downloadTemplate({value: {
      a: [{
        uom: '115',
        tagDescriptor: 'b',
        tagName: 'c',
        key: 'd',
      }]
    }});
  })

  it('should be able to call onFacilitySelected', () => {
    component.getFacility();
    component.onFacilitySelected({originalEvent: {target: {textContent: 'Demo Facility'}}})
  })

  it('should be able to upload excel', () => {
    const obj = {
      name: 'abc',
      historianMap: excelUploadData
    }
    const blob = new Blob([JSON.stringify(obj, null, 2)], {type : 'application/json'});
    component.uploadExcel({target:{ files : [blob]}})
    component.openExcelModel({});
  })

  it('should be able to refresh Formula', () => {
    component.refreshFormula({value: '10'}, 'plant_type')
  })
  
  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      spyOn(component, 'ngOnInit');
      component.ngOnInit();
      expect(component.getUserProfile).toBeTruthy();
      expect(component.getFacility).toBeTruthy();
      
    });
  });
});
