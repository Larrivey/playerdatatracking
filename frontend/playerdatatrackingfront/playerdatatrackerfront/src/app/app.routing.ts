import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ManualDataComponent } from './manual-data/manual-data.component';

const appRoutes : Routes = [
 { path: 'home', component: HomeComponent  },
 { path: 'manualData', component: ManualDataComponent },
 { path: '', redirectTo: 'home', pathMatch: 'full' }
]
export const routing = RouterModule.forRoot(appRoutes);
