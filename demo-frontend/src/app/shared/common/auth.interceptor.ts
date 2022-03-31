import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CommonService} from "./common.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private service: CommonService,
  ) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.url.startsWith('/oauth/')) {
      return next.handle(request);
    }
    const token = this.service.token
    const authReq = request.clone({
      headers: request.headers.set('Authorization', `${token.token_type} ${token.access_token}`)
    });
    return next.handle(authReq);
  }
}
