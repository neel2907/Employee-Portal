package com.example.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "ATTENDANCE")


public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
    @SequenceGenerator(name = "attendance_seq", sequenceName = "ATTENDANCE_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate attendance_date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    @Column(name = "total_hours")
    private Double totalHours;

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(Long id, User user, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime,
			Double totalHours) {
		super();
		this.id = id;
		this.user = user;
		this.attendance_date = attendance_date;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getAttendance_date() {
		return attendance_date;
	}

	public void setAttendance_date(LocalDate attendance_date) {
		this.attendance_date = attendance_date;
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
