import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-shoes',
  templateUrl: './shoes.component.html',
  styleUrls: ['./shoes.component.scss']
})
export class ShoesComponent implements OnInit {
  @Input() data: { mark: string, name: string, imagePath: string, price: number };

  constructor() { }

  ngOnInit() {
  }

}
