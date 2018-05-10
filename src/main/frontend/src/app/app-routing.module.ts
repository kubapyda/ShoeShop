import { RouterModule, Routes } from "@angular/router";

import { AddShoesComponent } from './add-shoes/add-shoes.component';
import { AuthGuard } from "./services/auth-guard.service";
import { LoginComponent } from './login/login.component';
import { NgModule } from "@angular/core";
import { OrderFormComponent } from './order-form/order-form.component';
import { ShoesDetailComponent } from './shoes-detail/shoes-detail.component';
import { ShoesListComponent } from "./shoes-list/shoes-list.component";
import { ShoppingCartComponent } from "./shopping-cart/shopping-cart.component";

const appRoutes: Routes = [
  {
    path: '',
    component: ShoesListComponent
  }, {
    path: 'add',
    component: AddShoesComponent,
    canActivate: [AuthGuard]
  }, {
    path: 'shoes/:id/variant/:variantId',
    component: ShoesDetailComponent
  }, {
    path: 'shopping-cart',
    component: ShoppingCartComponent
  }, {
    path: 'order-form',
    component: OrderFormComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
