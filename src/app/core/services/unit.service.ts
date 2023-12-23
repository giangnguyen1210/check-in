import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UnitService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private headerService: HeaderService) { }
  
  getUnitList(unit: any): Observable<any> {
    const url = `${this.apiUrl}/admin/unit/list-unit`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, unit,{ headers });
  }

  createUnit(unit: any): Observable<any> {
    const url = `${this.apiUrl}/admin/unit/create-unit`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, unit,{ headers });
  }

  updateUnit(unit: any): Observable<any> {
    const url = `${this.apiUrl}/admin/unit/update-unit`;
    const headers = this.headerService.getHeaders()
    return this.http.post(url, unit,{ headers });
  }
 
}
