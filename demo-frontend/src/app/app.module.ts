import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {IndexComponent} from './pages/index/index.component';
import {UserListComponent} from './pages/user-list/user-list.component';
import {LoginComponent} from './pages/login/login.component';
import {LoginGuard} from "./common/login.guard";

const routes: Routes = [
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'index', component: IndexComponent},
  {path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  {
    path: 'user', children: [
      {path: 'list', component: UserListComponent},
    ]
  },
]

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    LoginComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
