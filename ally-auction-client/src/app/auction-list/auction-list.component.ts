import { AuctionDetailsComponent } from '../auction-details/auction-details.component';
import { Observable } from "rxjs";
import { AuctionService } from "../auction.service";
import { Auction } from "../auction";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-auction-list",
  templateUrl: "./auction-list.component.html",
  styleUrls: ["./auction-list.component.css"]
})
export class AuctionListComponent implements OnInit {
  auctions: Observable<Auction[]>;

  constructor(private auctionService: AuctionService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.auctions = this.auctionService.getAuctionsList();
  }

  //deleteAuction(auctionItemId: number) {
  //  this.auctionService.deleteAuction(auctionItemId)
  //    .subscribe(
  //      data => {
  //        console.log(data);
  //        this.reloadData();
  //      },
  //      error => console.log(error));
  //}

  auctionDetails(auctionItemId: number){
    this.router.navigate(['/view', auctionItemId]);
  }
}