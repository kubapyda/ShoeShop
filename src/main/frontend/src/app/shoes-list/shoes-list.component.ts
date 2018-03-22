import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shoes-list',
  templateUrl: './shoes-list.component.html',
  styleUrls: ['./shoes-list.component.css']
})
export class ShoesListComponent implements OnInit {

  shoes: Array<{ mark: string, name: string, imagePath: string, price: number }> = [
    {
      mark: 'Nike',
      name: 'AirMax',
      imagePath: 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/800x800//0/0/0000200451596_01_jf.jpg',
      price: 349.99
    },
    {
      mark: 'Adidas',
      name: 'Gazelle',
      imagePath: 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/800x800//0/0/0000198767068_adidas-s76228_scarle_ftwwht_goldmt_mp_01.jpg',
      price: 249.99
    },
    {
      mark: 'Nike',
      name: 'Air Zoom',
      imagePath: 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650//0/0/0000200451572_01_ab.jpg',
      price: 399.99
    },
    {
      mark: 'Adidas',
      name: 'Powerlift',
      imagePath: 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/800x800//0/0/0000200470566_01_ks.jpg',
      price: 449.99
    }
  ]

  constructor() { }

  ngOnInit() {
  }

}
