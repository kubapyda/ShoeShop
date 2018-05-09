import { Component, Input, OnInit } from '@angular/core';

import { Global } from '../../services/global.servie';

@Component({
  selector: 'app-shoes',
  templateUrl: './shoes.component.html',
  styleUrls: ['./shoes.component.scss']
})
export class ShoesComponent implements OnInit {
  @Input() data: { brand: string, model: string, price: number, variantId: number };

  constructor(public global: Global) { }

  ngOnInit() { }

}
