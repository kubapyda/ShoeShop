import { Component, OnInit } from '@angular/core';

import { Global } from './../../services/global.servie';
import { MatDialogRef } from '@angular/material';
import { Variant } from './variant';

@Component({
  selector: 'app-add-variant',
  templateUrl: './add-variant.component.html',
  styleUrls: ['./add-variant.component.scss']
})
export class AddVariantComponent implements OnInit {

  colors: Array<{ value: string, viewValue: string }>;
  variants: Variant = new Variant();

  constructor(
    public dialogRef: MatDialogRef<AddVariantComponent>,
    private global: Global
  ) { }

  ngOnInit() {
    this.colors = this.global.colors;
    this.addVariant();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveVariant(): void {
    this.dialogRef.close(this.variants);
  }

  addVariant(): void {
    this.variants.sizedShoes.push({
      size: null,
      quantity: null
    });
  }

  removeVariant(index: number): void {
    this.variants.sizedShoes.splice(index, 1);
  }

}
