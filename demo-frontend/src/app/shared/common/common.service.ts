import {Injectable} from '@angular/core';
import {map, Observable, Subject} from "rxjs";
import {NzMessageService} from "ng-zorro-antd/message";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  private tokenSource = new Subject<any>()
  private userSource = new Subject<any>()
  token$ = this.tokenSource.asObservable()
  user$ = this.userSource.asObservable()

  constructor(
    private message: NzMessageService,
    private http: HttpClient,
    private router: Router,
  ) {
  }

  get user() {
    return JSON.parse(localStorage.getItem('user') || '{}')
  }

  set user(user: any) {
    localStorage.setItem('user', JSON.stringify(user))
    this.userSource.next(user)
  }

  get expired() {
    // 提前10秒，避免踩点过期
    return this.user.exp * 1000 - 10000 < new Date().getTime()
  }

  clearAuth() {
    this.user = null
    this.token = null
  }

  noAuth() {
    this.clearAuth()
    this.router.navigate(['/index'])
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

  success(message = `保存成功，正在返回列表`) {
    this.message.success(message)
  }

  error(message = `保存失败，请联系管理员`) {
    this.message.error(message)
  }

  info(message = `警告`) {
    this.message.info(message)
  }

  refreshToken(): Observable<boolean> {
    let param = `grant_type=refresh_token&refresh_token=${this.token.refresh_token}`
    let url = `/oauth/token?${param}`
    let headers = {
      'Authorization': `Basic ${btoa('client:secret')}`
    }
    return this.http.post(url, null, {headers}).pipe(map(r => {
      this.token = r
      return true
    }))
  }

  login(username: string, password: string) {
    let param = `grant_type=password&username=${username}&password=${password}`
    let url = `/oauth/token?${param}`
    let headers = {
      'Authorization': `Basic ${btoa('client:secret')}`
    }
    return this.http.post(url, null, {headers})
  }
}
