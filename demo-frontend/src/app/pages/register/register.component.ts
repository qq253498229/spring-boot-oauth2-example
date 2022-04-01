import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CommonService} from "../../shared/common/common.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  validateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private service: CommonService,
  ) {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      passwordAgain: [null, [Validators.required, this.confirmationValidator]],
      agree: [false],
    })
  }

  ngOnInit(): void {
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
    if (this.validateForm.get('agree')?.value === false) {
      this.service.info(`请阅读并同意协议`)
      return
    }
    this.http.post(`/oauth/register`, this.validateForm.getRawValue()).subscribe({
      next: () => {
        this.service.success(`注册成功，即将跳转到登录页面`)
        this.router.navigate(['/login'])
      },
      error: (e) => {
        if (e?.status === 400 && e?.error?.errors[0]?.defaultMessage) {
          this.service.error(`注册失败：${e.error.errors[0].defaultMessage}`)
        } else {
          this.service.error(`注册失败`)
        }
      }
    })
  }

  agreement() {
    this.service.info(`哎，啥都没有`)
  }
}
