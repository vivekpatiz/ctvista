import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Router } from '@angular/router';
import { BreadcrumbComponent } from './breadcrumb.component';

describe('BreadcrumbComponent', () => {
  let component: BreadcrumbComponent;
  let fixture: ComponentFixture<BreadcrumbComponent>;

  beforeEach(() => {
    const routerStub = () => ({
      events: { subscribe: f => f({}) },
      snapshot: { params: { id: {} }, data: { path: {} } },
      navigate: array => ({})
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [BreadcrumbComponent],
      providers: [{ provide: Router, useFactory: routerStub }]
    });
    fixture = TestBed.createComponent(BreadcrumbComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`breadcrumbList has default value`, () => {
    expect(component.breadcrumbList).toEqual([]);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      spyOn(component, 'getBreadcrumb').and.callThrough();
      component.ngOnInit();
      expect(component.getBreadcrumb).toHaveBeenCalled();
    });
    it('should be able to navigate', () => {
      component.navButton('xyz');
    })
  });
});
