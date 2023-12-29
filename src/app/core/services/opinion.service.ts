import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OpinionService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getOpinionList(opinion: any): Observable<any> {
    const url = `${this.apiUrl}/admin/opinion/list-opinion`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, opinion,{ headers });
  }

  responseOpinion(opinion: any): Observable<any> {
    const url = `${this.apiUrl}/admin/opinion/response-opinion`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, opinion,{ headers });
  }
}
