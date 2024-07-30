import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManualDataComponent } from './manual-data.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    ManualDataComponent
  ],
  imports: [
    CommonModule, // Importa CommonModule para ngIf y ngFor
    RouterModule  // Importa RouterModule para la navegación
  ],
  exports: [
    ManualDataComponent
  ]
})
export class ManualDataModule { }
