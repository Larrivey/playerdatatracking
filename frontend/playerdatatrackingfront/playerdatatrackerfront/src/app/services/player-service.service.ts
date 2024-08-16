import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ManualTrackedPlayer } from 'src/app/entitites/manual-tracker-player';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = 'http://localhost:8080/player';

  constructor(private http: HttpClient) { }

  getPlayer(id: number): Observable<ManualTrackedPlayer | null> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { id: id.toString() };

    // Hacemos un GET request, pero con el cuerpo
    return this.http.request<any>('GET', this.apiUrl, { headers, body })
      .pipe(
        map(response => {
          if (response.code === 0) {
            return response.entity as ManualTrackedPlayer;
          } else {
            console.error(response.description);
            return null;
          }
        }),
        catchError(error => {
          console.error('Error en la llamada GET:', error);
          return of(null);  // Devuelve un observable con valor null en caso de error
        })
      );
  }
}
