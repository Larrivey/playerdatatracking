import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ManualTrackedPlayer } from 'src/app/entitites/manual-tracker-player';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private players: ManualTrackedPlayer[] = [
    // Aquí estarían los datos simulados o un llamado a una API
  ];

  constructor() { }

  getPlayerById(id: number): Observable<ManualTrackedPlayer | undefined> {
    const player = this.players.find(p => p.id === id);
    return of(player);
  }
}
