import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-show-personal-detail',
  templateUrl: './show-personal-detail.component.html',
  styleUrls: ['./show-personal-detail.component.scss']
})
export class ShowPersonalDetailComponent implements OnInit {
  details: any = {
    name: undefined,
    age: undefined,
    email: undefined,
    gender: undefined,
  }

  constructor(
    private http: HttpClient,
  ) {
  }

  ngOnInit(): void {
    this.http.get(`/user/showPersonalDetail`).subscribe(r => {
      this.details = r
    })
  }

}
