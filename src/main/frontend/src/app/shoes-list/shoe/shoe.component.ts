import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-shoe',
  templateUrl: './shoe.component.html',
  styleUrls: ['./shoe.component.css']
})
export class ShoeComponent implements OnInit {
  @Input() data: { mark: string, name: string, imagePath: string, price: number };

  constructor() { }

  ngOnInit() {
  }

}
