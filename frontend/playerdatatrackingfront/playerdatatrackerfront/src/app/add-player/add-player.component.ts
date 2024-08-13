import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr'; // Importar ToastrService
import { ManualTrackedPlayer } from '../entitites/manual-tracker-player';

@Component({
  selector: 'app-add-player',
  templateUrl: './add-player.component.html',
  styleUrls: ['./add-player.component.css']
})
export class AddPlayerComponent {
  newPlayer: Partial<ManualTrackedPlayer> = {};
  qualitiesInput: string = '';

  constructor(
    private router: Router,
    private http: HttpClient,
    private toastr: ToastrService
  ) {}

  onSubmit() {
    // Parsear las cualidades
    if (this.qualitiesInput) {
      this.newPlayer.qualities = this.qualitiesInput.split(',').map(quality => quality.trim());
    }

    // Rellenar fecha actual y calcular edad
    const currentDate = new Date();
    this.newPlayer.date = this.formatDate(currentDate); // Formatea la fecha
    if (this.newPlayer.birth) {
      const birthDate = new Date(this.newPlayer.birth);
      const age = currentDate.getFullYear() - birthDate.getFullYear();
      this.newPlayer.age = age;
      this.newPlayer.birth = this.formatDate(birthDate); // Formatea la fecha de nacimiento
    }

    // Hacer la petición POST
    this.http.post<any>('http://localhost:8080/player', this.newPlayer).subscribe(
      (response) => {
        console.log(response.description);
        if (response.code === 0) {
          this.toastr.success('Jugador añadido correctamente', ' ', {
            timeOut: 3000,
            progressBar: true,
            closeButton: true,

          });
        } else {
          this.toastr.error(response.description, '', {
            timeOut: 3000,
            progressBar: true,
            closeButton: true
          });
        }
      },
      (error) => {
        console.error('Error al procesar la petición en el sistema');
        this.toastr.error('Error al procesar la petición en el sistema', 'Error', {
          timeOut: 3000,
          progressBar: true,
          closeButton: true
        });
      }
    );
  }

  private formatDate(date: Date): string {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Los meses son 0-indexados
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
  }
}
