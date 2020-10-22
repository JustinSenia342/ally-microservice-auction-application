import { AuctionService } from '../auction.service';
import { Auction } from '../auction';
import { Bid } from '../bid';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OktaAuthService } from '@okta/okta-angular';

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
  hasPathArg = false;
  userName: string;

  constructor(public oktaAuth: OktaAuthService, private route: ActivatedRoute,private auctionService: AuctionService,
  private router: Router) { }

  async ngOnInit() {
	
	this.bid = new Bid();
	
	if (this.route.snapshot.params['auctionItemId'] != null){
		this.auctionItemId = this.route.snapshot.params['auctionItemId'];
		
		if (this.auctionItemId != null){
			this.hasPathArg = true;
			this.bid.auctionItemId = this.auctionItemId.valueOf();
		}
	}
	


	// returns an array of claims
    const userClaims = await this.oktaAuth.getUser();
	
	// username is exposed as property
	this.userName = userClaims.email;

	this.bid.bidderName = this.userName;


  }


  newBid(): void {
    this.submitted = false;
    //this.bid = new Bid();
  }

  updateAuction() {

	  this.auctionService.updateAuctionById(this.bid)
		.subscribe(data => {
	    console.log(data);
		this.auctionItemId = this.bid.auctionItemId;
	    this.bid = new Bid();
	    this.gotoList(this.auctionItemId);
	  }, error => console.log(error));

  }

  onSubmit() {
	this.submitted = true;
    this.updateAuction();
  }
/*
  gotoList() {
    this.router.navigate(['/view']);
  }
*/

  gotoList(auctionItemId: number) {
    this.router.navigate(['/view', auctionItemId]);
  }
}