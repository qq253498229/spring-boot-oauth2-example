import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormGroup} from "@angular/forms";
import {CommonService} from "../../shared/common/common.service";

@Component({
  selector: 'app-update-personal-detail',
  templateUrl: './update-personal-detail.component.html',
  styleUrls: ['./update-personal-detail.component.scss']
})
export class UpdatePersonalDetailComponent implements OnInit {
  validateForm: FormGroup

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private service: CommonService,
  ) {
    this.validateForm = this.fb.group({
      id: [],
      userId: [],
      name: [],
      age: [],
      email: [],
      gender: [],
    })
  }

  ngOnInit(): void {
    this.http.get(`/user/showPersonalDetail`).subscribe(r => {
      this.validateForm.patchValue(r)
    })
  }

  submitForm() {
    this.http.post(`/user/updatePersonalDetail`, this.validateForm.getRawValue()).subscribe(() => {
      this.service.success(`保存成功`)
    })
  }
}
