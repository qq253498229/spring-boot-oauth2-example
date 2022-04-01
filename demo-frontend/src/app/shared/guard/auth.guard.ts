import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {catchError, map, Observable, throwError} from 'rxjs';
import {CommonService} from "../common/common.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivateChild {

  constructor(
    private service: CommonService,
    private router: Router,
  ) {
  }

  canActivateChild(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let user = this.service.user;
    if (user == null || user == {}) {
      this.router.navigate(['/index'])
      return false
    }
    if (this.service.expired) {
      return this.service.refreshToken().pipe(map(r => {
        if (r) {
          return true
        } else {
          this.service.info(`登录已过期，请重新登录`)
          this.router.navigate(['/index'])
          return false
        }
      }), catchError(e => {
        this.service.error(`登录状态刷新失败`)
        return throwError(`error: ${e.error}`)
      }))
    }
    return true;
  }
}
