import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getDepartmentList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-department`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }
  getGenderList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-gender`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }
  getJobTitleList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-jobtitle`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }

  getPositionList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-position`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }
  getStatusList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-status`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }
  getRoleList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-role`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }
  getUnitList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-unit`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }

  getToQList(): Observable<any> {
    const url = `${this.apiUrl}/admin/common/list-toq`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }

  getObjectsList(): Observable<any>{
    const url = `${this.apiUrl}/admin/common/list-objects`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, {},{ headers });
  }
//   getListRole(): Observable<any>{
//     const url = `${this.apiUrl}/admin/common/list-role`;
//     const headers = this.headerService.getHeaders()
//     return this.http.post(url, {},{ headers });
//   }

//   getListStatus(): Observable<any>{
//     const url = `${this.apiUrl}/admin/common/list-status`;
//     const headers = this.headerService.getHeaders()
//     return this.http.post(url, {},{ headers });
//   }

}
