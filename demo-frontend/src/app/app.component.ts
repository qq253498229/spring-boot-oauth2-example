import {Component, OnInit} from '@angular/core';
import {CommonService} from "./shared/common/common.service";
import {environment} from "../environments/environment";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isCollapsed = false;

  userInfo = {
    user_name: '',
    authorities: [],
  }

  constructor(
    private service: CommonService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.userInfo = this.service.user
    this.service.user$.subscribe(r => {
      this.userInfo = r
    })
  }

  sso() {
    const redirectUri = "http://localhost:4200/login"
    const clientId = 'client'
    const state = this.router.url
    const parameter = `response_type=code&redirect_uri=${redirectUri}&client_id=${clientId}&state=${state}`;
    window.location.href = `${environment.oauthUrl}/oauth/authorize?${parameter}`
  }

  logout() {
    const redirectUri = "http://localhost:4200"
    const token = this.service.token['access_token']
    this.service.user = null
    this.service.token = null
    window.location.href = `${environment.oauthUrl}/oauth/logout?token=${token}&redirect=${redirectUri}`
  }

  matchAuthority(resource: string) {
    if (!this.userInfo || !this.userInfo.authorities) {
      return false
    }
    const authorities = this.userInfo.authorities as string[]
    return authorities.indexOf(resource) > -1
  }
}
