import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-show-personal-role',
  templateUrl: './show-personal-role.component.html',
  styleUrls: ['./show-personal-role.component.scss']
})
export class ShowPersonalRoleComponent implements OnInit {
  list: any = []

  constructor(
    private http: HttpClient,
  ) {
  }

  ngOnInit(): void {
    this.http.get(`/user/showPersonalRole`).subscribe(r => {
      this.list = r
    })
  }

}
