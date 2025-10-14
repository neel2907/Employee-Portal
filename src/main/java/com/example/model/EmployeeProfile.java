package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE_PROFILE")

public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_profile_seq")
    @SequenceGenerator(name = "emp_profile_seq", sequenceName = "EMP_PROFILE_SEQ", allocationSize = 1)
    private Long id;

    private String department;
    private String designation;
    private LocalDate dateOfJoining;
    private String phoneNumber;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	public EmployeeProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeProfile(Long id, String department, String designation, LocalDate dateOfJoining, String phoneNumber,
			String address, User user) {
		super();
		this.id = id;
		this.department = department;
		this.designation = designation;
		this.dateOfJoining = dateOfJoining;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
