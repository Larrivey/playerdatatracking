import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ManualTrackedPlayer } from '../entitites/manual-tracker-player';

@Component({
  selector: 'app-add-player',
  templateUrl: './add-player.component.html',
  styleUrls: ['./add-player.component.css']
})
export class AddPlayerComponent {
  newPlayer: Partial<ManualTrackedPlayer> = {};
  qualitiesInput: string = '';

  constructor(private router: Router, private http: HttpClient) {}

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
    this.http.post('http://localhost:8080/player', this.newPlayer).subscribe(
      (response) => {
        console.log('Jugador añadido con éxito', response);
        this.router.navigate(['/']); // Navegar de vuelta a la lista de jugadores
      },
      (error) => {
        console.error('Error al añadir el jugador', error);
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
