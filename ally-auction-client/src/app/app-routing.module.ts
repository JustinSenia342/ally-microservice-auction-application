import { AuctionDetailsComponent } from './auction-details/auction-details.component';
import { CreateAuctionComponent } from './create-auction/create-auction.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { UpdateAuctionComponent } from './update-auction/update-auction.component';
import { OktaAuthGuard } from '@okta/okta-angular';

const routes: Routes = [
  { path: 'auctions', component: AuctionListComponent, canActivate: [OktaAuthGuard] },
  { path: 'add', component: CreateAuctionComponent, canActivate: [OktaAuthGuard] },
  { path: 'bid', component: UpdateAuctionComponent, canActivate: [OktaAuthGuard] },
  { path: 'bid/:auctionItemId', component: UpdateAuctionComponent, canActivate: [OktaAuthGuard] },
  { path: 'view', component: AuctionDetailsComponent, canActivate: [OktaAuthGuard] },
  { path: 'view/:auctionItemId', component: AuctionDetailsComponent, canActivate: [OktaAuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }