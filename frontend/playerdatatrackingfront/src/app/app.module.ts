import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ManualDataComponent } from './components/manual-data/manual-data.component';
import { HomeComponent } from './components/home/home.component';  // Importa el HomeComponent

@NgModule({
  declarations: [
    AppComponent,
    ManualDataComponent,
    HomeComponent  // Declara el HomeComponent aquí
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
