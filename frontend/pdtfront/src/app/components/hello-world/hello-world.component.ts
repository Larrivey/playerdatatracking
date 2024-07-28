import { Component } from '@angular/core';

@Component({
  selector: 'app-hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.css']
})

@NgModule({
  declarations: [HelloWorldComponent],
  exports: [HelloWorldComponent]
})


export class HelloWorldComponent {
  isTableVisible: boolean = false;

  toggleTableVisibility() {
    this.isTableVisible = !this.isTableVisible;
  }
}