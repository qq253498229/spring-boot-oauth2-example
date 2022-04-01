import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CommonService} from "../../shared/common/common.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: CommonService,
    private router: Router,
  ) {
    this.validateForm = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    })
  }

  ngOnInit(): void {
  }

  submitForm() {
    let rawValue = this.validateForm.getRawValue();
    this.service.login(rawValue.username, rawValue.password).subscribe(r => {
      this.service.token = r
      this.service.success(`登录成功`)
      this.router.navigate(['/index'])
    })
  }
}
