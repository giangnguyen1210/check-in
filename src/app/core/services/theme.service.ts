// theme.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private isDark: boolean = this.getTheme();

  constructor() {}

  private getTheme(): boolean {
    const storedTheme = localStorage.getItem('dark');
    return storedTheme ? JSON.parse(storedTheme) : window.matchMedia('(prefers-color-scheme: dark)').matches;
  }

  toggleTheme(): void {
    this.isDark = !this.isDark;
    localStorage.setItem('dark', JSON.stringify(this.isDark));
  }

  getIsDark(): boolean {
    return this.isDark;
  }
}
