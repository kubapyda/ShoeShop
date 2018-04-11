import { Component, OnInit, ViewContainerRef } from '@angular/core';

import { AddVariantComponent } from './add-variant/add-variant.component';
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

  brands: Array<{ value: string, viewValue: string }> = [{
    value: 'NIKE',
    viewValue: 'Nike'
  }, {
    value: 'ADIDAS',
    viewValue: 'Adidas'
  }, {
    value: 'REEBOK',
    viewValue: 'Reebok'
  }, {
    value: 'PUMA',
    viewValue: 'Puma'
  }];

  genders: Array<{ value: string, viewValue: string }> = [{
    value: 'MEN',
    viewValue: 'Mężczyźni'
  }, {
    value: 'WOMEN',
    viewValue: 'Kobiety'
  }, {
    value: 'BOYS',
    viewValue: 'Chłopcy'
  }, {
    value: 'GIRLS',
    viewValue: 'Dziewczynki'
  }];

  types: Array<{ value: string, viewValue: string }> = [{
    value: 'SNEAKERS',
    viewValue: 'Trampki'
  }, {
    value: 'SKATE',
    viewValue: 'Skatowe'
  }, {
    value: 'HEELS',
    viewValue: 'Na obcasach'
  }, {
    value: 'RUNNING',
    viewValue: 'Do biegania'
  }, {
    value: 'SLIPPERS',
    viewValue: 'Kapcie'
  }, {
    value: 'SANDALS',
    viewValue: 'Sandały'
  }];

  colors: Object = {
    RED: 'Czerwony',
    GREEN: 'Zielony',
    WHITE: 'Biały',
    BLACK: 'Czarny',
    ORANGE: 'Pomarańczowy'
  };

  constructor(public dialog: MatDialog,
    public toastr: ToastsManager,
    vcr: ViewContainerRef,
    private shoesService: ShoesService) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() { }

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
