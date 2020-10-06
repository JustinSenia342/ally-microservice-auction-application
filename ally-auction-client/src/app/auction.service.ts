import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuctionService {

  private baseUrl = 'http://localhost:8200';

  constructor(private http: HttpClient) { }

  getAuctionsList(): Observable<any> {
	return this.http.get(`${this.baseUrl}/auctionItems`);
  }

  getAuctionById(auctionItemId: number): Observable<any> {
	return this.http.get(`${this.baseUrl}/auctionItems/${auctionItemId}`);
  }
  
  postNewAuction(auction: Object): Observable<Object> {
	return this.http.post(`${this.baseUrl}/auctionItems`, auction);
  }
  
  updateAuctionById(bid: Object): Observable<Object> {
	return this.http.post(`${this.baseUrl}/bids`, bid);
  }

  deleteAuctionById(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/auctionItems/${id}`, { responseType: 'text' });
  }

}