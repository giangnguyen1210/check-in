import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistoryLoginService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getHistoryLoginList(history: any): Observable<any> {
    const url = `${this.apiUrl}/admin/history-login/list`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, history,{ headers });
  }
  getHistoryLoginDetail(history: any): Observable<any> {
    const url = `${this.apiUrl}/admin/history-login/detail`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, history,{ headers });
  }
  
  exportHistoryLogin(history: any){
    const url = `${this.apiUrl}/admin/history-login/export`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, history,{ headers });
  }

  exportHistoryLoginDetail(history: any){
    const url = `${this.apiUrl}/admin/history-login/export-detail`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, history,{ headers });
  }

 
}
