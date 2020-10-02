import { AuctionDetailsComponent } from './auction-details/auction-details.component';
import { CreateAuctionComponent } from './create-auction/create-auction.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { UpdateAuctionComponent } from './update-auction/update-auction.component';

const routes: Routes = [
  { path: '', redirectTo: 'auction', pathMatch: 'full' },
  { path: 'auctions', component: AuctionListComponent },
  { path: 'add', component: CreateAuctionComponent },
  //{ path: 'update/:id', component: UpdateAuctionComponent },
  { path: 'bid', component: UpdateAuctionComponent },
  //{ path: 'details/:id', component: AuctionDetailsComponent },
  { path: 'view/:auctionItemId', component: AuctionDetailsComponent },
  { path: 'view', component: AuctionDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }