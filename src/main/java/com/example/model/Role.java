package com.example.model;

import jakarta.persistence.*;


@Entity
@Table(name = "ROLES")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "ROLE_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // e.g., ADMIN, EMPLOYEE

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
