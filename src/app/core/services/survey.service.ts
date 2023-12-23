import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getSurveyList(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/survey/list`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json,{ headers });
  }

  createSurvey(json: any): Observable<any> {
    const url = `${this.apiUrl}/admin/survey/create`
    const headers = this.headerService.getHeaders()
    return this.http.post(url, json, { headers });
  }
  updateSurvey(user:any): Observable<any>{
    const headers = this.headerService.getHeaders();
    const url = `${this.apiUrl}/admin/survey/update`;
    return this.http.post(url, user, { headers });
    
  }

}
