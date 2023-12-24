import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getEventList(event: any): Observable<any> {
    const url = `${this.apiUrl}/admin/event/list-event`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, event,{ headers });
  }

  createEvent(event: any): Observable<any> {
    const url = `${this.apiUrl}/admin/event/create-event`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, event,{ headers });
  }

  updateEvent(event: any): Observable<any> {
    const url = `${this.apiUrl}/admin/event/update-event`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, event,{ headers });
  }
 
}
