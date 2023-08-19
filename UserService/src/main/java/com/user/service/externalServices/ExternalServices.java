package com.user.service.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.service.entity.Hotel;

@FeignClient(name= "HOTEL-SERVICE")
public interface ExternalServices {
	// important to give pathvariable parameter
	@GetMapping("/hotels/{Id}")
	Hotel getHotel(@PathVariable("Id") String Id);
}
