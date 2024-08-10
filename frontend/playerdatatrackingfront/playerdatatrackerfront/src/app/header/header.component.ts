import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(private router: Router, private route: ActivatedRoute){}

  isHomePage: boolean = false;

  ngOnInit() {
    // Detecta si la URL actual es /home
    this.router.events.subscribe(() => {
      this.isHomePage = this.router.url === '/home';
    });
  }

  navigateToHome() {
    this.router.navigate(['/']);
  }



}
