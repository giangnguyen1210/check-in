import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HolidayService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getHolidayList(holiday: any): Observable<any> {
    const url = `${this.apiUrl}/admin/holiday/list-holiday`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, holiday,{ headers });
  }

  createHoliday(holiday: any): Observable<any> {
    const url = `${this.apiUrl}/admin/holiday/create-holiday`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, holiday,{ headers });
  }

  updateHoliday(holiday: any): Observable<any> {
    const url = `${this.apiUrl}/admin/holiday/update-holiday`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, holiday,{ headers });
  }
 
}
