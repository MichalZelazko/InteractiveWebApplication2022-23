import { Component } from '@angular/core';
import { TokenStorageService } from './auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular'
  private roles?: string[];
  authority?: string;

  constructor(private tokenStorage: TokenStorageService) {  }

  ngOnInit() {
    console.log("init");
    if (this.tokenStorage.getToken()) {
      console.log(this.tokenStorage.getToken());
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }
  changeColor(event: any): void {
    let color = '#';
    const letters = '0123456789ABCDEF';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    event.target.style.backgroundColor = color;
  }

  changeTextColor(event: any): void {
    let color = '#';
    const letters = '0123456789ABCDEF';
    for (let i = 0; i < 6; i++)
    {
      color += letters[Math.floor(Math.random() * 16)];
    }
    event.target.style.color = color;
  }
}
