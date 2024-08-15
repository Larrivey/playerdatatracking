import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManualDataComponent } from './manual-data.component';
import { RouterModule } from '@angular/router';
import { PlayerListComponent } from './player-list/player-list.component';
import { DeletePlayerModule } from './delete-player/delete-player.module';

@NgModule({
  declarations: [
    ManualDataComponent,
    PlayerListComponent
  ],
  imports: [
    CommonModule,
    RouterModule ,
    DeletePlayerModule
  ],
  exports: [
    ManualDataComponent
  ]
})
export class ManualDataModule { }
