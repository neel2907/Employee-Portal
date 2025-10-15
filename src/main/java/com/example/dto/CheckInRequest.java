package com.example.dto;

import jakarta.validation.constraints.NotBlank;


public class CheckInRequest {
    // Optionally allow client to send location, note, etc.
    private String note;

	public CheckInRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckInRequest(String note) {
		super();
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
    
}
