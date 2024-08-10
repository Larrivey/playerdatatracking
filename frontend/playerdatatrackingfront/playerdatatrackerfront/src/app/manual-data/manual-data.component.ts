import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-manual-data',
  templateUrl: './manual-data.component.html',
  styleUrls: ['./manual-data.component.css']
})
export class ManualDataComponent {

  constructor(private router: Router, private http: HttpClient) {

  }

  players: any[] = [];
  isDataVisible: boolean = false; // Controla la visibilidad de los datos

  fetchPlayers(){
    // Hacer la llamada HTTP a la API
    this.http.get('http://localhost:8080/players')
    .subscribe((response: any) => {
    if (response.code === 0) {
      // Si la respuesta es exitosa, almacenar los jugadores en la variable
      this.players = response.entityList;
      console.log(this.players);
      this.isDataVisible = true; // Muestra los datos
    } else {
      console.error('Error al obtener jugadores:', response.description);
    }
    }, error => {
      console.error('Error en la solicitud:', error);
    });
  }

  togglePlayers() {
    if (this.isDataVisible) {
      // Ocultar los datos
      this.players = [];
      this.isDataVisible = false;
    } else {
      // Cargar los datos
      this.fetchPlayers();
    }
  }

}
