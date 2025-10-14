package com.example.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "JWT_TOKENS")

public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    @SequenceGenerator(name = "token_seq", sequenceName = "TOKEN_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    private boolean revoked;
    private boolean expired;
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	public JwtToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtToken(Long id, String token, boolean revoked, boolean expired, Instant createdAt, User user) {
		super();
		this.id = id;
		this.token = token;
		this.revoked = revoked;
		this.expired = expired;
		this.createdAt = createdAt;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
