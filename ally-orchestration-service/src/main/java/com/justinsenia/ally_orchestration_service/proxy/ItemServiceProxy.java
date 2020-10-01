package com.justinsenia.ally_orchestration_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.justinsenia.ally_orchestration_service.bean.ItemBean;

@FeignClient(name="ally-item-service", url="localhost:8300")
public interface ItemServiceProxy {

	@GetMapping("/items/{id}")
	public ItemBean getItemById(@PathVariable(value = "id") Long itemId);

}
