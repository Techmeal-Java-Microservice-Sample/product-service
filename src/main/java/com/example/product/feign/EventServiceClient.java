package com.example.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.product.dto.EventDto;

@FeignClient("event-service")
public interface EventServiceClient {

	@PostMapping("/event-service/event")
	public ResponseEntity<EventDto> create(@RequestBody EventDto eventDto);
}
