import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import {
  MatButtonModule,
  MatCardModule,
  MatChipsModule,
  MatDialogModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatPaginatorIntl,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatToolbarModule
} from '@angular/material';

import { AddShoesComponent } from './add-shoes/add-shoes.component';
import { AddVariantComponent } from './add-shoes/add-variant/add-variant.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './services/auth-guard.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FiltersComponent } from './filters/filters.component';
import { GetEmailComponent } from './shoes-detail/get-email/get-email.component';
import { Global } from './services/global.servie';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.service';
import { MatPaginatorIntlCro } from './objects/mat-paginator-intl';
import { NavbarComponent } from './navbar/navbar.component';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { OnlyNumber } from './directives/only-number.directive';
import { OrderConfirmationComponent } from './order-form/order-confirmation/order-confirmation.component';
import { OrderFormComponent } from './order-form/order-form.component';
import { OrderService } from './services/order.service';
import { RateOrderComponent } from './rate-order/rate-order.component';
import { RateService } from './services/rate.service';
import { ShoesComponent } from './shoes-list/shoes/shoes.component';
import { ShoesDetailComponent } from './shoes-detail/shoes-detail.component';
import { ShoesListComponent } from './shoes-list/shoes-list.component';
import { ShoesService } from './services/shoes.service';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { TokenInterceptor } from './services/token-interceptor';
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
    LoginComponent,
    RateOrderComponent,
    GetEmailComponent
  ],
  entryComponents: [
    AddVariantComponent,
    OrderConfirmationComponent,
    LoginComponent,
    GetEmailComponent
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
    NgbModule.forRoot(),
    ToastModule.forRoot()
  ],
  providers: [
    Global,
    ShoesService,
    OrderService,
    LoginService,
    AuthGuard,
    RateService,
    UploadImageService,
    {
      provide: MatPaginatorIntl,
      useClass: MatPaginatorIntlCro
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
