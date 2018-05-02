import { MatButtonModule, MatCardModule, MatChipsModule, MatDialogModule, MatIconModule, MatInputModule, MatListModule, MatSelectModule, MatToolbarModule } from '@angular/material';

import { AddShoesComponent } from './add-shoes/add-shoes.component';
import { AddVariantComponent } from './add-shoes/add-variant/add-variant.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FiltersComponent } from './filters/filters.component';
import { FormsModule } from '@angular/forms';
import { Global } from './services/global.servie';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './navbar/navbar.component';
import { NgModule } from '@angular/core';
import { OnlyNumber } from './directives/only-number.directive';
import { OrderService } from './services/order.service';
import { ShoesComponent } from './shoes-list/shoes/shoes.component';
import { ShoesDetailComponent } from './shoes-detail/shoes-detail.component';
import { ShoesListComponent } from './shoes-list/shoes-list.component';
import { ShoesService } from './services/shoes.service';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ToastModule } from 'ng2-toastr/ng2-toastr';

@NgModule({
  declarations: [
    AppComponent,
    AddShoesComponent,
    FiltersComponent,
    NavbarComponent,
    ShoesListComponent,
    ShoesComponent,
    AddVariantComponent,
    ShoesDetailComponent,
    ShoppingCartComponent,
    OnlyNumber
  ],
  entryComponents: [
    AddVariantComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatToolbarModule,
    MatCardModule,
    MatChipsModule,
    MatDialogModule,
    MatListModule,
    AppRoutingModule,
    HttpClientModule,
    ToastModule.forRoot()
  ],
  providers: [
    Global,
    ShoesService,
    OrderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
