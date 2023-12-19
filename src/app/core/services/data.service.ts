// data.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private sharedData: any;

  setSharedData(data: any): void {
    this.sharedData = data;
  }

  getSharedData(): any {
    return this.sharedData;
  }
}
