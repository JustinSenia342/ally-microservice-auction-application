package com.justinsenia.ally_orchestration_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.justinsenia.ally_orchestration_service.bean.AuctionBean;

@FeignClient(name="ally-auction-service", url="localhost:8200")
public interface AuctionServiceProxy {

	@GetMapping("/auctions")
	public AuctionBean getAllAuctions();
	
	@GetMapping("/auctions/{auction_item_id}")
	public AuctionBean getAuctionById(@PathVariable(value = "auction_item_id") Long auctionId);

	@PostMapping("/auction")
	public AuctionBean updateAuctionById(Long auctionItemId);
	
	

}
