import { Component, OnInit } from '@angular/core';
import {
  Router,
  ActivationStart
} from '@angular/router';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss'],
})
export class BreadcrumbComponent implements OnInit {
  breadcrumbList: Array<any> = [];
  constructor(private router: Router) {}

  ngOnInit(): void {
    this.getBreadcrumb();
  }
  getBreadcrumb(): void {
    this.router.events.subscribe((router: any) => {
      if (router instanceof ActivationStart) {
        this.breadcrumbList = [];
        if (router.snapshot.params.hasOwnProperty('id')) {
          this.breadcrumbList[0] = {
            path: 'condenser/configurations',
            label: 'Condenser Configuration',
          };
          this.breadcrumbList[1] = {
            path: '',
            label: decodeURIComponent(router.snapshot.params.id),
          };
        } else if (router.snapshot.data.path === 'condenser/configurations') {
          this.breadcrumbList = [];
        } else if (router.snapshot.data.path === 'condenser/create') {
          this.breadcrumbList[0] = {
            path: 'condenser/configurations',
            label: 'Condenser Configuration',
          };
          this.breadcrumbList[1] = { path: '', label: 'New Condenser' };
        }
      }
    });
  }
  navButton(route): void {
    this.router.navigate([route[0].path]);
  }
}
