import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

import { AddVariantComponent } from './add-variant/add-variant.component';
import { ErrorStateMatcher } from '@angular/material/core';
import { Global } from './../services/global.servie';
import { MatDialog } from '@angular/material';
import { MyErrorStateMatcher } from './../objects/my-error-state-matcher';
import { Shoes } from './shoes';
import { ShoesService } from './../services/shoes.service';
import { ToastsManager } from 'ng2-toastr';
import { UploadImageService } from '../services/upload-image.service';
import { Variant } from './add-variant/variant';

@Component({
  selector: 'app-add-shoes',
  templateUrl: './add-shoes.component.html',
  styleUrls: ['./add-shoes.component.scss']
})
export class AddShoesComponent implements OnInit {

  matcher = new MyErrorStateMatcher();
  shoes: Shoes = new Shoes();
  brands: Array<{ value: string, viewValue: string }>;
  genders: Array<{ value: string, viewValue: string }>;
  types: Array<{ value: string, viewValue: string }>;
  shoesForm = {
    brand: new FormControl(null, [Validators.required]),
    model: new FormControl('', [Validators.required]),
    price: new FormControl(null, [Validators.required]),
    gender: new FormControl(null, [Validators.required]),
    shoeType: new FormControl(null, [Validators.required]),
    description: new FormControl(null, [Validators.required]),
  };

  colors: Object = {
    RED: 'Czerwony',
    GREEN: 'Zielony',
    WHITE: 'Biały',
    BLACK: 'Czarny',
    ORANGE: 'Pomarańczowy'
  };

  constructor(
    public dialog: MatDialog,
    public toastr: ToastsManager,
    private vcr: ViewContainerRef,
    private shoesService: ShoesService,
    private global: Global,
    private uploadImage: UploadImageService
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this.brands = this.global.brands;
    this.genders = this.global.genders;
    this.types = this.global.types;
  }

  isButtonDisabled() {
    return this.shoesForm.brand.status === 'INVALID' ||
      this.shoesForm.model.status === 'INVALID' ||
      this.shoesForm.price.status === 'INVALID' ||
      this.shoesForm.gender.status === 'INVALID' ||
      this.shoesForm.shoeType.status === 'INVALID' ||
      this.shoesForm.description.status === 'INVALID';
  }

  openDialog() {
    let dialogRef = this.dialog.open(AddVariantComponent, {
      width: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.shoes.variants.push(result);
        this.global.loaderFalse();
      }
    })
  }

  async saveShoes() {
    try {
      this.global.loaderTrue();
      const success = await this.shoesService.addShoes(this.shoes) as Shoes;
      await this.uploadImage.saveAllImage(success.variants);
      this.shoes = new Shoes();
      this.toastr.success('Pomyślnie dodano obuwie', 'Powodzenie!');
      this.global.loaderFalse();
    }
    catch (e) {
      this.toastr.error('Podczas dodawania obuwia wystąpił błąd', 'Niepowodzenie');
      this.global.loaderFalse();
    }
  }
}
