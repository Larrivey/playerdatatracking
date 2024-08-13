import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ManualDataComponent } from './manual-data/manual-data.component';
import { AddPlayerComponent } from './add-player/add-player.component';

const appRoutes : Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent  },
  { path: 'manualData', component: ManualDataComponent },
  { path: 'addplayer', component: AddPlayerComponent}
]
export const routing = RouterModule.forRoot(appRoutes);
