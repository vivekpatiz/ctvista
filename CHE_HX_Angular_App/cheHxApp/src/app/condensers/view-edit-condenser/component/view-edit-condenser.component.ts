import {
  Component,
  OnInit,
  ViewChild,
  ChangeDetectorRef,
  ElementRef,
} from '@angular/core';
import { Router, ActivatedRoute, ActivationEnd } from '@angular/router';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl,
  FormArray,
} from '@angular/forms';
import { ViewEditCondenserService } from '../service/view-edit-condenser.service';
import { ToastMessgaeService } from '../../shared/toast-service/service/toast-messgae.service';
import { MatStepper } from '@angular/material/stepper';
import { DownloadToExcel } from '../../shared/download-excel/download-to-excel.service';
import { DataDrivinModalComponent } from '../../shared/data-drivin-modal/data-drivin-modal.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import * as XLSX from 'xlsx';
import { ViewOptionsRefined } from '@fullcalendar/core';
import { saveAs } from 'file-saver';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-view-edit-condenser',
  templateUrl: './view-edit-condenser.component.html',
  styleUrls: ['./view-edit-condenser.component.scss'],
})
export class ViewEditCondenserComponent implements OnInit {
  @ViewChild('stepper', { static: false }) private matStepper: MatStepper;
  @ViewChild('fileInput', { static: false }) fileInput: ElementRef;
  myInputVariable: ElementRef;
  historianFormData: any;
  startDate: any;
  endDate: any;
  fileData;
  arrayBuffer: any;
  file: File;
  filelist: any;
  showUploadExcel = false;
  disableReview = false;
  fileName: any;
  excelName: string;
  files = [];
  envURl: any;
  label;
  confirmDlg: MatDialogRef<DataDrivinModalComponent>;
  disableSystem = false;
  isDisabled: boolean;
  tenantId: any;
  apiUrls: any;
  facilityName: any;
  selectedFacility: any;
  facilities: any[];
  userFacility: any[];
  listFacility: any[];
  listSystem: any[];
  listPlantType: any[];
  listPlantSource: any[];
  listTubeMetallurgy: any[];
  enblEditBtn = true;
  enblSaveBtn = false;
  options: any;
  condenserName: string;
  glUom: any[];
  cbpUom: any[];
  bsfUom: any[];
  atUom: any[];
  csfUom: any[];
  ccwintUom: any[];
  ccwoutUom: any[];
  stoUom: any[];
  gtoUom: any[];
  lpestUom: any[];
  htUom: any[];
  ccwipUom: any[];
  ccwopUom: any[];
  ccwdpUom: any[];
  cpaUom: any[];
  gffUom: any[];
  dbffUom: any[];
  cffUom: any[];
  arUom: any[];
  nlUom: any[];
  ghrUom: any[];
  nhrUom: any[];
  atempUom: any[];
  hmUom: any[];
  bpUom: any[];
  csUom: any[];
  cccUom: any[];
  cdoUom: any[];

  isLinear = true;

  gl: boolean = false;
  cbp: boolean = false;
  bsf: boolean = false;
  af: boolean = false;
  csf: boolean = false;
  ccwit: boolean = false;
  ccwot: boolean = false;
  nl: boolean = false;
  ghr: boolean = false;
  nhr: boolean = false;
  at: boolean = false;
  hm: boolean = false;
  bp: boolean = false;
  cs: boolean = false;
  ccc: boolean = false;
  cd: boolean = false;
  sto: boolean = false;
  gto: boolean = false;
  lpest: boolean = false;
  ht: boolean = false;
  ccwip: boolean = false;
  ccwop: boolean = false;
  ccwdp: boolean = false;
  cpa: boolean = false;
  gff: boolean = false;
  dbff: boolean = false;
  cff: boolean = false;
  ar: boolean = false;
  atn = false;
  cgntf = false;
  allUom: any[];
  formGroup: FormGroup;
  submitted = false;
  condenserId: string;
  userDelats: any;
  accountId: any;
  recentUserAccName = '';
  recentUserAccId;
  recentUserAccFacilityName = '';
  recentUserAccFacilityId = '';
  recentUserAccSystemName = '';
  recentUserAccSystemId = '';
  maskCurr = {
    align: 'left',
    allowNegative: true,
    allowZero: true,
    decimal: '.',
    precision: 2,
    prefix: '',
    suffix: '',
    thousands: ',',
    nullable: true,
  };
  maskTwoDecimal = {
    align: 'left',
    allowNegative: true,
    allowZero: true,
    decimal: '.',
    precision: 2,
    prefix: '',
    suffix: '',
    thousands: '',
    nullable: true,
  };
  maskThreeDecimal = {
    align: 'left',
    allowNegative: true,
    allowZero: true,
    decimal: ',',
    precision: 0,
    prefix: '',
    suffix: '',
    thousands: ',',
    nullable: true,
  };
  maskOneDecimalDot = {
    align: 'left',
    allowNegative: true,
    allowZero: true,
    decimal: '.',
    precision: 1,
    prefix: '',
    suffix: '',
    thousands: ',',
    nullable: true,
  };
  maskThreeDecimalDot = {
    align: 'left',
    allowNegative: true,
    allowZero: true,
    decimal: '.',
    precision: 3,
    prefix: '',
    suffix: '',
    thousands: ',',
    nullable: true,
  };
  maskTwoDecimalDot = {
    align: 'left',
    allowNegative: true,
    allowZero: true,
    decimal: '.',
    precision: 2,
    prefix: '',
    suffix: '',
    thousands: ',',
    nullable: true,
  };

  constructor(
    private route: Router,
    private activeRoute: ActivatedRoute,
    private fb: FormBuilder,
    private cdRef: ChangeDetectorRef,
    private viewEditCondenserService: ViewEditCondenserService,
    public toastService: ToastMessgaeService,
    private DownloadToExcel: DownloadToExcel,
    private dialog: MatDialog
  ) {
    this.formGroup = this.fb.group({
      condenserName: [null, Validators.required],
      facilityName: [null, Validators.required],
      systemName: [null, Validators.required],
      tenantId: [null],
      orgId: [null],
      createdBy: [null],
      plantData: this.fb.group({}),
      unitData: this.fb.group({
        plant_type: [null],
        plant_fuel_source: [null],
        plant_max_load: [null],
        plant_capacity_factor: [null],
        extraction_steam: [null],
        typical_mwhr_sale_price: [null],
        typical_heat_rate: [null],
        typical_heat_rate_penalty: [null],
        average_fuel_costs_per_unit: [null],
        average_fuel_costs_per_mmbtus: [null],
        expected_co2_emmissions: [null],
        design_duty: [null],
        design_steam_flow: [null],
        design_water_flow: [null],
        design_cw_temp_in: [null],
        number_of_pressure_sample_points: [null],
        design_cw_pressure_drop: [null],
        design_cf: [null],
        no_of_shells: [null],
        tube_metallurgy: [null],
        no_of_passes: [null],
        no_of_total_tubes: [null],
        no_of_unplugged_tubes: [null],
        tube_od: [null],
        tube_wall_gauge: [null],
        tube_effective_length: [null],
        condenser_effective_area: [null],
      }),
      historianMap: fb.group({
        gross_load: this.fb.array([]),
        condenser_back_pressure: this.fb.array([]),
        boiler_steam_flow: this.fb.array([]),
        condenser_cw_in_temps: this.fb.array([]),
        condenser_cw_out_temps: this.fb.array([]),
      }),
    });
    this.formGroup.disable();
    if (this.route.getCurrentNavigation() !== null || undefined) {
      this.condenserId = this.route.getCurrentNavigation().extras.state.cID;
    }

    this.listPlantType = [
      { value: 'Supercritical', label: 'Supercritical' },
      {
        value: 'Subcritical (Press >2400 psi)',
        label: 'Subcritical (Press >2400 psi)',
      },
      {
        value: 'Subcritical (Press <2400 psi)',
        label: 'Subcritical (Press <2400 psi)',
      },
      {
        value: 'Combined Cycle (Press >2100 psi)',
        label: 'Combined Cycle (Press >2100 psi)',
      },
      {
        value: 'Combined Cycle (Press <2100 psi)',
        label: 'Combined Cycle (Press <2100 psi)',
      },
      { value: 'Nuclear', label: 'Nuclear' },
    ];

    // array to get plant source in dropdown
    this.listPlantSource = [
      { value: 'Bituminous Coal', label: 'Bituminous Coal' },
      { value: 'Sub-bituminous coal', label: 'Sub-bituminous coal' },
      { value: 'PRB Coal', label: 'PRB Coal' },
      { value: 'Lignite', label: 'Lignite' },
      { value: 'Natural Gas', label: 'Natural Gas' },
      { value: '#2 Oil', label: '#2 Oil' },
      { value: '#6 Oil', label: '#6 Oil' },
      { value: 'Wood and wood waste', label: 'Wood and wood waste' },
      { value: 'Municipal Refuse', label: 'Municipal Refuse' },
      { value: 'Nuclear', label: 'Nuclear' },
    ];

    // array to get tube metallurgy in dropdown
    this.listTubeMetallurgy = [
      { value: '70-30 Cu-Ni', label: '70-30 Cu-Ni' },
      { value: '90-10 Cu-Ni', label: '90-10 Cu-Ni' },
      { value: 'Admiralty Metal', label: 'Admiralty Metal' },
      { value: 'Aluminum Brass', label: 'Aluminum Brass' },
      { value: 'Aluminum Bronze', label: 'Aluminum Bronze' },
      { value: 'Arsenical Copper', label: 'Arsenical Copper' },
      {
        value: 'Cold-Rolled Low Carbon Steel',
        label: 'Cold-Rolled Low Carbon Steel',
      },
      {
        value: 'Copper Iron 194 (Olin 194)',
        label: 'Copper Iron 194 (Olin 194)',
      },
      { value: 'Titanium grades 1 & 2', label: 'Titanium grades 1 & 2' },
      { value: 'Type 304 SS', label: 'Type 304 SS' },
      { value: 'Type 316/317 SS', label: 'Type 316/317 SS' },
      { value: 'UNS N08367 (AL6XN)', label: 'UNS N08367 (AL6XN)' },
      { value: 'UNS S43035 (TP439)', label: 'UNS S43035 (TP439)' },
      { value: 'UNS S44660 (Sea-Cure)', label: 'UNS S44660 (Sea-Cure)' },
      { value: 'UNS S44735 (AL29-4C)', label: 'UNS S44735 (AL29-4C)' },
      { value: 'UNS 32003 (Duplex SS ATI 2003)', label: 'UNS 32003 (Duplex SS ATI 2003)' },
      { value: 'UNS S31803,S32205 (Duplex SS 2205)', label: 'UNS S31803,S32205 (Duplex SS 2205)' },
      { value: 'UNS S32750 (Duplex SS 2507)', label: 'UNS S32750 (Duplex SS 2507)' },
    ];
    this.glUom = [
      { value: '', label: 'Select Unit' },
      { value: '256', label: 'MW' },
      { value: '257', label: 'KW' },
      { value: '35', label: 'W' },
      { value: '388', label: 'J/s' },
      { value: '389', label: 'GW' },
    ];

    this.cbpUom = [
      { value: '', label: 'Select Unit' },
      { value: '393', label: 'inHg(a)' },
      { value: '394', label: 'inHg(v)' },
      { value: '395', label: 'kPa' },
      { value: '37', label: 'Pa' },
      { value: '396', label: 'psia' },
      { value: '397', label: 'psig' },
      { value: '86', label: 'mmHg' },
      { value: '41', label: 'bar' },
      { value: '85', label: 'mbar' },
    ];

    this.bsfUom = [
      { value: '', label: 'Select Unit' },
      { value: '398', label: 'kpph' },
      { value: '259', label: 'lbs/hr' },
      { value: '399', label: 'MMlbs/hr' },
      { value: '400', label: 'kg/hr' },
      { value: '401', label: 'ton/hr' },
      { value: '402', label: 'lb/sec' },
    ];

    this.atUom = [
      { value: '', label: 'Select Unit' },
      { value: '398', label: 'kpph' },
      { value: '259', label: 'lbs/hr' },
      { value: '399', label: 'MMlbs/hr' },
      { value: '400', label: 'kg/hr' },
      { value: '401', label: 'ton/hr' },
      { value: '402', label: 'lb/sec' },
    ];

    this.csfUom = [
      { value: '', label: 'Select Unit' },
      { value: '398', label: 'kpph' },
      { value: '259', label: 'lbs/hr' },
      { value: '399', label: 'MMlbs/hr' },
      { value: '400', label: 'kg/hr' },
      { value: '401', label: 'ton/hr' },
      { value: '402', label: 'lb/sec' },
    ];

    this.ccwintUom = [
      { value: '', label: 'Select Unit' },
      { value: '124', label: '°F' },
      { value: '123', label: 'C' },
    ];

    this.ccwoutUom = [
      { value: '', label: 'Select Unit' },
      { value: '124', label: '°F' },
      { value: '123', label: 'C' },
    ];

    this.nlUom = [
      { value: '', label: 'Select Unit' },
      { value: '256', label: 'MW' },
      { value: '35', label: 'W' },
      { value: '388', label: 'J/s' },
      { value: '389', label: 'GW' },
    ];

    this.ghrUom = [
      { value: '', label: 'Select Unit' },
      { value: '390', label: 'btu/kWhr' },
      { value: '391', label: 'MJ/kWhr' },
    ];

    this.nhrUom = [
      { value: '', label: 'Select Unit' },
      { value: '390', label: 'btu/kWhr' },
      { value: '391', label: 'MJ/kWhr' },
    ];

    this.atempUom = [
      { value: '', label: 'Select Unit' },
      { value: '124', label: '°F' },
      { value: '123', label: 'C' },
    ];

    this.hmUom = [
      { value: '', label: 'Select Unit' },
      { value: '392', label: '%' },
    ];

    this.bpUom = [
      { value: '', label: 'Select Unit' },
      { value: '393', label: 'inHg(a)' },
      { value: '394', label: 'inHg(v)' },
      { value: '395', label: 'kPa' },
      { value: '37', label: 'Pa' },
      { value: '396', label: 'psia' },
      { value: '397', label: 'psig' },
      { value: '86', label: 'mmHg' },
      { value: '41', label: 'bar' },
      { value: '85', label: 'mbar' },
    ];

    this.csUom = [
      { value: '', label: 'Select Unit' },
      { value: '406', label: 'ppb' },
      { value: '407', label: 'ppt' },
    ];
    this.cccUom = [
      { value: '', label: 'Select Unit' },
      { value: '408', label: 'umhos' },
    ];
    this.cdoUom = [
      { value: '', label: 'Select Unit' },
      { value: '406', label: 'ppb' },
    ];
    this.ccwipUom = [
      { value: '', label: 'Select Unit' },
      { value: '115', label: 'psi' },
      { value: '403', label: 'ft H2O' },
      { value: '404', label: 'in H2O' },
      { value: '397', label: 'psig' },
      { value: '395', label: 'Kpa' },
      { value: '41', label: 'bar' },
    ];

    this.ccwopUom = [
      { value: '', label: 'Select Unit' },
      { value: '115', label: 'psi' },
      { value: '403', label: 'ft H2O' },
      { value: '404', label: 'in H2O' },
      { value: '397', label: 'psig' },
      { value: '395', label: 'Kpa' },
      { value: '41', label: 'bar' },
    ];

    this.ccwdpUom = [
      { value: '', label: 'Select Unit' },
      { value: '115', label: 'psi' },
      { value: '403', label: 'ft H2O' },
      { value: '404', label: 'in H2O' },
      { value: '397', label: 'psig' },
      { value: '395', label: 'Kpa' },
      { value: '41', label: 'bar' },
    ];

    this.stoUom = [
      { value: '', label: 'Select Unit' },
      { value: '256', label: 'MW' },
      { value: '257', label: 'KW' },
      { value: '35', label: 'W' },
      { value: '388', label: 'J/s' },
    ];

    this.gtoUom = [
      { value: '', label: 'Select Unit' },
      { value: '256', label: 'MW' },
      { value: '257', label: 'KW' },
      { value: '35', label: 'W' },
      { value: '388', label: 'J/s' },
    ];
    this.lpestUom = [
      { value: '', label: 'Select Unit' },
      { value: '124', label: '°F' },
    ];
    this.htUom = [
      { value: '', label: 'Select Unit' },
      { value: '124', label: '°F' },
    ];
    this.cpaUom = [
      { value: '', label: 'Select Unit' },
      { value: '405', label: 'amps' },
    ];
    this.gffUom = [
      { value: '', label: 'Select Unit' },
      { value: '409', label: 'scfm' },
      { value: '410', label: 'MMbtus/hr' },
      { value: '411', label: 'ft3/min' },
      { value: '412', label: 'ft3/sec' },
      { value: '413', label: 'ft3/hr' },
      { value: '414', label: 'm3/min' },
      { value: '415', label: 'm3/sec' },
      { value: '416', label: 'm3/hr' },
    ];
    this.dbffUom = [
      { value: '', label: 'Select Unit' },
      { value: '409', label: 'scfm' },
      { value: '410', label: 'MMbtus/hr' },
      { value: '411', label: 'ft3/min' },
      { value: '412', label: 'ft3/sec' },
      { value: '413', label: 'ft3/hr' },
      { value: '414', label: 'm3/min' },
      { value: '415', label: 'm3/sec' },
      { value: '416', label: 'm3/hr' },
    ];
    this.cffUom = [
      { value: '', label: 'Select Unit' },
      { value: '417', label: 'tph' },
      { value: '259', label: 'lbs/hr' },
      { value: '398', label: 'kpph' },
      { value: '418', label: 'klbs/min' },
    ];
    this.arUom = [
      { value: '', label: 'Select Unit' },
      { value: '409', label: 'scfm' },
    ];

    this.allUom = [
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
    this.listFacility = [];
    this.listSystem = [];
  }

  ngOnInit(): void {
    const par: any = parent;
    // console.log(par);
    if (par.ctvpapp !== undefined) {
      const account = par.get_storage('recent-' + par.ctvpapp.user.id);
      // console.log(`'recent'`, account);
      if (account.account) {
        this.recentUserAccName = account.account.name;
        this.recentUserAccId = account.account.id;
        // console.log(`recentUserAccName`, this.recentUserAccName);
        // console.log(`recentUserAccId`, this.recentUserAccId);
      }
      if (account.facility) {
        this.recentUserAccFacilityName = account.facility.name;
        this.recentUserAccFacilityId = account.facility.id;
        // console.log(
        //   `facilityName : ${this.recentUserAccFacilityName} , FacilityId : ${this.recentUserAccFacilityId}`
        // );
      }
      if (account.system) {
        this.recentUserAccSystemName = account.system.name;
        this.recentUserAccSystemId = account.system.id;

        // console.log(
        //   `SystemName : ${this.recentUserAccSystemName} , SystemId : ${this.recentUserAccSystemId}`
        // );
      }
    }
    this.envURl = environment;
    this.accountId = '';
    this.facilities = [];
    this.userFacility = [];
    this.getUserProfile();
    this.getFacility();
  }
  // getter for error handling
  get f(): any {
    return this.formGroup.controls;
  }
  // set Form value by fecthing API
  setFormValue(condenserData, pData, uData, historianData): any {
    this.excelName = `${condenserData.condenserName}`;
    this.fileName = `${condenserData.condenserName}`;
    this.startDate = condenserData.createdAt;
    this.endDate = condenserData.updatedAt;
    this.facilityName = condenserData.facilityName;
    this.tenantId = condenserData.tenantId;
    this.onFacilitySelected({ value: this.facilityName }, false);

    this.formGroup.get('tenantId').patchValue(condenserData.tenantId);
    this.formGroup.get('createdBy').patchValue(condenserData.createdBy);
    this.formGroup.get('condenserName').patchValue(condenserData.condenserName);
    this.formGroup.get('facilityName').patchValue(condenserData.facilityName);
    if(condenserData.systemName.includes('~')){
    this.formGroup.get('systemName').patchValue(condenserData.systemName.split('~')[0]);
    }
    else{
    this.formGroup.get('systemName').patchValue(condenserData.systemName);
    }
    for (let key in pData) {
      if (this.formGroup.get('unitData').get(key)) {
        this.formGroup.get('unitData').get(key).patchValue(pData[key]);
      }
    }

    for (let key in uData) {
      if (this.formGroup.get('unitData').get(key)) {
        this.formGroup.get('unitData').get(key).patchValue(uData[key]);
      } else if (this.formGroup.get('unitData').get(key)) {
        this.formGroup.get('unitData').get(key).patchValue(uData[key]);
      }
    }
    // tslint:disable-next-line: forin
    for (let key in historianData) {
      let hsKey = historianData[key]['key'];
      const control = this.fb.group({
        key: new FormControl(historianData[key]['key']),
        uom: new FormControl(historianData[key]['uom']),
        tagName: new FormControl(historianData[key]['tagName']),
        descriptor: new FormControl(historianData[key]['descriptor']),
        uomName: new FormControl(historianData[key]['uomName']),
      });
      if (hsKey.includes('gross_load')) {
        (this.formGroup
          .get('historianMap')
          .get('gross_load') as FormArray).push(control);
      } else if (hsKey.includes('condenser_back_pressure')) {
        (this.formGroup
          .get('historianMap')
          .get('condenser_back_pressure') as FormArray).push(control);
      } else if (hsKey.includes('boiler_steam_flow')) {
        (this.formGroup
          .get('historianMap')
          .get('boiler_steam_flow') as FormArray).push(control);
      } else if (hsKey.includes('condenser_cw_in_temps')) {
        (this.formGroup
          .get('historianMap')
          .get('condenser_cw_in_temps') as FormArray).push(control);
      } else if (hsKey.includes('condenser_cw_out_temps')) {
        (this.formGroup
          .get('historianMap')
          .get('condenser_cw_out_temps') as FormArray).push(control);
      } else if (hsKey.includes('net_load')) {
        this.nl = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'net_load',
          this.fb.array([])
        );
        (this.formGroup.get('historianMap').get('net_load') as FormArray).push(
          control
        );
      } else if (hsKey.includes('gross_heat_rate')) {
        this.ghr = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'gross_heat_rate',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('gross_heat_rate') as FormArray).push(control);
      } else if (hsKey.includes('net_heat_rate')) {
        this.nhr = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'net_heat_rate',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('net_heat_rate') as FormArray).push(control);
      } else if (hsKey.includes('ambient_temp')) {
        this.at = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'ambient_temp',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('ambient_temp') as FormArray).push(control);
      } else if (hsKey.includes('humidity')) {
        this.hm = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'humidity',
          this.fb.array([])
        );
        (this.formGroup.get('historianMap').get('humidity') as FormArray).push(
          control
        );
      } else if (hsKey.includes('barometric_pressure')) {
        this.bp = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'barometric_pressure',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('barometric_pressure') as FormArray).push(control);
      } else if (hsKey.includes('condensate_sodium')) {
        this.cs = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'condensate_sodium',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('condensate_sodium') as FormArray).push(control);
      } else if (hsKey.includes('condensate_cation_conductivity')) {
        this.ccc = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'condensate_cation_conductivity',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('condensate_cation_conductivity') as FormArray).push(control);
      } else if (hsKey.includes('condensate_do')) {
        this.cd = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'condensate_do',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('condensate_do') as FormArray).push(control);
      } else if (hsKey.includes('steam_turbine_output')) {
        this.sto = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'steam_turbine_output',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('steam_turbine_output') as FormArray).push(control);
      } else if (hsKey.includes('gas_turbine_output')) {
        this.gto = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'gas_turbine_output',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('gas_turbine_output') as FormArray).push(control);
      } else if (hsKey.includes('lp_exhaust_steam_temp')) {
        this.lpest = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'lp_exhaust_steam_temp',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('lp_exhaust_steam_temp') as FormArray).push(control);
      } else if (hsKey.includes('hotwell_temp')) {
        this.ht = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'hotwell_temp',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('hotwell_temp') as FormArray).push(control);
      } else if (hsKey.includes('attemperation_flow')) {
        this.atn = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'attemperation_flow',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('attemperation_flow') as FormArray).push(control);
      } else if (hsKey.includes('cogen_steam_flow')) {
        this.cgntf = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'cogen_steam_flow',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('cogen_steam_flow') as FormArray).push(control);
      } else if (hsKey.includes('condenser_cw_inlet_pressure')) {
        this.ccwip = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'condenser_cw_inlet_pressure',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('condenser_cw_inlet_pressure') as FormArray).push(control);
      } else if (hsKey.includes('condenser_cw_outlet_pressure')) {
        this.ccwop = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'condenser_cw_outlet_pressure',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('condenser_cw_outlet_pressure') as FormArray).push(control);
      } else if (hsKey.includes('condenser_cw_delta_pressure')) {
        this.ccwdp = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'condenser_cw_delta_pressure',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('condenser_cw_delta_pressure') as FormArray).push(control);
      } else if (hsKey.includes('circ_pump_amps')) {
        this.cpa = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'circ_pump_amps',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('circ_pump_amps') as FormArray).push(control);
      } else if (hsKey.includes('gas_fuel_flow')) {
        this.gff = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'gas_fuel_flow',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('gas_fuel_flow') as FormArray).push(control);
      } else if (hsKey.includes('duct_burner_fuel_flow')) {
        this.dbff = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'duct_burner_fuel_flow',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('duct_burner_fuel_flow') as FormArray).push(control);
      } else if (hsKey.includes('coal_fuel_flow')) {
        this.cff = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'coal_fuel_flow',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('coal_fuel_flow') as FormArray).push(control);
      } else if (hsKey.includes('air_removal')) {
        this.ar = true;
        (this.formGroup.get('historianMap') as FormGroup).addControl(
          'air_removal',
          this.fb.array([])
        );
        (this.formGroup
          .get('historianMap')
          .get('air_removal') as FormArray).push(control);
      }
    }
    this.formGroup.get('historianMap').disable();
    localStorage.setItem('form-data', '');
    localStorage.setItem(
      'form-data',
      JSON.stringify(this.formGroup.get('historianMap').value)
    );
  }
  // method to handle form submission and toast messages for successfull/failed form submission
  onSave(e: any): any {
    let arr = [];
    let tempArr: FormGroup;
    this.submitted = true;
    tempArr = JSON.parse(JSON.stringify(this.formGroup.value));
    if (this.userDelats !== undefined) {
       tempArr['updatedBy'] = this.userDelats.username;
    }
      // tempArr['updatedBy'] = 'Vinay';

    // tslint:disable-next-line: forin
    for (let key in tempArr['historianMap']) {
      tempArr['historianMap'][key].map(function (obj) {
        arr.push(obj);
      });
    }

    if (this.disableSystem) {
      tempArr['systemName'] = 'No data available';
    }
     else{
     tempArr['systemName'] =   this.formGroup.get('systemName').value+'~'+this.formGroup.get('condenserName').value;
    }

    tempArr['historianMap'] = arr;
    localStorage.setItem(
      'form-data_save',
      JSON.stringify(this.formGroup.get('historianMap').value)
    );
    tempArr['orgId'] = this.recentUserAccId;

    //  tempArr['orgId'] = 1207;
    if (this.selectedFacility) {
      tempArr['facilityName'] = this.selectedFacility;
    } else {
      tempArr['facilityName'] = this.facilityName;
    }
    
    if (this.formGroup.invalid) {
      return;
    }
    // console.log(JSON.stringify(tempArr));
    this.viewEditCondenserService.getJSON().subscribe(
      (url) => {
        this.viewEditCondenserService
          .patchCondenserDataById(url, this.condenserId, tempArr)
          .subscribe((res) => {
            if (res.message) {
              const toastSuccessMesage = {
                severity: 'error',
                summary: `${this.formGroup.get('condenserName').value} Name Already Exists `,
                key: 'existError',
              };
              this.toastService.showError(toastSuccessMesage);
            }
            else {
              const toastSuccessMesage = {
                severity: 'success',
                key: 'update',
                summary: `${this.formGroup.get('condenserName').value}`,
              };
              this.toastService.showSuccess(toastSuccessMesage);
              const statusUrl = `${url.accountUrl}/${this.recentUserAccId}/condenser/status`;
              this.viewEditCondenserService.setStatusFlag(statusUrl).subscribe( res => { })
              this.formGroup.disable();
              localStorage.setItem('form-data', '');
              localStorage.setItem('form-data', JSON.stringify(this.formGroup.get('historianMap').value));
              this.enblSaveBtn = false;
              this.enblEditBtn = true;
              this.excelName = this.formGroup.get('condenserName').value;
              this.route.navigate([`condenser/configurations/${this.formGroup.get('condenserName').value}`],
                { state: { cID: this.condenserId } });
            }
          });
        },
      (err) => {
        const toastSuccessMesage = {
          severity: 'error',
          summary: `${this.formGroup.get('condenserName').value}`,
          key: 'updateError',
        };
        this.toastService.showError(toastSuccessMesage);
      }
    );
  }
  // method to enable editing form
  onEdit(e: any): any {
    this.enblEditBtn = false;
    this.enblSaveBtn = true;
    this.formGroup.enable();
    this.isDisabled = true;
    this.matStepper.previous();
  }
  // method to prevent alphabets
  numberOnlyFltr(event): boolean {
    const charCode = event.which ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
  checkCheckBoxvalue(event, lbl): void {
    if (lbl === 'Steam Turbine Output') {
      this.sto = event.checked;
      this.addDynamicFC(this.sto, 'steam_turbine_output', '256', 'MW');
    } else if (lbl === 'Gas Turbine Output') {
      this.gto = event.checked;
      this.addDynamicFC(this.gto, 'gas_turbine_output', '256', 'MW');
    } else if (lbl === 'LP Exhaust Steam Temp') {
      this.lpest = event.checked;
      this.addDynamicFC(this.lpest, 'lp_exhaust_steam_temp', '124', '°F');
    } else if (lbl === 'Hotwell Temp') {
      this.ht = event.checked;
      this.addDynamicFC(this.ht, 'hotwell_temp', '124', '°F');
    } else if (lbl === 'Attemperation Flow') {
      this.atn = event.checked;
      this.addDynamicFC(this.atn, 'attemperation_flow', '398', 'kpph');
    } else if (lbl === 'Cogen Steam Flow') {
      this.cgntf = event.checked;
      this.addDynamicFC(this.cgntf, 'cogen_steam_flow', '398', 'kpph');
    } else if (lbl === 'Condenser CW Inlet Pressure') {
      this.ccwip = event.checked;
      this.addDynamicFC(
        this.ccwip,
        'condenser_cw_inlet_pressure',
        '115',
        'psi'
      );
    } else if (lbl === 'Condenser CW Outlet Pressure') {
      this.ccwop = event.checked;
      this.addDynamicFC(
        this.ccwop,
        'condenser_cw_outlet_pressure',
        '115',
        'psi'
      );
    } else if (lbl === 'Condenser CW Delta Pressure') {
      this.ccwdp = event.checked;
      this.addDynamicFC(
        this.ccwdp,
        'condenser_cw_delta_pressure',
        '115',
        'psi'
      );
    } else if (lbl === 'Circ Pump Amps') {
      this.cpa = event.checked;
      this.addDynamicFC(this.cpa, 'circ_pump_amps', '405', 'amps');
    } else if (lbl === 'Gas Fuel Flow') {
      this.gff = event.checked;
      this.addDynamicFC(this.gff, 'gas_fuel_flow', '409', 'scfm');
    } else if (lbl === 'Duct Burner Fuel Flow') {
      this.dbff = event.checked;
      this.addDynamicFC(this.dbff, 'duct_burner_fuel_flow', '409', 'scfm');
    } else if (lbl === 'Coal Fuel Flow') {
      this.cff = event.checked;
      this.addDynamicFC(this.cff, 'coal_fuel_flow', '417', 'tph');
    } else if (lbl === 'Air Removal') {
      this.ar = event.checked;
      this.addDynamicFC(this.ar, 'air_removal', '409', 'scfm');
    } else if (lbl === 'Net Load') {
      this.nl = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('net_load', '256', 'MW');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'net_load'
        );
      }
    } else if (lbl === 'Gross Heat Rate') {
      this.ghr = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('gross_heat_rate', '390', 'btu/kWhr');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'gross_heat_rate'
        );
      }
    } else if (lbl === 'Net Heat Rate') {
      this.nhr = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('net_heat_rate', '390', 'btu/kWhr');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'net_heat_rate'
        );
      }
    } else if (lbl === 'Ambient Temp') {
      this.at = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('ambient_temp', '124', '°F');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'ambient_temp'
        );
      }
    } else if (lbl === 'Humidity') {
      this.hm = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('humidity', '392', '%');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'humidity'
        );
      }
    } else if (lbl === 'Barometric Pressure') {
      this.bp = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('barometric_pressure', '393', 'inHg(a)');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'barometric_pressure'
        );
      }
    } else if (lbl === 'Condensate Sodium') {
      this.cs = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('condensate_sodium', '406', 'ppb');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'condensate_sodium'
        );
      }
    } else if (lbl === 'Condensate Cation Conductivity') {
      this.ccc = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('condensate_cation_conductivity', '408', 'umhos');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'condensate_cation_conductivity'
        );
      }
    } else if (lbl === 'Condensate DO') {
      this.cd = event.checked;
      if (event.checked) {
        this.addDynamicSingleFC('condensate_do', '406', 'ppb');
      } else {
        (this.formGroup.get('historianMap') as FormGroup).removeControl(
          'condensate_do'
        );
      }
    }
  }
  // method to add multiple form control
  addDynamicFC(bool, field, val, lable) {
    // console.log(`field`, field);
    // console.log(`field fc`, val);
    if (bool) {
      (this.formGroup.get('historianMap') as FormGroup).addControl(
        field,
        this.fb.array([])
      );
      const control = new FormGroup({
        key: new FormControl(field + '_' + 1),
        uom: new FormControl(val),
        tagName: new FormControl(null),
        descriptor: new FormControl(null),
        uomName: new FormControl(lable),
      });
      (this.formGroup.get('historianMap').get(field) as FormArray).push(
        control
      );
    } else {
      (this.formGroup.get('historianMap') as FormGroup).removeControl(field);
    }
  }

  // method to add single form control
  addDynamicSingleFC(field, val, lable) {
    (this.formGroup.get('historianMap') as FormGroup).addControl(
      field,
      this.fb.array([])
    );
    const control = new FormGroup({
      key: new FormControl(field),
      uom: new FormControl(val),
      tagName: new FormControl(null),
      descriptor: new FormControl(null),
      uomName: new FormControl(lable),
    });
    (this.formGroup.get('historianMap').get(field) as FormArray).push(control);
  }
  // method to get field and labels to generate form
  getFieldAndLabel(event) {
    if (event[1] === 'Condenser Back Pressure') {
      this.generateDynamicArray('condenser_back_pressure', '393', event[0]);
    } else if (event[1] === 'Boiler Steam Flow') {
      this.generateDynamicArray('boiler_steam_flow', '398', event[0]);
    } else if (event[1] === 'Condenser CW In Temps') {
      this.generateDynamicArray('condenser_cw_in_temps', '124', event[0]);
    } else if (event[1] === 'Condenser CW Out Temps') {
      this.generateDynamicArray('condenser_cw_out_temps', '124', event[0]);
    } else if (event[1] === 'Attemperation Flow') {
      (this.formGroup
        .get('historianMap')
        .get('attemperation_flow') as FormArray).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '398', 'attemperation_flow');
    } else if (event[1] === 'Cogen Steam Flow') {
      (this.formGroup
        .get('historianMap')
        .get('cogen_steam_flow') as FormArray).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '398', 'cogen_steam_flow');
    } else if (event[1] === 'Steam Turbine Output') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('steam_turbine_output')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '256', 'steam_turbine_output');
    } else if (event[1] === 'Gas Turbine Output') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('gas_turbine_output')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '256', 'gas_turbine_output');
    } else if (event[1] === 'LP Exhaust Steam Temp') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('lp_exhaust_steam_temp')
      )).clear();
      this.genDynamicNonMandtryMultplFld(
        event[0],
        '124',
        'lp_exhaust_steam_temp'
      );
    } else if (event[1] === 'Hotwell Temp') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('hotwell_temp')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '124', 'hotwell_temp');
    } else if (event[1] === 'Condenser CW Inlet Pressure') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('condenser_cw_inlet_pressure')
      )).clear();
      this.genDynamicNonMandtryMultplFld(
        event[0],
        '115',
        'condenser_cw_inlet_pressure'
      );
    } else if (event[1] === 'Condenser CW Outlet Pressure') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('condenser_cw_outlet_pressure')
      )).clear();
      this.genDynamicNonMandtryMultplFld(
        event[0],
        '115',
        'condenser_cw_outlet_pressure'
      );
    } else if (event[1] === 'Condenser CW Delta Pressure') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('condenser_cw_delta_pressure')
      )).clear();
      this.genDynamicNonMandtryMultplFld(
        event[0],
        '115',
        'condenser_cw_delta_pressure'
      );
    } else if (event[1] === 'Circ Pump Amps') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('circ_pump_amps')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '405', 'circ_pump_amps');
    } else if (event[1] === 'Gas Fuel Flow') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('gas_fuel_flow')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '409', 'gas_fuel_flow');
    } else if (event[1] === 'Duct Burner Fuel Flow') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('duct_burner_fuel_flow')
      )).clear();
      this.genDynamicNonMandtryMultplFld(
        event[0],
        '409',
        'duct_burner_fuel_flow'
      );
    } else if (event[1] === 'Coal Fuel Flow') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('coal_fuel_flow')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '417', 'coal_fuel_flow');
    } else if (event[1] === 'Air Removal') {
      (<FormArray>(
        this.formGroup.get('historianMap').get('air_removal')
      )).clear();
      this.genDynamicNonMandtryMultplFld(event[0], '409', 'air_removal');
    }
  }
  // genrate non mandatory multi fields
  genDynamicNonMandtryMultplFld(n, inituom, field): void {
    for (let i = 1; i <= n; i++) {
      const control = new FormGroup({
        key: new FormControl(field + '_' + i),
        uom: new FormControl(inituom),
        tagName: new FormControl(null),
        descriptor: new FormControl(null),
        uomName: new FormControl(this.getUnitNameById(inituom)),
      });
      (<FormArray>this.formGroup.get('historianMap').get(field)).push(control);
    }
  }
  // generate dynamic mandatory fields
  generateDynamicArray(field, inituom, n) {
    (<FormArray>this.formGroup.get('historianMap').get(field)).clear();
    for (let i = 1; i <= n; i++) {
      const control = new FormGroup({
        key: new FormControl(field + '_' + i),
        uom: new FormControl(inituom),
        tagName: new FormControl(null),
        descriptor: new FormControl(null),
        uomName: new FormControl(this.getUnitNameById(inituom)),
      });
      (<FormArray>this.formGroup.get('historianMap').get(field)).push(control);
    }
  }
  // patch value to similar formcontrol on dropdown select
  patchToSimilar(e, field): void {
    for (let key in this.formGroup.get('historianMap').get(field)['controls']) {
      (this.formGroup.get('historianMap').get(field)['controls'][key][
        'controls'
      ]['uom'] as FormControl).patchValue(e.value);
      if(e.value == ''){
        (this.formGroup.get('historianMap').get(field)['controls'][key][
          'controls'
        ]['uom'] as FormControl).setValidators([ Validators.required]);
        (this.formGroup.get('historianMap').get(field)['controls'][key][
          'controls'
        ]['uom'] as FormControl).updateValueAndValidity();
      }
      
      (this.formGroup.get('historianMap').get(field)['controls'][key][
        'controls'
      ]['uomName'] as FormControl).patchValue(this.getUnitNameById(e.value) );
    }
  }
  // Download template
  downloadTemplate(data) {
    let tempArr = [];
    let tempHistryArr = [];

    const sDate = new Date(this.startDate);
    const eDate = new Date(this.endDate);
    const startingDate =
      sDate.getMonth() +
      1 +
      '/' +
      sDate.getDate() +
      '/' +
      sDate.getFullYear() +
      ' ' +
      sDate.toLocaleString('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true,
      });

    const endingDate =
      eDate.getMonth() +
      1 +
      '/' +
      eDate.getDate() +
      '/' +
      eDate.getFullYear() +
      ' ' +
      eDate.toLocaleString('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true,
      });

    tempHistryArr = JSON.parse(JSON.stringify(data.value));

    // tslint:disable-next-line: forin
    for (let key in tempHistryArr) {
      tempHistryArr[key].map(function (obj) {
        tempArr.push(obj);
      });
    }
    if (this.formGroup.invalid) {
      return;
    }
    const dataForExcel = { mapping: [] };
    tempArr.map((item) => {
      dataForExcel.mapping.push({
        uom: item.uomName,
        tagDescriptor: item.descriptor,
        tagName: item.tagName,
        key: item.key,
      });
    });
    dataForExcel['assetId'] = this.condenserId;
    dataForExcel['assetName'] = this.condenserName;
    dataForExcel['tenantId'] = this.tenantId;

    this.viewEditCondenserService.getJSON().subscribe((url) => {
      this.viewEditCondenserService
        .downloadFinalHistorianExcel(url, dataForExcel)
        .subscribe((res) => {
          if (res) {
            saveAs(res, `${this.fileName}.xlsx`, {
              type:
                'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
            });
          }
        });
    });
  }
  // when historian tab selected on edit mode move the stepper to last stage
  tabChanged(e): void {
    this.matStepper.selectedIndex = e.index;
  }
  // Set counter init value after http response for madatory fields
  getInitValue(field): any {
    if (this.formGroup.get('historianMap')['value'][field].length !== 0) {
      return this.formGroup.get('historianMap')['value'][field].length;
    } else {
      return 1;
    }
  }
  // Set counter init value after http response for non madatory fields
  getInitValueNonMand(field): any {
    if (this.formGroup.get('historianMap')['value'][field] != undefined) {
      return this.formGroup.get('historianMap')['value'][field].length;
    } else {
      return 0;
    }
  }

  getFacility(): void {
    this.viewEditCondenserService.getJSON().subscribe((url) => {
      this.viewEditCondenserService
        .getFacilityDropdown(url)
        .subscribe((res) => {
          this.setfacalityandCondenser(res);
        });
    });
  }
  setfacalityandCondenser(res): void{
    this.viewEditCondenserService.getJSON().subscribe((url) => {

    this.facilities = res;
    // console.log('View Facility', res);
    this.viewEditCondenserService
      .getCondenserDataById(url, this.condenserId)
      .subscribe((data) => {
        this.cbp = true;
        this.condenserName = data.data.Condenser.condenserName;
        // console.log(JSON.stringify(data.data) );
        this.setFormValue(
          data.data.Condenser,
          data.data.plantData,
          data.data.unitData,
          data.data.historianMap
        );
      });

    if (this.recentUserAccId) {
      const resaccount = this.recentUserAccId;
      // console.log(resaccount);
      // console.log(`res`, this.facilities);
      this.userFacility = this.facilities.filter( (fs) => {
        return fs.id == resaccount;
      });
    } else {
      this.userFacility = this.facilities;
    }
    // tslint:disable-next-line: prefer-for-of
    if (this.userFacility[0].children) {
      for (let i = 0; i < this.userFacility[0].children.length; i++) {
        this.listFacility.push({
          value: this.userFacility[0].children[i].name,
          label: this.userFacility[0].children[i].name,
        });
      }
      // console.log('view listFacility ', this.listFacility);
    }
  });
  }

  getUserProfile(): void {
    this.viewEditCondenserService.getJSON().subscribe((url) => {
      this.viewEditCondenserService.getUserProfile(url).subscribe((res) => {
        this.userDelats = res;
        this.accountId = this.userDelats.id;
        // console.log(this.userDelats.username);
        // console.log(this.accountId);
      });
    });
  }

  onFacilitySelected(e, isEdited: boolean = true): void {
    // console.log('selected e ', e);
    const systemsArray = [];
    this.listSystem = [];
    if (this.userFacility.length > 0) {
      if (this.userFacility[0].children) {
        this.userFacility[0].children.forEach((element) => {
          if (element.name === e.value) {
            systemsArray.push(element.children);
          }
        });
        // console.log('systemsArray ', systemsArray);
        if (systemsArray[0] === undefined) {
          this.listSystem = [];
          this.listSystem.push({
            value: 'No data available',
            label: 'No data available',
          });
          this.disableSystem = true;
        } else {
          this.disableSystem = false;
          for (let i = 0; i < systemsArray[0].length; i++) {
            this.listSystem.push({
              value: systemsArray[0][i].name,
              label: systemsArray[0][i].name,
            });
          }
          // console.log('view listSystem ', this.listSystem);
        }
      }
    } else {
      this.listSystem = [];
      this.listSystem.push({
        value: 'No data available',
        label: 'No data available',
      });
      this.disableSystem = true;
    }
    if (isEdited) {
      this.selectedFacility = e.originalEvent.target.textContent;
    }
    // console.log('view listSystem ', this.listSystem);
  }
  openModel(e: any): any {
    this.confirmDlg = this.dialog.open(DataDrivinModalComponent, {
      disableClose: true,
      position: { top: '5%', left: '35%' },
      data: {
        pagetext: `Changes in Unit/Block design or Condenser design values will cause changes in charts
   and new reports created will use these values`,
        pageTitle: 'Save Changes?',
        leavepagebtn: 'Save Changes',
        staypagebtn: 'Discard',
      },
    });
    this.confirmDlg.afterClosed().subscribe(async (response) => {
      if (response) {
        this.onSave(e);
      }
    });
  }

  // Download excel template
  downloadTagExcelTemplate(): void {
    const tempArr = [];
    let tempHistryArr = [];
    let tagData: any;
    tagData = JSON.parse(localStorage.getItem('form-data'));
    // console.log('downloadTagExcelTemplate ', tagData);
    tempHistryArr = tagData;
    // tslint:disable-next-line: forin
    for (let key in tagData) {
      tempHistryArr[key].map(function (obj) {
        tempArr.push(obj);
      });
    }
    // console.log(`temparr`, tempArr);
    const dataForExcel = { mapping: [] };
    tempArr.map((item) => {
      console.log(item);
      dataForExcel.mapping.push({
        uom: this.getUnitTextName(item.uom),
        tagDescriptor: item.descriptor,
        tagName: item.tagName,
        key: item.key,
      });
    });

    // console.log('dataForExcel', dataForExcel);
    this.viewEditCondenserService.getJSON().subscribe((url) => {
      this.viewEditCondenserService
        .downloadHistorianExcel(url, dataForExcel)
        .subscribe((res) => {
          if (res) {
            saveAs(res, `${this.condenserName}.xlsx`, {
              type:
                'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
            });
          }
        });
    });
  }

  uploadExcel(e): void {
    this.fileName = e.target.files[0].name;
    this.file = e.target.files[0];
    const fileReader = new FileReader();
    fileReader.readAsArrayBuffer(this.file);
    fileReader.onload = (e) => {
      this.arrayBuffer = fileReader.result;
      const data = new Uint8Array(this.arrayBuffer);
      const arr = new Array();
      for (let i = 0; i !== data.length; ++i) {
        arr[i] = String.fromCharCode(data[i]);
      }
      const bstr = arr.join('');
      const workbook = XLSX.read(bstr, { type: 'binary' });
      const firstSheetName = workbook.SheetNames[1];
      const worksheet = workbook.Sheets[firstSheetName];
      this.fileData = ''; 
      this.fileData = XLSX.utils.sheet_to_json(worksheet, { raw: true });
      // console.log(this.fileData);
      const arraylist = XLSX.utils.sheet_to_json(worksheet, { raw: true });
      // console.log('file data ', this.fileData);
      localStorage.setItem('form-data', '');
      // localStorage.setItem('form-data', this.fileData);
    };
    this.showUploadExcel = true;
    this.excelName = '';
    this.excelName = `${this.fileName}`;
    this.enblEditBtn = false;
    this.enblSaveBtn = true;
  }

  clearExcel(): void {
    this.fileInput.nativeElement.value = '';
    this.fileName = '';
    this.showUploadExcel = false;
    this.excelName = this.condenserName;
    this.fileName = `${this.condenserName}`;
    this.disableReview = false;
    localStorage.setItem('form-data', '');
    localStorage.setItem(
      'form-data',
      JSON.stringify(this.formGroup.get('historianMap').value)
    );
    this.enblEditBtn = true;
    this.enblSaveBtn = false;
  }

  openExcelModel(e: any): any {
    this.confirmDlg = this.dialog.open(DataDrivinModalComponent, {
      disableClose: true,
      position: { top: '5%', left: '35%' },
      data: {
        pagetext: ` ${this.fileName} has been imported successfully.
    This version of Tag Mapping will be available to download from
    'Download Tag Mapping Excel File' section later as well.  `,
        pageTitle: 'File Import Successful',
        leavepagebtn: 'I Understand',
      },
    });
    this.confirmDlg.afterClosed().subscribe(async (response) => {
      if (response) {
        this.disableReview = true;
        this.fileInput.nativeElement.value = '';
        this.showUploadExcel = false;
        this.excelName = '';
        this.excelName = this.fileName;
        this.formGroup.enable();
        this.enblEditBtn = false;
        this.enblSaveBtn = true;
        this.excelName = `${this.fileName}`;
        this.mapHistorianData();
      }
    });
  }

 

  mapHistorianData(): void {
    if (this.fileData) {
      // tslint:disable-next-line: forin
      for (let key in this.fileData) {
        let hsKey = this.fileData[key]['CT Vista+ Tag Name'];
        const control: any = this.fb.group({
          key: new FormControl(this.fileData[key]['CT Vista+ Tag Name']),
          uom: new FormControl(
            this.getUnitName(
              this.fileData[key]['Historian Tag Unit of Measure']
            )
          ),
          tagName: new FormControl(this.fileData[key]['Historian Tag Name']),
          descriptor: new FormControl(
            this.fileData[key]['Historian Descriptor']
          ),
          uomName: new FormControl(
            this.fileData[key]['Historian Tag Unit of Measure']
          ),
        });
        if (hsKey.includes('gross_load')) {
          const sections = this.formGroup
            .get('historianMap')
            .get('gross_load') as FormArray;
          sections.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('gross_load') as FormArray).push(control);

          if (!this.unitExists(this.glUom, control.value.uomName)) {
            this.patchExcelValues('gross_load');
          }
        } else if (hsKey.includes('condenser_back_pressure')) {
          const condenser_back_pressure = this.formGroup
            .get('historianMap')
            .get('condenser_back_pressure') as FormArray;
          condenser_back_pressure.removeAt(0);
          (this.formGroup
            .get('historianMap')
            .get('condenser_back_pressure') as FormArray).push(control);
          if (!this.unitExists(this.cbpUom, control.value.uomName)) {
            this.patchExcelValues('condenser_back_pressure');
          }
        } else if (hsKey.includes('boiler_steam_flow')) {
          const boiler_steam_flow = this.formGroup
            .get('historianMap')
            .get('boiler_steam_flow') as FormArray;
          boiler_steam_flow.removeAt(0);
          (this.formGroup
            .get('historianMap')
            .get('boiler_steam_flow') as FormArray).push(control);
          if (!this.unitExists(this.bsfUom, control.value.uomName)) {
            this.patchExcelValues('boiler_steam_flow');
          }
        } else if (hsKey.includes('condenser_cw_in_temps')) {
          const condenser_cw_in_temps = this.formGroup
            .get('historianMap')
            .get('condenser_cw_in_temps') as FormArray;
          condenser_cw_in_temps.removeAt(0);
          (this.formGroup
            .get('historianMap')
            .get('condenser_cw_in_temps') as FormArray).push(control);
          if (!this.unitExists(this.ccwintUom, control.value.uomName)) {
            this.patchExcelValues('condenser_cw_in_temps');
          }
        } else if (hsKey.includes('condenser_cw_out_temps')) {
          const condenser_cw_out_temps = this.formGroup
            .get('historianMap')
            .get('condenser_cw_out_temps') as FormArray;
          condenser_cw_out_temps.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condenser_cw_out_temps') as FormArray).push(control);
          if (!this.unitExists(this.ccwoutUom, control.value.uomName)) {
            this.patchExcelValues('condenser_cw_out_temps');
          }
        } else if (hsKey.includes('net_load')) {
          this.nl = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'net_load',
            this.fb.array([])
          );
          const net_load = this.formGroup
            .get('historianMap')
            .get('net_load') as FormArray;
          net_load.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('net_load') as FormArray).push(control);
          if (!this.unitExists(this.nlUom, control.value.uomName)) {
            this.patchExcelValues('net_load');
          }
        } else if (hsKey.includes('gross_heat_rate')) {
          this.ghr = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'gross_heat_rate',
            this.fb.array([])
          );
          const gross_heat_rate = this.formGroup
            .get('historianMap')
            .get('gross_heat_rate') as FormArray;
          gross_heat_rate.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('gross_heat_rate') as FormArray).push(control);
          if (!this.unitExists(this.ghrUom, control.value.uomName)) {
            this.patchExcelValues('gross_heat_rate');
          }
        } else if (hsKey.includes('net_heat_rate')) {
          this.nhr = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'net_heat_rate',
            this.fb.array([])
          );
          const net_heat_rate = this.formGroup
            .get('historianMap')
            .get('net_heat_rate') as FormArray;
          net_heat_rate.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('net_heat_rate') as FormArray).push(control);
          if (!this.unitExists(this.nhrUom, control.value.uomName)) {
            this.patchExcelValues('net_heat_rate');
          }
        } else if (hsKey.includes('ambient_temp')) {
        
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'ambient_temp',
            this.fb.array([])
          );
          const ambient_temp = this.formGroup
            .get('historianMap')
            .get('ambient_temp') as FormArray;
          ambient_temp.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('ambient_temp') as FormArray).push(control);
            this.at = true;
          if (!this.unitExists(this.atempUom, control.value.uomName)) {
            this.patchExcelValues('ambient_temp');
          }
        } else if (hsKey.includes('humidity')) {
          this.hm = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'humidity',
            this.fb.array([])
          );
          const humidity = this.formGroup
            .get('historianMap')
            .get('humidity') as FormArray;
          humidity.removeAt(0);
          (this.formGroup
            .get('historianMap')
            .get('humidity') as FormArray).push(control);
          if (!this.unitExists(this.hmUom, control.value.uomName)) {
            this.patchExcelValues('humidity');
          }
        } else if (hsKey.includes('barometric_pressure')) {
          this.bp = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'barometric_pressure',
            this.fb.array([])
          );
          const barometric_pressure = this.formGroup
            .get('historianMap')
            .get('barometric_pressure') as FormArray;
          barometric_pressure.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('barometric_pressure') as FormArray).push(control);
          if (!this.unitExists(this.bpUom, control.value.uomName)) {
            this.patchExcelValues('barometric_pressure');
          }
        } else if (hsKey.includes('condensate_sodium')) {
          this.cs = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'condensate_sodium',
            this.fb.array([])
          );
          const condensate_sodium = this.formGroup
            .get('historianMap')
            .get('condensate_sodium') as FormArray;
          condensate_sodium.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condensate_sodium') as FormArray).push(control);
          if (!this.unitExists(this.csUom, control.value.uomName)) {
            this.patchExcelValues('condensate_sodium');
          }
        } else if (hsKey.includes('condensate_cation_conductivity')) {
          this.ccc = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'condensate_cation_conductivity',
            this.fb.array([])
          );
          const condensate_cation_conductivity = this.formGroup
            .get('historianMap')
            .get('condensate_cation_conductivity') as FormArray;
          condensate_cation_conductivity.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condensate_cation_conductivity') as FormArray).push(control);
          if (!this.unitExists(this.cccUom, control.value.uomName)) {
            this.patchExcelValues('condensate_cation_conductivity');
          }
        } else if (hsKey.includes('condensate_do')) {
          this.cd = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'condensate_do',
            this.fb.array([])
          );
          const condensate_do = this.formGroup
            .get('historianMap')
            .get('condensate_do') as FormArray;
          condensate_do.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condensate_do') as FormArray).push(control);
          if (!this.unitExists(this.cdoUom, control.value.uomName)) {
            this.patchExcelValues('condensate_do');
          }
        } else if (hsKey.includes('steam_turbine_output')) {
          this.sto = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'steam_turbine_output',
            this.fb.array([])
          );
          const steam_turbine_output = this.formGroup
            .get('historianMap')
            .get('steam_turbine_output') as FormArray;
          steam_turbine_output.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('steam_turbine_output') as FormArray).push(control);
          if (!this.unitExists(this.stoUom, control.value.uomName)) {
            this.patchExcelValues('steam_turbine_output');
          }
        } else if (hsKey.includes('gas_turbine_output')) {
          this.gto = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'gas_turbine_output',
            this.fb.array([])
          );
          const gas_turbine_output = this.formGroup
            .get('historianMap')
            .get('gas_turbine_output') as FormArray;
          gas_turbine_output.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('gas_turbine_output') as FormArray).push(control);
          if (!this.unitExists(this.gtoUom, control.value.uomName)) {
            this.patchExcelValues('gas_turbine_output');
          }
        } else if (hsKey.includes('lp_exhaust_steam_temp')) {
          this.lpest = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'lp_exhaust_steam_temp',
            this.fb.array([])
          );
          const lp_exhaust_steam_temp = this.formGroup
            .get('historianMap')
            .get('lp_exhaust_steam_temp') as FormArray;
          lp_exhaust_steam_temp.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('lp_exhaust_steam_temp') as FormArray).push(control);

          if (!this.unitExists(this.lpestUom, control.value.uomName)) {
            this.patchExcelValues('lp_exhaust_steam_temp');
          }
        } else if (hsKey.includes('hotwell_temp')) {
          this.ht = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'hotwell_temp',
            this.fb.array([])
          );
          const hotwell_temp = this.formGroup
            .get('historianMap')
            .get('hotwell_temp') as FormArray;
          hotwell_temp.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('hotwell_temp') as FormArray).push(control);
          if (!this.unitExists(this.htUom, control.value.uomName)) {
            this.patchExcelValues('hotwell_temp');
          }
        } else if (hsKey.includes('attemperation_flow')) {
          this.atn = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'attemperation_flow',
            this.fb.array([])
          );
          const attemperation_flow = this.formGroup
            .get('historianMap')
            .get('attemperation_flow') as FormArray;
          attemperation_flow.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('attemperation_flow') as FormArray).push(control);
          if (!this.unitExists(this.atUom, control.value.uomName)) {
            this.patchExcelValues('attemperation_flow');
          }
        } else if (hsKey.includes('cogen_steam_flow')) {
          this.cgntf = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'cogen_steam_flow',
            this.fb.array([])
          );
          const cogen_steam_flow = this.formGroup
            .get('historianMap')
            .get('cogen_steam_flow') as FormArray;
          cogen_steam_flow.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('cogen_steam_flow') as FormArray).push(control);
          if (!this.unitExists(this.csfUom, control.value.uomName)) {
            this.patchExcelValues('cogen_steam_flow');
          }
        } else if (hsKey.includes('condenser_cw_inlet_pressure')) {
          this.ccwip = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'condenser_cw_inlet_pressure',
            this.fb.array([])
          );
          const condenser_cw_inlet_pressure = this.formGroup
            .get('historianMap')
            .get('condenser_cw_inlet_pressure') as FormArray;
          condenser_cw_inlet_pressure.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condenser_cw_inlet_pressure') as FormArray).push(control);
          if (!this.unitExists(this.ccwipUom, control.value.uomName)) {
            this.patchExcelValues('condenser_cw_inlet_pressure');
          }
        } else if (hsKey.includes('condenser_cw_outlet_pressure')) {
          this.ccwop = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'condenser_cw_outlet_pressure',
            this.fb.array([])
          );
          const condenser_cw_outlet_pressure = this.formGroup
            .get('historianMap')
            .get('condenser_cw_outlet_pressure') as FormArray;
          condenser_cw_outlet_pressure.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condenser_cw_outlet_pressure') as FormArray).push(control);

          if (!this.unitExists(this.ccwopUom, control.value.uomName)) {
            this.patchExcelValues('condenser_cw_outlet_pressure');
          }
        } else if (hsKey.includes('condenser_cw_delta_pressure')) {
          this.ccwdp = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'condenser_cw_delta_pressure',
            this.fb.array([])
          );
          const condenser_cw_delta_pressure = this.formGroup
            .get('historianMap')
            .get('condenser_cw_delta_pressure') as FormArray;
          condenser_cw_delta_pressure.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('condenser_cw_delta_pressure') as FormArray).push(control);
          if (!this.unitExists(this.ccwdpUom, control.value.uomName)) {
            this.patchExcelValues('condenser_cw_delta_pressure');
          }
        } else if (hsKey.includes('circ_pump_amps')) {
          this.cpa = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'circ_pump_amps',
            this.fb.array([])
          );

          const circ_pump_amps = this.formGroup
            .get('historianMap')
            .get('circ_pump_amps') as FormArray;
          circ_pump_amps.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('circ_pump_amps') as FormArray).push(control);
          if (!this.unitExists(this.cpaUom, control.value.uomName)) {
            this.patchExcelValues('circ_pump_amps');
          }
        } else if (hsKey.includes('gas_fuel_flow')) {
          this.gff = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'gas_fuel_flow',
            this.fb.array([])
          );
          const gas_fuel_flow = this.formGroup
            .get('historianMap')
            .get('gas_fuel_flow') as FormArray;
          gas_fuel_flow.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('gas_fuel_flow') as FormArray).push(control);
          if (!this.unitExists(this.gffUom, control.value.uomName)) {
            this.patchExcelValues('gas_fuel_flow');
          }
        } else if (hsKey.includes('duct_burner_fuel_flow')) {
          this.dbff = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'duct_burner_fuel_flow',
            this.fb.array([])
          );
          const duct_burner_fuel_flow = this.formGroup
            .get('historianMap')
            .get('duct_burner_fuel_flow') as FormArray;
          duct_burner_fuel_flow.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('duct_burner_fuel_flow') as FormArray).push(control);
          if (!this.unitExists(this.dbffUom, control.value.uomName)) {
            this.patchExcelValues('duct_burner_fuel_flow');
          }
        } else if (hsKey.includes('coal_fuel_flow')) {
          this.cff = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'coal_fuel_flow',
            this.fb.array([])
          );
          const coal_fuel_flow = this.formGroup
            .get('historianMap')
            .get('coal_fuel_flow') as FormArray;
          coal_fuel_flow.removeAt(0);

          (this.formGroup
            .get('historianMap')
            .get('coal_fuel_flow') as FormArray).push(control);

          if (!this.unitExists(this.cffUom, control.value.uomName)) {
            this.patchExcelValues('coal_fuel_flow');
          }
        } else if (hsKey.includes('air_removal')) {
          this.ar = true;
          (this.formGroup.get('historianMap') as FormGroup).addControl(
            'air_removal',
            this.fb.array([])
          );
          const air_removal = this.formGroup
            .get('historianMap')
            .get('air_removal') as FormArray;
          air_removal.removeAt(0);
          (this.formGroup
            .get('historianMap')
            .get('air_removal') as FormArray).push(control);
          if (!this.unitExists(this.arUom, control.value.uomName)) {
            this.patchExcelValues('air_removal');
          }
        }
      }

      localStorage.setItem(
        'form-data',
        JSON.stringify(this.formGroup.get('historianMap').value)
      );
    }
  }

  patchExcelValues(controlName): void {
    // tslint:disable-next-line: forin
    for (let key in this.formGroup.get('historianMap').get(controlName)[
      'controls'
    ]) {
      (this.formGroup.get('historianMap').get(controlName)['controls'][key][
        'controls'
      ]['uom'] as FormControl).patchValue('');
      (this.formGroup.get('historianMap').get(controlName)['controls'][key][
        'controls'
      ]['uom'] as FormControl).setValidators([ Validators.required]);
      (this.formGroup.get('historianMap').get(controlName)['controls'][key][
        'controls'
      ]['uom'] as FormControl).updateValueAndValidity();
      // this.submitted = true;
    }
  }
  saveTemplate(data): void {
    localStorage.setItem('form-data', '');
    localStorage.setItem('form-data', JSON.stringify(data.value));
    this.fileInput.nativeElement.value = '';
    this.showUploadExcel = false;
    this.fileName = this.condenserName;
    this.excelName = this.condenserName;
  }
  unitExists(arr, unit): boolean {
    return arr.some(function (el) {
      return el.label == unit;
    });
  }
  unitIdExists(unit): boolean {
    return this.allUom.some(function (el) {
      return el.label == unit;
    });
  }
  getUnitName(unit): string {
    if (this.unitIdExists(unit)) {
      return this.allUom.find((item) => item.label === unit).value;
    }
    else{
      return '';
    }
  }
  getUnitTextName(unit): string {
      return this.allUom.find((item) => item.value === unit).label;
  }

  refreshFormula(event, type): void{
  
    let inputObj = {}

    if (type === 'plant_type') {
        console.log(event.value);
        inputObj = {
            input: {
                'plant_type': event.value
            }
        }
    } else if (type === 'plant_fuel_source') {
        console.log(event.value);
        inputObj = {
            input: {
                'plant_fuel_source': event.value
            }
        }

    }

    this.viewEditCondenserService.getJSON().subscribe(
        (url) => {
            // console.log(JSON.stringify(tempArr));
            this.viewEditCondenserService
                .postCustomCalcData(url, inputObj)
                .subscribe((res) => {
                    console.log(res.input);
                    if (res.input) {
                        if (type === 'plant_type') {
                            this.formGroup.get('unitData').get('typical_heat_rate').patchValue(res.input['typical_heat_rate']);
                        } else if (type === 'plant_fuel_source') {
                            this.formGroup.get('unitData').get('average_fuel_costs_per_unit').patchValue(res.input['average_fuel_costs_per_unit']);
                        }
                    }
                });
        },
        (err) => {
            const toastSuccessMesage = {
                severity: 'error',
                summary: `${this.formGroup.get('condenserName').value}`,
                key: 'saveError',
            };
            this.toastService.showError(toastSuccessMesage);
        }
    );

}
  getUnitNameById(unit): string {
    return this.allUom.find((item) => item.value === unit).label;
  }
}
