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

registerLocaleData(zh);

const routes: Routes = [
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'index', component: IndexComponent},
  {path: 'login', canActivate: [LoginGuard], component: LoginComponent},
  {path: 'showPersonalRole', canActivate: [AuthGuard], component: ShowPersonalRoleComponent},
  {path: 'updatePersonalDetail', canActivate: [AuthGuard], component: UpdatePersonalDetailComponent},
  {path: 'showUserList', canActivate: [AuthGuard], component: ShowUserListComponent},
  {path: 'showUserDetail', canActivate: [AuthGuard], component: ShowUserDetailComponent},
  {path: 'resetUserPassword', canActivate: [AuthGuard], component: ResetUserPasswordComponent},
  {path: 'showPersonalDetail', canActivate: [AuthGuard], component: ShowPersonalDetailComponent},
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
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    SharedModule,
  ],
  providers: [{provide: NZ_I18N, useValue: zh_CN}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
