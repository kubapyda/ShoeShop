import { RouterModule, Routes } from "@angular/router";

import { AddShoesComponent } from './add-shoes/add-shoes.component';
import { NgModule } from "@angular/core";
import { ShoesDetailComponent } from './shoes-detail/shoes-detail.component';
import { ShoesListComponent } from "./shoes-list/shoes-list.component";
import { ShoppingCartComponent } from "./shopping-cart/shopping-cart.component";

const appRoutes: Routes = [
  {
    path: '',
    component: ShoesListComponent
  }, {
    path: 'add',
    component: AddShoesComponent
  }, {
    path: 'shoes/:id/variant/:variantId',
    component: ShoesDetailComponent
  }, {
    path: 'shopping-cart',
    component: ShoppingCartComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
