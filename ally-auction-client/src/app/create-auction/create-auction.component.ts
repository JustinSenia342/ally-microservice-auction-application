import { AuctionService } from '../auction.service';
import { Auction } from '../auction';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-auction',
  templateUrl: './create-auction.component.html',
  styleUrls: ['./create-auction.component.css']
})
export class CreateAuctionComponent implements OnInit {

  auction: Auction = new Auction();
  submitted = false;

  constructor(private auctionService: AuctionService,
    private router: Router) { }

  ngOnInit() {
  }

  newAuction(): void {
    this.submitted = false;
    this.auction = new Auction();
  }

  save() {
    this.auctionService
    .postNewAuction(this.auction).subscribe(data => {
      console.log(data)
      this.auction = new Auction();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/auctions']);
  }
}