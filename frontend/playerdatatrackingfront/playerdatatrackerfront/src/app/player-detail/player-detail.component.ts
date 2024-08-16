import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ManualTrackedPlayer } from 'src/app/entitites/manual-tracker-player';
import { PlayerService } from '../services/player-service.service';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {
  player: ManualTrackedPlayer | null = null;

  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService
  ) {}

  ngOnInit(): void {
    const playerId = Number(this.route.snapshot.paramMap.get('id'));
    if (playerId) {
      this.playerService.getPlayer(playerId).subscribe(
        data => {
          if (data) {
            this.player = data;
          } else {
            console.error('No se pudo obtener la información del jugador.');
          }
        },
        error => console.error('Error en la solicitud:', error)
      );
    }
  }
}
