import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'gender'
})
export class GenderPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    if (value === 1) {
      return '男'
    } else if (value === 2) {
      return '女'
    } else if (value === 3) {
      return '其它'
    } else {
      return ''
    }
  }

}
