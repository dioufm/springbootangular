import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, Subject } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private reportSubject = new Subject<any>();

  getReport() {
    return this.reportSubject.asObservable();
  }

  setReport(report) {
    this.reportSubject.next(report);
  }

  /*private selectRoleDetailsDataSubject = new Subject<any>();
  getSelectRoleDetailsDataSubject() {
    return this.selectRoleDetailsDataSubject.asObservable();
  }

  setSelectRoleDetailsDataSubject(roleDetails: RoleDetails) {
    this.selectRoleDetailsDataSubject.next(roleDetails);
  }*/

}

