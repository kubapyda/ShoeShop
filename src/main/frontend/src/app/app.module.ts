import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule, MatCardModule, MatChipsModule, MatDialogModule, MatIconModule, MatInputModule, MatListModule, MatPaginatorIntl, MatPaginatorModule, MatProgressSpinnerModule, MatSelectModule, MatToolbarModule } from '@angular/material';

import { AddShoesComponent } from './add-shoes/add-shoes.component';
import { AddVariantComponent } from './add-shoes/add-variant/add-variant.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './services/auth-guard.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FiltersComponent } from './filters/filters.component';
import { Global } from './services/global.servie';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.service';
import { MatPaginatorIntlCro } from './objects/mat-paginator-intl';
import { NavbarComponent } from './navbar/navbar.component';
import { NgModule } from '@angular/core';
import { OnlyNumber } from './directives/only-number.directive';
import { OrderConfirmationComponent } from './order-form/order-confirmation/order-confirmation.component';
import { OrderFormComponent } from './order-form/order-form.component';
import { OrderService } from './services/order.service';
import { ShoesComponent } from './shoes-list/shoes/shoes.component';
import { ShoesDetailComponent } from './shoes-detail/shoes-detail.component';
import { ShoesListComponent } from './shoes-list/shoes-list.component';
import { ShoesService } from './services/shoes.service';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { UploadImageService } from './services/upload-image.service';

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
    OnlyNumber,
    OrderFormComponent,
    OrderConfirmationComponent,
    LoginComponent
  ],
  entryComponents: [
    AddVariantComponent,
    OrderConfirmationComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
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
    MatProgressSpinnerModule,
    MatPaginatorModule,
    AppRoutingModule,
    HttpClientModule,
    ToastModule.forRoot()
  ],
  providers: [
    Global,
    ShoesService,
    OrderService,
    LoginService,
    AuthGuard,
    UploadImageService,
    {
      provide: MatPaginatorIntl,
      useClass: MatPaginatorIntlCro
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
