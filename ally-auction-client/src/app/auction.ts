/*import { Type } from 'class-transformer';

export class Auction {
	auctionItemId: number;
	currentBid: number;
	bidderName: string;
	maxAutoBidAmount: number;
	reservePrice: number;
	
	@Type(() => Item)
	item: Item
	
	constructor(args: Auction) {
		Object.assign(this, args);
	}
}

export class Item {
	itemId: string;
	description: string;
	
	constructor(args: Item) {
		Object.assign(this, args);
	}
}
*/

export class Item {
    itemId: string  = "";
    description: string = "";
}

export class Auction {
	auctionItemId: string = "";
	currentBid: string = "";
	bidderName: string = "";
	maxAutoBidAmount: string = "";
	reservePrice: string = "";

    item = new Item()
}

export class Bid {
    auctionItemId: string;
    maxAutoBidAmount: number;
	bidderName: string;
}