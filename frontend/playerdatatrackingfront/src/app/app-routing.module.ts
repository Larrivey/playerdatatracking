import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManualDataComponent } from './components/manual-data/manual-data.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'manual-data', component: ManualDataComponent },
  { path: 'home', component: HomeComponent } // Añade un HomeComponent si es necesario
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
