import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  private tokenSource = new Subject<any>()
  private userSource = new Subject<any>()
  token$ = this.tokenSource.asObservable()
  user$ = this.userSource.asObservable()

  constructor() {
  }

  get user() {
    return JSON.parse(localStorage.getItem('user') || '{}')
  }

  set user(user: any) {
    localStorage.setItem('user', JSON.stringify(user))
    this.userSource.next(user)
  }

  get token() {
    return JSON.parse(localStorage.getItem('token') || '{}')
  }

  set token(token: any) {
    if (!!token) {
      const user = atob(token['access_token'].split('.')[1])
      this.user = JSON.parse(user)
    }
    localStorage.setItem('token', JSON.stringify(token))
    this.tokenSource.next(token)
  }
}
