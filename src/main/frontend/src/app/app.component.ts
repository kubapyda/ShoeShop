import { Component } from '@angular/core';
import { Global } from './services/global.servie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(public global: Global) { }
}
