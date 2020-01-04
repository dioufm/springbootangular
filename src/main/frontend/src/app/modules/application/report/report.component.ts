import { Component, OnInit, Injector, AfterViewInit } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';
import { ReportService } from 'src/app/services/report/report.service';
import { CommonService } from 'src/app/services/common/common.service';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { NgxSpinnerService } from 'ngx-spinner';

//import { TokenStorageService } from '../auth/token-storage.service';
import { CloudData, CloudOptions } from 'angular-tag-cloud-module';

import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';


@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  form: any = {};
  report: any;

  wordCloudDatas: CloudData[] = [];
  options: CloudOptions;

  constructor(
    private meta: Meta,
    private titleService: Title,
    injector: Injector,
    private reportService: ReportService,
    private commonService: CommonService,
    private route: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService) {

  }

  initialize() {


  }

  ngOnInit() {
    this.titleService.setTitle('Report ......');
    this.meta.addTag({
      name: 'angular.ganatan',
      content: 'danny ganatan'
    });
    this.meta.updateTag(
      {
        name: 'description',
        content: 'All the new movies'
      });
    this.spinner.show();
    this.form.url = this.route.snapshot.paramMap.get('url');


    this.options = {
      // if width is between 0 and 1 it will be set to the size of the upper element multiplied by the value 
      width: 1000,
      height: 400,
      overflow: false,
    };

    this.reportService.getReport(this.form.url)
      .subscribe(
        report => {
          if (report != null) {
            this.spinner.hide();
            this.report = report;



            //this.report.dateReportString = new DatePipe('fr-FR').transform(this.report.dateReport, 'long');
            //this.token.saveReport(JSON.stringify(this.report));



          }
        },
        (error) => {
          console.log('ERROR: ' + error)
          this.router.navigate(['']);
        }
      );

    if (this.report != null) {
      for (var i = 0; i < this.report.words.length; i++) {
        let word = this.report.words[i];
        //let wordAdd = { text: word.word, weight: word.count, link: 'https://google.com', color: 'red' };
        let wordAdd = { text: word.word, weight: word.count, link: 'https://google.com', color: 'red' };
        this.wordCloudDatas.push(wordAdd);

      }
    }

  }




  onSubmit() {
    console.log(this.form.url);
    this.report = {};
    this.spinner.show();
    this.reportService.getReport(this.form.url)
      .subscribe(
        report => {
          if (report != null) {
            this.spinner.hide();
            console.log(report);
            this.report = report;

            //this.report.dateReportString = new DatePipe('fr-FR').transform(this.report.dateReport, 'long');
            //this.token.saveReport(JSON.stringify(this.report));
          }
        },
        error => console.log('ERROR: ' + error));

    if (this.report != null) {
      for (var i = 0; i < this.report.words.length; i++) {
        let word = this.report.words[i];
        let wordAdd = { text: word.word, weight: word.count, link: 'https://google.com', color: 'blue' };
        this.wordCloudDatas.push(wordAdd);
      }
    }
  }



  public captureScreen() {
    var data = document.getElementById('rapport');
    html2canvas(data).then(canvas => {
      // Few necessary setting options  
      var imgWidth = 208;
      var pageHeight = 295;
      var imgHeight = canvas.height * imgWidth / canvas.width;
      var heightLeft = imgHeight;

      const contentDataURL = canvas.toDataURL('image/png')
      let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF  
      var position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
      pdf.save('rapport.pdf'); // Generated PDF   
    });
  }


}
