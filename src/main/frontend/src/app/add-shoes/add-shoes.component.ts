import { Component, OnInit, ViewContainerRef } from '@angular/core';

import { AddVariantComponent } from './add-variant/add-variant.component';
import { Global } from './../services/global.servie';
import { MatDialog } from '@angular/material';
import { Shoes } from './shoes';
import { ShoesService } from './../services/shoes.service';
import { ToastsManager } from 'ng2-toastr';
import { Variant } from './add-variant/variant';

@Component({
  selector: 'app-add-shoes',
  templateUrl: './add-shoes.component.html',
  styleUrls: ['./add-shoes.component.scss'],
  providers: [ShoesService]
})
export class AddShoesComponent implements OnInit {

  shoes: Shoes = new Shoes();
  brands: Array<{ value: string, viewValue: string }>;
  genders: Array<{ value: string, viewValue: string }>;
  types: Array<{ value: string, viewValue: string }>;

  colors: Object = {
    RED: 'Czerwony',
    GREEN: 'Zielony',
    WHITE: 'Biały',
    BLACK: 'Czarny',
    ORANGE: 'Pomarańczowy'
  };

  constructor(public dialog: MatDialog,
    public toastr: ToastsManager,
    private vcr: ViewContainerRef,
    private shoesService: ShoesService,
    private global: Global) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this.brands = this.global.brands;
    this.genders = this.global.genders;
    this.types = this.global.types;
  }

  openDialog() {
    let dialogRef = this.dialog.open(AddVariantComponent, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.shoes.variants.push(result);
      }
    })
  }

  saveShoes(): void {
    this.shoesService.addShoes(this.shoes).subscribe(success => {
      this.toastr.success('Pomyślnie dodano obuwie');
    }, err => {
      this.toastr.error('Podczas dodawania obuwia wystąpił błąd');
    });
  }

}
