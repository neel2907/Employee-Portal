package com.example.model;

import jakarta.persistence.*;


@Entity
@Table(name = "LEAVE_BALANCE")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_bal_seq")
    @SequenceGenerator(name = "leave_bal_seq", sequenceName = "LEAVE_BAL_SEQ", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int totalLeaves = 30;
    private int usedLeaves = 0;
    private int remainingLeaves = 30;
	public LeaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveBalance(Long id, User user, int totalLeaves, int usedLeaves, int remainingLeaves) {
		super();
		this.id = id;
		this.user = user;
		this.totalLeaves = totalLeaves;
		this.usedLeaves = usedLeaves;
		this.remainingLeaves = remainingLeaves;
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
	public int getTotalLeaves() {
		return totalLeaves;
	}
	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}
	public int getUsedLeaves() {
		return usedLeaves;
	}
	public void setUsedLeaves(int usedLeaves) {
		this.usedLeaves = usedLeaves;
	}
	public int getRemainingLeaves() {
		return remainingLeaves;
	}
	public void setRemainingLeaves(int remainingLeaves) {
		this.remainingLeaves = remainingLeaves;
	}
    
    
}
