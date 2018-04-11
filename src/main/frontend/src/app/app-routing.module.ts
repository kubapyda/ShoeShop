import { RouterModule, Routes } from "@angular/router";

import { AddShoesComponent } from './add-shoes/add-shoes.component';
import { NgModule } from "@angular/core";
import { ShoesListComponent } from "./shoes-list/shoes-list.component";

const appRoutes: Routes = [
  {
    path: '',
    component: ShoesListComponent
  }, {
    path: 'add',
    component: AddShoesComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
