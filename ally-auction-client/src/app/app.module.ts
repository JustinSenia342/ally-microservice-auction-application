import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateAuctionComponent } from './create-auction/create-auction.component';
import { AuctionDetailsComponent } from './auction-details/auction-details.component';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateAuctionComponent } from './update-auction/update-auction.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateAuctionComponent,
    AuctionDetailsComponent,
    AuctionListComponent,
    UpdateAuctionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }