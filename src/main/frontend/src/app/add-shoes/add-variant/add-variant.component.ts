import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

import { Global } from './../../services/global.servie';
import { MatDialogRef } from '@angular/material';
import { MyErrorStateMatcher } from './../../objects/my-error-state-matcher';
import { UploadImageService } from './../../services/upload-image.service';
import { Variant } from './variant';

@Component({
  selector: 'app-add-variant',
  templateUrl: './add-variant.component.html',
  styleUrls: ['./add-variant.component.scss']
})
export class AddVariantComponent implements OnInit {

  colors: Array<{ value: string, viewValue: string }>;
  variants: Variant = new Variant();
  matcher = new MyErrorStateMatcher();
  selectedImage: File;
  variantsForm = {
    shankColor: new FormControl(null, [Validators.required]),
    soleColor: new FormControl(null, [Validators.required]),
  };

  constructor(
    public dialogRef: MatDialogRef<AddVariantComponent>,
    private uploadImage: UploadImageService,
    private global: Global
  ) { }

  ngOnInit() {
    this.colors = this.global.colors;
    this.addVariant();
  }

  selectImage(event) {
    this.selectedImage = <File>event.target.files[0];
    this.uploadImage.saveImage(this.selectedImage);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveVariant(): void {
    this.global.loaderTrue();
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
