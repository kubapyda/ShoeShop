import { Component, OnInit } from '@angular/core';

import { ShoesService } from '../services/shoes.service';

@Component({
  selector: 'app-shoes-list',
  templateUrl: './shoes-list.component.html',
  styleUrls: ['./shoes-list.component.scss']
})
export class ShoesListComponent implements OnInit {

  page: number = 0;

  constructor(public shoesService: ShoesService) { }

  ngOnInit() {
    this.shoesService.findShoes(this.page);
  }

  changePage(event) {
    this.page = event.pageIndex;
    this.shoesService.findShoes(this.page);
  }

}
