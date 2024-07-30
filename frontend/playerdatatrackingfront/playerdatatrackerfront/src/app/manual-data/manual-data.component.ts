import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-manual-data',
  templateUrl: './manual-data.component.html',
  styleUrls: ['./manual-data.component.css']
})
export class ManualDataComponent {
  isTableVisible: boolean = false;
  data: {nombre: string, club:string}[] = [];

  constructor(private router: Router, private httpClient: HttpClient) {

  }

  toggleTableVisibility() {
    this.isTableVisible = !this.isTableVisible;
  }

  fetchData(value1: string, value2: string) {
    this.data.push({ nombre: value1, club:value2});
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }

  searchManualTrackedPlayers(){
    let url = "http://localhost:8080/players";
    this.httpClient.get(url).subscribe(
      response => {
        let data: any = response;
        console.log(data);
        for(var i=0; i<data.items.length;i++){

        this.fetchData("player"+ i.toString,"club"+i.toString);
        }

      },
      error => console.error(error)

    );

  }
}
