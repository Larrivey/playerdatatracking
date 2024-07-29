import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pdtfront';

  constructor(private router: Router) {}

  navigateToManualData() {
    this.router.navigate(['/manual-data']);
  }
}
