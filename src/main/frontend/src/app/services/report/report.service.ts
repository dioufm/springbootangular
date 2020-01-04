import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080/report';


  getReport(url: string): Observable<any> {
    return this.http.post<string>(`${this.baseUrl}/www`, url, httpOptions);
  }

}

