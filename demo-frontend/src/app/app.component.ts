import {Component, OnInit} from '@angular/core';
import {CommonService} from "./shared/common/common.service";

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
  ) {
  }

  ngOnInit(): void {
    this.userInfo = this.service.getUserInfo()
    console.log(this.userInfo)
  }
}
