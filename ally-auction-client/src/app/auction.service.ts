import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuctionService {

  //private baseUrl = 'http://localhost:8200/ally-auction-client';
  private baseUrl = 'http://localhost:8200';

  constructor(private http: HttpClient) { }

// @PostMapping("/auctionItems") "reservePrice": 450.00, "item": { "itemId": "abcd", "description": "item description" }
// @GetMapping("/auctionItems")
// @GetMapping("/auctionItems/{auction_item_id}")
// @PostMapping("/bids") "auctionItemId": "1", "maxAutoBidAmount": 90.11, "bidderName": "Test-BidderName6"

    // auctionItemId: number;
    // currentBid: number;
    // bidderName: string;
    // maxAutoBidAmount: number;
    // reservePrice: number;
	// item: string;

  getAuctionsList(): Observable<any> {
	return this.http.get(`${this.baseUrl}/auctionItems`);
  }

  getAuctionById(auctionItemId: number): Observable<any> {
	return this.http.get(`${this.baseUrl}/auctionItems/${auctionItemId}`);
  }
  
  postNewAuction(auction: Object): Observable<Object> {
	return this.http.post(`${this.baseUrl}/auctionItems`, auction);
  }
  
  updateAuctionById(auctionItemId: number): Observable<Object> {
	return this.http.post(`${this.baseUrl}/bids`, auctionItemId);
  }

  //getAuction(id: number): Observable<any> {
  //  return this.http.get(`${this.baseUrl}/${id}`);
  //}

  //createAuction(auction: Object): Observable<Object> {
  //  return this.http.post(`${this.baseUrl}`, auction);
  //}

  //updateAuction(id: number, value: any): Observable<Object> {
  //  return this.http.put(`${this.baseUrl}/${id}`, value);
  //}

  //deleteAuction(id: number): Observable<any> {
  //  return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  //}

  //getAuctionsList(): Observable<any> {
  //  return this.http.get(`${this.baseUrl}`);
  //}
}