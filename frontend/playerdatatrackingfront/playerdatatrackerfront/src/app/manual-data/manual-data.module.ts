import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManualDataComponent } from './manual-data.component';
import { RouterModule } from '@angular/router';
import { PlayerListComponent } from './player-list/player-list.component';

@NgModule({
  declarations: [
    ManualDataComponent,
    PlayerListComponent
  ],
  imports: [
    CommonModule, // Importa CommonModule para ngIf y ngFor
    RouterModule , // Importa RouterModule para la navegación
  ],
  exports: [
    ManualDataComponent
  ]
})
export class ManualDataModule { }
