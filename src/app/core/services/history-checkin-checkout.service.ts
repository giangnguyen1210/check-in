import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckinCheckoutService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getCheckinCheckoutList(checkin: any): Observable<any> {
    const url = `${this.apiUrl}/admin/history-checkin-checkout/list`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, checkin,{ headers });
  }

  getCheckinCheckoutDetailList(checkin: any): Observable<any> {
    const url = `${this.apiUrl}/admin/history-checkin-checkout/detail`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, checkin,{ headers });
  }
  exportHistoryCheckin(history: any){
    const url = `${this.apiUrl}/admin/history-checkin-checkout/export`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, history,{ headers });
  }

  exportHistoryCheckinDetail(history: any){
    const url = `${this.apiUrl}/admin/history-checkin-checkout/export-detail`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, history,{ headers });
  }

 
}
