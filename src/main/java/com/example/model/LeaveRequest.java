package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LEAVE_REQUESTS")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_req_seq")
    @SequenceGenerator(name = "leave_req_seq", sequenceName = "LEAVE_REQ_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String leaveType; // e.g. SICK, CASUAL, VACATION
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING;

    private String reason;
    private LocalDate appliedDate = LocalDate.now();
	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveRequest(Long id, User user, String leaveType, LocalDate startDate, LocalDate endDate,
			LeaveStatus status, String reason, LocalDate appliedDate) {
		super();
		this.id = id;
		this.user = user;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.reason = reason;
		this.appliedDate = appliedDate;
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
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LeaveStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
    
    
}



enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}
