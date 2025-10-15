package com.example.dto;

public class CheckOutRequest {
    private String note;

	public CheckOutRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckOutRequest(String note) {
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
