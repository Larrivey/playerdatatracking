import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeletePlayerComponent } from './delete-player.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [DeletePlayerComponent],
  imports: [CommonModule, RouterModule],
  exports: [DeletePlayerComponent] // Exporta el componente para que otros módulos puedan usarlo
})
export class DeletePlayerModule { }
