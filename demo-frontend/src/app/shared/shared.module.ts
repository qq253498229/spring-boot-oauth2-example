import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NzMenuModule} from "ng-zorro-antd/menu";
import {NzFormModule} from "ng-zorro-antd/form";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {IconsProviderModule} from "./icons-provider.module";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzTableModule} from "ng-zorro-antd/table";
import {NzInputModule} from "ng-zorro-antd/input";
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzMessageModule} from "ng-zorro-antd/message";

const THIRD_MODULES: any[] = [
  FormsModule,
  ReactiveFormsModule,
  HttpClientModule,
];
const NG_ZORRO_MODULES: any[] = [
  IconsProviderModule,
  NzLayoutModule,
  NzFormModule,
  NzMenuModule,
  NzButtonModule,
  NzTableModule,
  NzDividerModule,
  NzInputModule,
  NzSelectModule,
  NzMessageModule,
];
const COMPONENTS: any[] = [];
const DIRECTIVES: any[] = [];
const PIPES: any[] = [];

@NgModule({
  declarations: [
    ...PIPES,
    ...COMPONENTS,
    ...DIRECTIVES,
  ],
  imports: [
    CommonModule,
    ...THIRD_MODULES,
    ...NG_ZORRO_MODULES,
  ],
  exports: [
    ...THIRD_MODULES,
    ...NG_ZORRO_MODULES,
    ...PIPES,
    ...COMPONENTS,
    ...DIRECTIVES,
  ],
})
export class SharedModule {
}
