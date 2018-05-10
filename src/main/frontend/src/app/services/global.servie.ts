import { Injectable } from '@angular/core';

@Injectable()
export class Global {
  loader: boolean = false;
  apiAddress: string = 'http://localhost:8080';

  colors: Array<{ value: string, viewValue: string }> = [{
    value: 'RED',
    viewValue: 'Czerwone'
  }, {
    value: 'GREEN',
    viewValue: 'Zielone'
  }, {
    value: 'WHITE',
    viewValue: 'Białe'
  }, {
    value: 'BLACK',
    viewValue: 'Czarne'
  }, {
    value: 'ORANGE',
    viewValue: 'Pomarańczowe'
  }];

  brands: Array<{ value: string, viewValue: string }> = [{
    value: 'NIKE',
    viewValue: 'Nike'
  }, {
    value: 'ADIDAS',
    viewValue: 'Adidas'
  }, {
    value: 'REEBOK',
    viewValue: 'Reebok'
  }, {
    value: 'PUMA',
    viewValue: 'Puma'
  }];

  genders: Array<{ value: string, viewValue: string }> = [{
    value: 'MEN',
    viewValue: 'Mężczyźni'
  }, {
    value: 'WOMEN',
    viewValue: 'Kobiety'
  }, {
    value: 'BOYS',
    viewValue: 'Chłopcy'
  }, {
    value: 'GIRLS',
    viewValue: 'Dziewczynki'
  }];

  types: Array<{ value: string, viewValue: string }> = [{
    value: 'SNEAKERS',
    viewValue: 'Trampki'
  }, {
    value: 'SKATE',
    viewValue: 'Skatowe'
  }, {
    value: 'HEELS',
    viewValue: 'Na obcasach'
  }, {
    value: 'RUNNING',
    viewValue: 'Do biegania'
  }, {
    value: 'SLIPPERS',
    viewValue: 'Kapcie'
  }, {
    value: 'SANDALS',
    viewValue: 'Sandały'
  }];

  loaderTrue() {
    setTimeout(() => this.loader = true);
  }

  loaderFalse() {
    setTimeout(() => this.loader = false);
  }
}
