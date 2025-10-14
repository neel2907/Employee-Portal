package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PROJECT_ALLOCATION")
public class ProjectAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_alloc_seq")
    @SequenceGenerator(name = "project_alloc_seq", sequenceName = "PROJECT_ALLOC_SEQ", allocationSize = 1)
    private Long id;

    private String projectName;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	public ProjectAllocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectAllocation(Long id, String projectName, String role, LocalDate startDate, LocalDate endDate,
			User user) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.role = role;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
