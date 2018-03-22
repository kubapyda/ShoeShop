import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { FiltersComponent } from './filters/filters.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { NgModule } from '@angular/core';
import { NgSelectizeModule } from 'ng-selectize';
import { ShoeComponent } from './shoes-list/shoe/shoe.component';
import { ShoesListComponent } from './shoes-list/shoes-list.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FiltersComponent,
    ShoesListComponent,
    ShoeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgSelectizeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
