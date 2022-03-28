import {Component} from '@angular/core';
import {environment} from "../environments/environment";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  username = ''

  constructor(private router: Router) {
  }

  authLogin() {
    const currentUrl = this.router.url
    const loginHost = `${environment.oauthUrl}/oauth/authorize`;
    const redirectUrl = 'http://localhost:4200/login';
    window.location.href = `${loginHost}?response_type=code&client_id=client&redirect_uri=${redirectUrl}&scope=app&state=${currentUrl}`;
  }
}
