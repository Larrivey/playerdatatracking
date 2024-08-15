import { Component, Input, OnInit } from '@angular/core';
import { ManualTrackedPlayer } from 'src/app/entitites/manual-tracker-player';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {
  @Input() player: ManualTrackedPlayer | null = null;

  constructor() { }

  ngOnInit(): void { }

}
