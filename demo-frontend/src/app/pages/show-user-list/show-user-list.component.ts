import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-show-user-list',
  templateUrl: './show-user-list.component.html',
  styleUrls: ['./show-user-list.component.scss']
})
export class ShowUserListComponent implements OnInit {
  dataSet: any = []

  constructor(
    private http: HttpClient,
    private router: Router) {
  }

  ngOnInit(): void {
    this.load()
  }

  load() {
    this.http.get(`/user`).subscribe(r => {
      this.dataSet = r
    })
  }

  details(data: any) {
    this.router.navigate(['/showUserDetail'], {queryParams: {id: data.id}})
  }

  changePassword(data: any) {
    this.router.navigate(['/resetUserPassword'], {queryParams: {id: data.id}})
  }
}
