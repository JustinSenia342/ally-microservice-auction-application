import { Component, OnInit } from '@angular/core';
import { Auction } from '../auction';
import { ActivatedRoute, Router } from '@angular/router';
import { AuctionService } from '../auction.service';

@Component({
  selector: 'app-update-auction',
  templateUrl: './update-auction.component.html',
  styleUrls: ['./update-auction.component.css']
})
export class UpdateAuctionComponent implements OnInit {

  auctionItemId: number;
  auction: Auction;

  constructor(private route: ActivatedRoute,private router: Router,
    private auctionService: AuctionService) { }

  ngOnInit() {
    this.auction = new Auction();

    this.auctionItemId = this.route.snapshot.params['auctionItemId'];
    
    this.auctionService.getAuctionById(this.auctionItemId)
      .subscribe(data => {
        console.log(data)
        this.auction = data;
      }, error => console.log(error));
  }

  updateAuction() {
    this.auctionService.updateAuctionById(this.auctionItemId)
      .subscribe(data => {
        console.log(data);
        this.auction = new Auction();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateAuction();    
  }

  gotoList() {
    this.router.navigate(['/auctions']);
  }
}