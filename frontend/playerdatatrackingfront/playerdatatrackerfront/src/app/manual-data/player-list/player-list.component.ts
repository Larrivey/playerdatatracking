import { Component, Input, OnInit } from '@angular/core';
import { ManualTrackedPlayer } from 'src/app/entitites/manual-tracker-player';


@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {

  @Input() entityList: ManualTrackedPlayer[] = [];

  constructor() {}

  ngOnInit(): void {
    // Puedes realizar alguna operación inicial con entityList aquí
  }
}
