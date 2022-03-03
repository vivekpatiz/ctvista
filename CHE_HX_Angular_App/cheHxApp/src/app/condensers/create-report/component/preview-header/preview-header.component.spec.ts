import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewHeaderComponent } from './preview-header.component';

describe('PreviewHeaderComponent', () => {
  let component: PreviewHeaderComponent;
  let fixture: ComponentFixture<PreviewHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreviewHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewHeaderComponent);
    component = fixture.componentInstance;
     fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should check ngOnInit', () => {
    component.item = {
      name: '',
      cName: '',
      facilityName: '',
      systemName: '',
      fromeDte : new Date(),
      toDate:  new Date(),
      value: 2,
      highValue: 100,
      createdBy: 'ab'
    };
    component.ngOnInit();

  });
});
