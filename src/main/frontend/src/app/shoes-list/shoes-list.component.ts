import { Component, OnInit } from '@angular/core';

import { ShoesService } from '../services/shoes.service';

@Component({
  selector: 'app-shoes-list',
  templateUrl: './shoes-list.component.html',
  styleUrls: ['./shoes-list.component.scss'],
  providers: [ShoesService]
})
export class ShoesListComponent implements OnInit {

  shoes: any;

  constructor(private shoesService: ShoesService) { }

  ngOnInit() {
    this.shoesService.findShoes().subscribe(data => {
      this.shoes = data;
    });
  }

}
