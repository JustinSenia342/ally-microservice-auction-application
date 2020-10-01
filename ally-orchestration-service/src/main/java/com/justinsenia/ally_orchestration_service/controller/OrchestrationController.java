package com.justinsenia.ally_orchestration_service.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

//import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import com.justinsenia.ally_orchestration_service.bean.AuctionBean;
import com.justinsenia.ally_orchestration_service.bean.BidBean;
import com.justinsenia.ally_orchestration_service.proxy.AuctionServiceProxy;

import lombok.Getter;
import lombok.Setter;

import com.justinsenia.ally_orchestration_service.bean.OrchestrationBean;


@RequestMapping(path="/api/v1")
@RestController
public class OrchestrationController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	// POST /auctionItems
	// Creates new auction using API
	/*@PostMapping("/auctionItems")
	public AuctionBean createAuction(@Valid @RequestBody AuctionBean auctionBean, HttpServletRequest request) {
	

		//String url = "http://localhost:8200/api/v1/auctions";
		//String requestJson = "{\"reservePrice\": 10000.00, \"itemId\": \"TestItemId\", \"description\": \"TestDescription\"}";
		
		final String uri = "http://localhost:8200/api/v1/auctions";
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("reservePrice", "10000.00");
		uriVariables.put("itemId", "TestItemId");
		uriVariables.put("description", "TestDescription");
		

	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setDefaultUriVariables(uriVariables);
	    
	    String result = restTemplate.postForLocation(uri, String.class);
	    
	    String result = restTemplate.getForObject(uri, String.class);
	    //
	    uriVariables.put("id", auctionItemId);
		
		ResponseEntity<AuctionBean> responseAuctionEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/api/v1/auctions", AuctionBean.class, uriVariables);
		
		AuctionBean auctionResponse = responseAuctionEntity.getBody();
		
		return new AuctionBean(auctionItemId, auctionResponse.getCurrentBid(), auctionResponse.getBidderName(), auctionResponse.getReservePrice(), auctionResponse.getItemId(), auctionResponse.getDescription() );
		
	}
	    
	  */  
		
	    /*
	     * 
	    RestTemplate restTemplate = new RestTemplate();

		

		HttpClient httpclient = new DefaultHttpClient();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		String answer = restTemplate.postForObject(url, entity, String.class);
		System.out.println(answer);
		*/

		//auction.setReservePrice(auction.getReservePrice());
		//auction.setItemId(auction.getItemId());
		//auction.setDescription(auction.getDescription());

		
	//	auctionBean.setCreatedBy(request.getRemoteUser());
		//auctionBean.setUpdatedAt(new Date());
		//auctionbean.setUpdatedBy(request.getRemoteUser());

       // return auctionRepository.save(auction);
		
	/*
	 * 
	Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", auctionId);
			
		ResponseEntity<AuctionBean> responseAuctionEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/api/v1/auctions/{id}", AuctionBean.class, uriVariables);
		
		AuctionBean auctionResponse = responseAuctionEntity.getBody();
		
		// Validate that both results were successful
		
		return new OrchestrationBean(auctionId, auctionResponse.getAuctionTitle(), auctionResponse.getAuctionDescription(),
			auctionResponse.getSellerName(), auctionResponse.getReservePrice(), auctionResponse.getStartDate(),
			auctionResponse.getEndDate(), auctionResponse.getStartingBid(), auctionResponse.getCurrentBid(),
			auctionResponse.getHighestBidderId(), auctionResponse.getHighestBidderUsername(),
			auctionResponse.getMaxAutoBidAmount(), auctionResponse.getCreatedAt(), auctionResponse.getCreatedBy(),
			auctionResponse.getUpdatedAt(), auctionResponse.getUpdatedBy()), itemResponse.getItemId(),
			itemResponse.getItemName(), itemResponse.getItemDescription();
	 */
	
	//}
	
	// GET /auctionItems *********************************
	@GetMapping("/auctionItems")
	public String retrieveAllAuctions() {
		
		//************* RETURN AN ENTITY LIST THAT YOU CAN ATTACH TO AN ENTITY LIST OF THE SAME GENERIC TYPE

		//ResponseEntity<AuctionBean> responseAuctionEntity = new RestTemplate().getForEntity(
		//		"http://localhost:8200/api/v1/auctions", AuctionBean.class);
		
		ResponseEntity<String> responseAuctionEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/api/v1/auctions", String.class);
		
		String auctionResponse = responseAuctionEntity.getBody();
		 //returns a list of Auction beans, convert to JSON with responseEntity in original LL Method and return that JSON response
		return auctionResponse;	
	}
	

	
	// GET /auctionItems/{auction_item_id} **************************
	@GetMapping("/auctionItems/{auction_item_id}")
	public AuctionBean retrieveAuctionById(@PathVariable(value = "auction_item_id") Long auctionItemId) {
		
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("auction_item_id", auctionItemId);
		
		ResponseEntity<AuctionBean> responseAuctionEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/api/v1/auctions/{auction_item_id}", AuctionBean.class, uriVariables);
		
		AuctionBean auctionResponse = responseAuctionEntity.getBody();
		
		return new AuctionBean(auctionItemId, auctionResponse.getCurrentBid(), auctionResponse.getBidderName(), auctionResponse.getReservePrice(), auctionResponse.getItemId(), auctionResponse.getDescription() );
		
	}
	
	// POST /bids
	@PostMapping("/bids")
	public String createBid(@Valid @RequestBody BidBean bidBean) {
	
		bidBean.setAuctionItemId(bidBean.getAuctionItemId());
		bidBean.setMaxAutoBidAmount(bidBean.getMaxAutoBidAmount());
		bidBean.setBidderName(bidBean.getBidderName());
		
		//MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		//parts.add("bidBean", bidBean);
		
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("auction_item_id", bidBean.getAuctionItemId());

		ResponseEntity<BidBean> responseAuctionEntity = new RestTemplate().postForEntity(
				"http://localhost:8200/api/v1/auction", bidBean, BidBean.class);
		
		String auctionResponse = responseAuctionEntity.getBody().toString();
		
		return auctionResponse;
		
		//“auctionItemId”: “1234”,
		//“maxAutoBidAmount”: 9500.00,
		//“bidderName”: “ABC Dealership”

	
	}
		
		
	
	/*
	@GetMapping("/fullauctiondetails/{id}")
	public OrchestrationBean retrieveFullAuction(@PathVariable(value = "id") Long auctionId) {
		
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", auctionId);
			
		ResponseEntity<AuctionBean> responseAuctionEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/api/v1/auctions/{id}", AuctionBean.class, uriVariables);
		
		AuctionBean auctionResponse = responseAuctionEntity.getBody();
		
		// Validate that both results were successful
		
		return new OrchestrationBean(auctionId, auctionResponse.getAuctionTitle(), auctionResponse.getAuctionDescription(),
			auctionResponse.getSellerName(), auctionResponse.getReservePrice(), auctionResponse.getStartDate(),
			auctionResponse.getEndDate(), auctionResponse.getStartingBid(), auctionResponse.getCurrentBid(),
			auctionResponse.getHighestBidderId(), auctionResponse.getHighestBidderUsername(),
			auctionResponse.getMaxAutoBidAmount(), auctionResponse.getCreatedAt(), auctionResponse.getCreatedBy(),
			auctionResponse.getUpdatedAt(), auctionResponse.getUpdatedBy()), itemResponse.getItemId(),
			itemResponse.getItemName(), itemResponse.getItemDescription();
		
	}
	
	@GetMapping("/auctiondetails/{id}")
	public AuctionBean retrieveAuctionDetails(@PathVariable(value = "id") Long auctionId) {
		
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", auctionId);
			
		ResponseEntity<AuctionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/api/v1/auctions/{id}", AuctionBean.class, uriVariables);
		
		AuctionBean auctionResponse = responseEntity.getBody();
		
		return new AuctionBean(auctionId, auctionResponse.getAuctionTitle(), auctionResponse.getAuctionDescription(),
			auctionResponse.getSellerName(), auctionResponse.getReservePrice(), auctionResponse.getStartDate(),
			auctionResponse.getEndDate(), auctionResponse.getStartingBid(), auctionResponse.getCurrentBid(),
			auctionResponse.getHighestBidderId(), auctionResponse.getHighestBidderUsername(),
			auctionResponse.getMaxAutoBidAmount(), auctionResponse.getCreatedAt(), auctionResponse.getCreatedBy(),
			auctionResponse.getUpdatedAt(), auctionResponse.getUpdatedBy());
		
	}
	
	@GetMapping("/auctionitems/{id}")
	public ItemBean retrieveAuctionItem(@PathVariable(value = "id") Long itemId) {
		
		Map<String, Long> uriVariables = new HashMap<>();
		uriVariables.put("id", itemId);
			
		ResponseEntity<ItemBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8300/api/v1/items/{id}", ItemBean.class, uriVariables);
		
		ItemBean itemResponse = responseEntity.getBody();
		
		return new ItemBean(itemId, itemResponse.getItemName(), itemResponse.getItemDescription(),
			itemResponse.getCreatedAt(), itemResponse.getCreatedBy(),
			itemResponse.getUpdatedAt(), itemResponse.getUpdatedBy());
		
	}
	
	@Autowired
	private AuctionServiceProxy proxy;
	
	//@Autowired
	//private ItemServiceProxy proxy;
	
	
	*/
	/*
	@GetMapping("/auctionitemsfeign/{id}")
	public AuctionBean retrieveAuctionItemsFeign(@PathVariable(value = "id") Long auctionId) {

	    AuctionBean auctionResponse = proxy.getAuctionById(auctionId);

	    logger.info("{}", auctionResponse);

	    return new AuctionBean(auctionId, auctionResponse.getAuctionTitle(), auctionResponse.getAuctionDescription(),
				auctionResponse.getSellerName(), auctionResponse.getReservePrice(), auctionResponse.getStartDate(),
				auctionResponse.getEndDate(), auctionResponse.getStartingBid(), auctionResponse.getCurrentBid(),
				auctionResponse.getHighestBidderId(), auctionResponse.getHighestBidderUsername(),
				auctionResponse.getMaxAutoBidAmount(), auctionResponse.getCreatedAt(), auctionResponse.getCreatedBy(),
				auctionResponse.getUpdatedAt(), auctionResponse.getUpdatedBy());
	}
	*/
	
	
	
}
