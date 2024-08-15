import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ManualDataComponent } from './manual-data/manual-data.component';
import { AddPlayerComponent } from './add-player/add-player.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';

const appRoutes : Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent  },
  { path: 'manualData', component: ManualDataComponent },
  { path: 'addplayer', component: AddPlayerComponent},
  { path: 'manualdataplayer/:id', component: PlayerDetailComponent },
]
export const routing = RouterModule.forRoot(appRoutes);
