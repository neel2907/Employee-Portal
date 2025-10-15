package com.example.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponse {
    private Long id;
    private Long userId;
    private String userEmail;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Double totalHours;
	public AttendanceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttendanceResponse(Long id, Long userId, String userEmail, LocalDate date, LocalTime checkInTime,
			LocalTime checkOutTime, Double totalHours) {
		super();
		this.id = id;
		this.userId = userId;
		this.userEmail = userEmail;
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.totalHours = totalHours;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}
	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(LocalTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public Double getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}
    
    
}
