import { Auction } from '../auction';
import { Component, OnInit, Input } from '@angular/core';
import { AuctionService } from '../auction.service';
import { AuctionListComponent } from '../auction-list/auction-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-auction-details',
  templateUrl: './auction-details.component.html',
  styleUrls: ['./auction-details.component.css']
})
export class AuctionDetailsComponent implements OnInit {

  auctionItemId: number;
  auction: Auction;
  submitted = false;

  constructor(private route: ActivatedRoute,private router: Router,
    private auctionService: AuctionService) { }

  ngOnInit() {
    this.auction = new Auction();

    this.auctionItemId = this.route.snapshot.params['auctionItemId'];
    
	//getAuctionDetails() {
    //this.auctionService
    //.getAuctionById(this.auctionItemId).subscribe(data => {
    //  console.log(data)
    //  this.auction = new Auction();
    //  this.gotoList();
    //}, 
    //error => console.log(error));
  //}
	
	//onSubmit() {
    //this.submitted = true;
    //this.getAuctionDetails();    
    //}
	
    this.auctionService.getAuctionById(this.auctionItemId)
      .subscribe(data => {
        console.log(data)
        this.auction = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['view']);
  }
}