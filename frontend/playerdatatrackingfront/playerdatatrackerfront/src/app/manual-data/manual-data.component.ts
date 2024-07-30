import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manual-data',
  templateUrl: './manual-data.component.html',
  styleUrls: ['./manual-data.component.css']
})
export class ManualDataComponent {
  isTableVisible: boolean = false;
  data: any[] = [];

  constructor(private router: Router) {
    this.fetchData();
  }

  toggleTableVisibility() {
    this.isTableVisible = !this.isTableVisible;
  }

  fetchData() {
    this.data = [
      { column1: 'Dato 1.1', column2: 'Dato 1.2' },
      { column1: 'Dato 2.1', column2: 'Dato 2.2' },
      { column1: 'Dato 3.1', column2: 'Dato 3.2' }
    ];

    console.log(this.data); // Verificar que los datos se están asignando correctamente.
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }
}