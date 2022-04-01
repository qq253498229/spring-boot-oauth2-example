import {Injectable} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {map, Observable} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {CommonService} from "../common/common.service";

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate, CanActivateChild {
  constructor(
    private http: HttpClient,
    private router: Router,
    private service: CommonService,
  ) {
  }

  getTokenByCode(code: string, state: string | null): Observable<boolean> {
    const grantType = 'authorization_code'
    const param = `grant_type=${grantType}&code=${code}&redirect_uri=http://localhost:4200/login`
    const url = `/oauth/token?${param}`;
    const headers = {
      'Authorization': `Basic ${btoa('client:secret')}`
    }
    return this.http.post(url, null, {headers}).pipe(map(r => {
      this.service.token = r
      this.service.success(`登录成功`)
      if (!!state) {
        this.router.navigate([state])
      } else {
        this.router.navigate(['/index'])
      }
      return false
    }))
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const code = route.queryParamMap.get('code')
    if (!!code) {
      const state = route.queryParamMap.get('state')
      return this.getTokenByCode(code, state)
    }
    return true;
  }

  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log('canActivateChild')
    return true;
  }
}
