import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../auth/token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  info: any;
  countdown: number = 10;
  countdownInterval: any;

  constructor(private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if(this.info.token == null || this.info.token == '{}') {
      this.startCountdown();
    }
  }

  ngOnDestroy() {
    clearInterval(this.countdownInterval);
  }

  redirectToLogin(): void {
    window.location.href = '/auth/signin';
  }

  startCountdown(): void {
    this.countdownInterval = setInterval(() => {
      this.countdown--;

      if (this.countdown === 0) {
        clearInterval(this.countdownInterval);
        this.redirectToLogin();
      }
    }, 1000);
  }

}
