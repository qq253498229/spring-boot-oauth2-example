import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {catchError, mergeMap, Observable, throwError} from 'rxjs';
import {CommonService} from "./common.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private service: CommonService,
  ) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url.startsWith('/oauth/')) {
      return next.handle(request);
    }
    let token = this.service.token
    if (token != null && token != {}) {
      if (this.service.expired) {
        return this.service.refreshToken().pipe(mergeMap(() => {
          token = this.service.token
          return this.getAuthReq(request, token, next);
        }), catchError(e => {
          this.service.error(`登录状态刷新失败，请重新登录`)
          this.service.noAuth()
          return throwError(`error: ${e.error}`)
        }))
      } else {
        return this.getAuthReq(request, token, next);
      }
    } else {
      return next.handle(request);
    }
  }

  getAuthReq(request: HttpRequest<any>, token: any, next: HttpHandler) {
    const authReq = request.clone({
      headers: request.headers.set('Authorization', `${token.token_type} ${token.access_token}`)
    });
    return next.handle(authReq);
  }
}
