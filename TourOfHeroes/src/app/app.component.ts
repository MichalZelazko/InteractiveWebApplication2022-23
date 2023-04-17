import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TourOfHeroes';

  changeColor(event: any): void {
    let color = '#';
    const letters = '0123456789ABCDEF';
     for (let i = 0; i < 6; i++) {
       color += letters[Math.floor(Math.random() * 16)];
     }
    event.target.style.backgroundColor = color;
  }
}
