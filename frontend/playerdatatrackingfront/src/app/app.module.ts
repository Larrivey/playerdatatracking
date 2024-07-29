import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { Router, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ManualDataComponent } from './components/manual-data/manual-data.component';
import { HomeComponent } from './components/home/home.component';  // Importa el HomeComponent
import { routes } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    //ManualDataComponent,
    //HomeComponent  // Declara el HomeComponent aquí
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    routing
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
