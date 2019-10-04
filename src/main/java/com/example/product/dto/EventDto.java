package com.example.product.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

	@ApiModelProperty(notes = "Type of the Event", name = "event type", required = true, value = "INFO", allowableValues = "INFO, ERROR" )
	private String eventType;
	
	@ApiModelProperty(notes = "Description of the Event", name = "event description", required = true, value = "Test Description" )
	private String eventDescription;
	
	@ApiModelProperty(notes = "Date of the Event", name = "event date", required = true, value = "12-12-2019" )
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date eventDate;
	
	public EventDto() {
	}

	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public static class Builder {
		private String eventType;
		private String eventDescription;
		private Date eventDate;

		public Builder eventType(String eventType) {
			this.eventType = eventType;
			return this;
		}

		public Builder eventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
			return this;
		}

		public Builder eventDate(Date eventDate) {
			this.eventDate = eventDate;
			return this;
		}

		public EventDto build() {
			return new EventDto(this);
		}
	}

	private EventDto(Builder builder) {
		this.eventType = builder.eventType;
		this.eventDescription = builder.eventDescription;
		this.eventDate = builder.eventDate;
	}

	@Override
	public String toString() {
		return "EventDto [eventType=" + eventType + ", eventDescription=" + eventDescription + ", eventDate="
				+ eventDate + "]";
	}
	
}
