import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CommonService} from "../../shared/common/common.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-show-user-detail',
  templateUrl: './show-user-detail.component.html',
  styleUrls: ['./show-user-detail.component.scss']
})
export class ShowUserDetailComponent implements OnInit {
  details: any = {
    name: undefined,
    age: undefined,
    email: undefined,
    gender: undefined,
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CommonService,
    private http: HttpClient,
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(r => {
      if (!r['id']) {
        this.service.info(`请先选择用户`)
        this.router.navigate(['/showUserList'])
      } else {
        this.http.get(`/user/details/${r['id']}`).subscribe(r1 => {
          this.details = r1
        })
      }
    })
  }

}
