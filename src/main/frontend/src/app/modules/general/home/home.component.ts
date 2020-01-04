import { Component, OnInit } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { environment } from '../../../../environments/environment';
import { ReportService } from 'src/app/services/report/report.service';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/services/common/common.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  name = environment.application.name;
  angular = environment.application.angular;
  bootstrap = environment.application.bootstrap;

  form: any = {};
  report: any;

  constructor(
    private meta: Meta,
    private titleService: Title,
    private reportService: ReportService,
    private commonService: CommonService,
    private router: Router) {
  }

  ngOnInit() {
    this.titleService.setTitle('angular.ganatan : Une application Progressive WebApp développée avec Angular');
    this.meta.addTag({
      name: 'angular-webapp',
      content: 'danny ganatan'
    });
    this.meta.updateTag(
      {
        name: 'description',
        content: 'Cette application a été développée avec angular version 8.2.12 et bootstrap ' +
          ' Elle applique le Routing, le Lazy loading, le Server side rendering et les Progressive Web App (PWA)'
      });

    //iniot form home page
    this.report = {};
  }

  startStudyWebsite() {
    /*
    this.reportService.getReport(this.form.url)
      .subscribe(
        data => {
          if (data != null) {
            console.log(data);
            this.report = data;
            this.router.navigate(['/report', { url: this.form.url }]);
            //this.commonService.setReport(this.report);
            //this.report.dateReportString = new DatePipe('fr-FR').transform(this.report.dateReport, 'long');
            //this.token.saveReport(JSON.stringify(this.report));
          }
        },
        error => console.log('ERROR: ' + error));
  }*/
    this.router.navigate(['/report', { url: this.form.url }]);
  }

}



