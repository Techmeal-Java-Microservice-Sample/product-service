package com.example.product.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.product.dto.EventDto;
import com.example.product.feign.EventServiceClient;

@Component
public class CommonUtil {

	@Autowired
	private EventServiceClient eventServiceClient;
	
	public void publishEvent(String eventDescription, String eventType) {
		EventDto eventDto = new EventDto.Builder()
								.eventType(eventType)
								.eventDate(new Date())
								.eventDescription(eventDescription)
								.build();
		eventServiceClient.create(eventDto);
	}
}