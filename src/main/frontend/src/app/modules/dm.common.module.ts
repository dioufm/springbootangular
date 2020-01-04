import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProgressBarModule } from 'angular-progress-bar';
import { NgCircleProgressModule } from 'ng-circle-progress';
import { NgxSpinnerModule } from "ngx-spinner";
import { SearchBarModule } from '../components/search-bar/search-bar.module';

import { ReportRoutingModule } from './application/report/report-routing.module';
import { MatButtonModule, MatTabsModule, MatIconModule } from '@angular/material';
import { TagCloudModule } from 'angular-tag-cloud-module';


@NgModule({
  declarations: [
  ],
  imports: [
    MatTabsModule,
    MatIconModule,
    TagCloudModule,
    CommonModule,
    FormsModule,
    SearchBarModule,
    ProgressBarModule,
    NgCircleProgressModule.forRoot({
      // set defaults here
      radius: 100,
      outerStrokeWidth: 16,
      innerStrokeWidth: 8,
      outerStrokeColor: "#78C000",
      innerStrokeColor: "#C7E596",
      animationDuration: 300,
    }),
    NgxSpinnerModule
  ],
  exports: [
    MatTabsModule,
    MatIconModule,
    TagCloudModule,
    CommonModule,
    FormsModule,
    SearchBarModule,
    ProgressBarModule,
    NgCircleProgressModule,
    NgxSpinnerModule,
    ReportRoutingModule
  ],
})
export class DmCommonModule { }
