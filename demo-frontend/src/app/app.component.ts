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

  userInfo: any

  constructor(
    private service: CommonService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.userInfo = this.service.getUserInfo()
    console.log(this.userInfo)
  }

  sso() {
    const redirectUri = "http://localhost:4200/login"
    const clientId = 'client'
    console.log(this.router)
    //todo
    return
    window.location.href = `${environment.oauthUrl}/oauth/authorize?response_type=code&redirect_uri=${redirectUri}&client_id=${clientId}`
  }
}
