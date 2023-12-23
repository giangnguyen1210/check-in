import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getDepartmentList(department: any): Observable<any> {
    const url = `${this.apiUrl}/admin/department/list-department`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, department,{ headers });
  }

  createDepartment(department: any): Observable<any> {
    const url = `${this.apiUrl}/admin/department/create-department`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, department,{ headers });
  }

  updateDepartment(department: any): Observable<any> {
    const url = `${this.apiUrl}/admin/department/update-department`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, department,{ headers });
  }
 
}
