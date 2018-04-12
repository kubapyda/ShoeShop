import { Component, OnInit } from '@angular/core';

import { ShoesService } from './../services/shoes.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  searchText: string;

  constructor(public shoesService: ShoesService) { }

  ngOnInit() {
  }

  searchByPhrase() {
    this.shoesService.searchByPhrase(this.searchText);
  }

}
