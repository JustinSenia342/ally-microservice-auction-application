export class Item {
    itemId: string;
    description: string;
}

export class Auction {
	auctionItemId: number;
	bidderName: string;
	currentBid: number;
	maxAutoBidAmount: number;
	reservePrice: number;
    item = new Item()
}
