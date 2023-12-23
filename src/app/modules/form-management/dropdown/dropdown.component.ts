// dropdown.component.ts
import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-dropdown',
  template: `
    <div class="relative">
      <div (click)="toggleDropdown()" class="cursor-pointer">
        {{ selectedOption?.name }}
      </div>
      <div *ngIf="isDropdownOpen" class="absolute top-8 bg-white border border-gray-300 p-2 rounded shadow">
        <div *ngFor="let option of options" (mouseover)="onOptionHover(option)" (click)="onOptionSelect(option)">
          {{ option.name }}
        </div>
      </div>
    </div>
  `,
  styles: []
})
export class DropdownComponent {
  @Input() options!: any[];
  @Input() selectedOption: any;
  @Output() optionSelected = new EventEmitter<any>();

  isDropdownOpen = false;

  toggleDropdown(): void {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  onOptionHover(option: any): void {
    this.optionSelected.emit(option);
  }

  onOptionSelect(option: any): void {
    this.optionSelected.emit(option);
    this.isDropdownOpen = false;
  }
}
