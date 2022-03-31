import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() {
  }

  getUserInfo() {
    return JSON.parse(localStorage.getItem('user') || '{}')
  }
}
