import { HttpClient, HttpHeaders } from "@angular/common/http";

import { Global } from './global.servie';
import { Injectable } from "@angular/core";
import { LoginService } from './login.service';
import { ToastsManager } from 'ng2-toastr';
import { Variant } from './../add-shoes/add-variant/variant';

@Injectable()
export class UploadImageService {
  private url: string;
  private imageVariants: Array<FormData> = [];

  constructor(
    private http: HttpClient,
    private global: Global,
    private loginService: LoginService,
    private toastr: ToastsManager
  ) {
    this.url = `${global.apiAddress}/shoes`;
  }

  saveImage(file: File) {
    let formData: FormData = new FormData();
    formData.append('file', file);
    this.imageVariants.push(formData);
  }

  saveSingleImage(id: number, variantImage: FormData) {
    const headers = new HttpHeaders({ 'Authorization': this.loginService.getToken() });
    return this.http.post(`${this.url}/${id}/picture`, variantImage, { headers: headers }).subscribe(success => { }, err => {
      this.toastr.error(`Podczas dodawania zdjęcia do wariantu o identyfikatorze ${id} wystąpił problem`);
    });
  }

  async asyncSaveImage(id: number, variantImage: FormData) {
    await this.saveSingleImage(id, variantImage);
  }

  async saveAllImage(variants: Array<Variant>) {
    const savePromises = variants.map((variant, i) => this.asyncSaveImage(variant.id, this.imageVariants[i]));
    return Promise.all(savePromises);
  }
}
