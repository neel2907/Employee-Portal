package com.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PERFORMANCE_REVIEW")
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @SequenceGenerator(name = "review_seq", sequenceName = "REVIEW_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    private LocalDate reviewDate;
    private int rating; // 1 to 5
    private String feedback;
    private String goalsForNextCycle;
	public PerformanceReview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PerformanceReview(Long id, User employee, User manager, LocalDate reviewDate, int rating, String feedback,
			String goalsForNextCycle) {
		super();
		this.id = id;
		this.employee = employee;
		this.manager = manager;
		this.reviewDate = reviewDate;
		this.rating = rating;
		this.feedback = feedback;
		this.goalsForNextCycle = goalsForNextCycle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	public LocalDate getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getGoalsForNextCycle() {
		return goalsForNextCycle;
	}
	public void setGoalsForNextCycle(String goalsForNextCycle) {
		this.goalsForNextCycle = goalsForNextCycle;
	}
    
    
}
