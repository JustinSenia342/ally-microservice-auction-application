package com.justinsenia.ally_orchestration_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.justinsenia.ally_orchestration_service.bean.UserBean;

@FeignClient(name="ally-user-service", url="localhost:8100")
//@FeignClient(name="ally-user-service")
//@RibbonClient(name="ally-user-service")
public interface UserServiceProxy {

	@GetMapping("/users/{id}")
	public UserBean getUserById(@PathVariable(value = "id") Long userId);
	
}
