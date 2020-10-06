import { AuctionService } from '../auction.service';
import { Auction } from '../auction';
import { Bid } from '../bid';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

//import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-auction',
  templateUrl: './update-auction.component.html',
  styleUrls: ['./update-auction.component.css']
})
export class UpdateAuctionComponent implements OnInit {

  auctionItemId: number;
  bid: Bid;
  submitted = false;

  constructor(private auctionService: AuctionService,
  private router: Router) { }

  ngOnInit() {
	
	this.bid = new Bid();

  }

  newBid(): void {
    this.submitted = false;
    //this.bid = new Bid();
  }

  updateAuction() {

	  this.auctionService.updateAuctionById(this.bid)
		.subscribe(data => {
	    console.log(data);
	    this.bid = new Bid();
	    this.gotoList();
	  }, error => console.log(error));

  }

  onSubmit() {
	this.submitted = true;
    this.updateAuction();
  }

  gotoList() {
    this.router.navigate(['/view']);
  }
}