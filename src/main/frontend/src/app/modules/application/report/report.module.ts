import { NgModule } from '@angular/core';

import { ReportComponent } from './report.component';
import { DmCommonModule } from '../../dm.common.module';




@NgModule({
  declarations: [
    ReportComponent,
  ],
  imports: [
    DmCommonModule
  ],
  exports: [
    ReportComponent
  ],
})
export class ReportModule { }
