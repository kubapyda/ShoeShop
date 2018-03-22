import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css']
})
export class FiltersComponent implements OnInit {

  selectizeConfig: Object = {
    maxItems: 1,
    valueField: 'id',
    labelField: 'value'
  };

  markOptions = [
    {
      id: 1,
      value: 'Adidas'
    }, {
      id: 2,
      value: 'Nike'
    }, {
      id: 3,
      value: 'Puma'
    }, {
      id: 4,
      value: 'Umbro'
    }
  ];

  mark: number;

  constructor() { }

  ngOnInit() {
  }

}
