import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CommonService} from "../../shared/common/common.service";
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-reset-user-password',
  templateUrl: './reset-user-password.component.html',
  styleUrls: ['./reset-user-password.component.scss']
})
export class ResetUserPasswordComponent implements OnInit {
  validateForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CommonService,
    private http: HttpClient,
    private fb: FormBuilder,
  ) {
    this.validateForm = this.fb.group({
      username: [{value: null, disabled: true}],
      password: [null, [Validators.required]],
      passwordAgain: [null, [Validators.required, this.confirmationValidator]],
    })
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(r => {
      if (!r['id']) {
        this.service.info(`请先选择用户`)
        this.router.navigate(['/showUserList'])
      } else {
        this.http.get<any>(`/user/details/${r['id']}`).subscribe(r1 => {
          this.validateForm.patchValue({username: r1['username']})
        })
      }
    })
  }

  get password() {
    return this.validateForm.get('password') as FormControl
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return {required: true};
    } else if (control.value !== this.password.value) {
      return {confirm: true, error: true};
    }
    return {};
  };

  submitForm() {
    this.http.post(`/user/resetUserPassword`, this.validateForm.getRawValue()).subscribe(() => {
      this.service.success(`密码重置成功，即将跳转到用户列表`)
      this.router.navigate(['/showUserList'])
    })
  }
}
