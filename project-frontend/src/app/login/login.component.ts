import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../auth/token-storage.service";
import {LoginInfo} from "../auth/login-info";
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {};
  token?: string;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];
  private loginInfo?: LoginInfo;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken() != null && this.tokenStorage.getToken() != '{}') {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    console.log(this.form);

    this.loginInfo = new LoginInfo(this.form.username, this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken || '{}');
        this.tokenStorage.saveUsername(data.username || '{}');
        this.tokenStorage.saveAuthorities(data.authorities || []);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.token = this.tokenStorage.getToken();
        this.roles = this.tokenStorage.getAuthorities();
        this.redirectToHomePage()
      },
      error => {
        console.log(error);
        this.isLoginFailed = true;
      }
    );
  }

  redirectToHomePage() {
    window.location.href = '/home';
  }

}
