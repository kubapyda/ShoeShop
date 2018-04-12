import { Component, OnInit } from '@angular/core';

import { ShoesService } from '../services/shoes.service';

@Component({
  selector: 'app-shoes-list',
  templateUrl: './shoes-list.component.html',
  styleUrls: ['./shoes-list.component.scss']
})
export class ShoesListComponent implements OnInit {

  constructor(public shoesService: ShoesService) { }

  ngOnInit() {
    this.shoesService.findShoes();
  }

}
