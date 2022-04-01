import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {NZ_I18N, zh_CN} from 'ng-zorro-antd/i18n';
import {registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from "./shared/shared.module";
import {IndexComponent} from './pages/index/index.component';
import {LoginGuard} from "./shared/guard/login.guard";
import {ShowPersonalRoleComponent} from './pages/show-personal-role/show-personal-role.component';
import {UpdatePersonalDetailComponent} from './pages/update-personal-detail/update-personal-detail.component';
import {ShowUserListComponent} from './pages/show-user-list/show-user-list.component';
import {ShowUserDetailComponent} from './pages/show-user-detail/show-user-detail.component';
import {ResetUserPasswordComponent} from './pages/reset-user-password/reset-user-password.component';
import {ShowPersonalDetailComponent} from './pages/show-personal-detail/show-personal-detail.component';
import {AuthGuard} from "./shared/guard/auth.guard";
import {LoginComponent} from './pages/login/login.component';
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "./shared/common/auth.interceptor";
import {RegisterComponent} from './pages/register/register.component';

registerLocaleData(zh);

const routes: Routes = [
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'index', component: IndexComponent},
  {path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  {
    path: '',
    canActivateChild: [AuthGuard], children: [
      {path: 'showPersonalRole', component: ShowPersonalRoleComponent},
      {path: 'updatePersonalDetail', component: UpdatePersonalDetailComponent},
      {path: 'showUserList', component: ShowUserListComponent},
      {path: 'showUserDetail', component: ShowUserDetailComponent},
      {path: 'resetUserPassword', component: ResetUserPasswordComponent},
      {path: 'showPersonalDetail', component: ShowPersonalDetailComponent},
    ]
  },
  {path: 'register', component: RegisterComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ShowPersonalRoleComponent,
    UpdatePersonalDetailComponent,
    ShowUserListComponent,
    ShowUserDetailComponent,
    ResetUserPasswordComponent,
    ShowPersonalDetailComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    SharedModule,
  ],
  providers: [
    {provide: NZ_I18N, useValue: zh_CN},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
