package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "ATTENDANCE")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
    @SequenceGenerator(name = "attendance_seq", sequenceName = "ATTENDANCE_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate date;

    @Column(name = "check_in_time")
    private LocalTime checkInTime;

    @Column(name = "check_out_time")
    private LocalTime checkOutTime;

    @Column(name = "check_in_timestamp")
    private LocalDateTime checkInTimestamp;

    @Column(name = "check_out_timestamp")
    private LocalDateTime checkOutTimestamp;

    @Column(name = "total_hours")
    private Double totalHours;

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(Long id, User user, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime,
			LocalDateTime checkInTimestamp, LocalDateTime checkOutTimestamp, Double totalHours) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.checkInTimestamp = checkInTimestamp;
		this.checkOutTimestamp = checkOutTimestamp;
		this.totalHours = totalHours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public LocalDateTime getCheckInTimestamp() {
		return checkInTimestamp;
	}

	public void setCheckInTimestamp(LocalDateTime checkInTimestamp) {
		this.checkInTimestamp = checkInTimestamp;
	}

	public LocalDateTime getCheckOutTimestamp() {
		return checkOutTimestamp;
	}

	public void setCheckOutTimestamp(LocalDateTime checkOutTimestamp) {
		this.checkOutTimestamp = checkOutTimestamp;
	}

	public Double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}
    
    
    
}
