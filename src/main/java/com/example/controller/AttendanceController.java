package com.example.controller;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.dto.AttendanceResponse;
import com.example.dto.CheckInRequest;
import com.example.dto.CheckOutRequest;
import com.example.service.AttendanceService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")

public class AttendanceController {

    private final AttendanceService attendanceService;
    
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * Check-in for the current authenticated user.
     * Authentication principal is used to get user email/username.
     */
    @PostMapping("/checkin")
    public ResponseEntity<AttendanceResponse> checkIn(Authentication authentication,
                                                      @Valid @RequestBody(required = false) CheckInRequest request) {
        String userEmail = authentication.getName();
        CheckInRequest req = request == null ? new CheckInRequest() : request;
        AttendanceResponse resp = attendanceService.checkIn(userEmail, req);
        return ResponseEntity.ok(resp);
    }

    /**
     * Check-out for current authenticated user.
     */
    @PostMapping("/checkout")
    public ResponseEntity<AttendanceResponse> checkOut(Authentication authentication,
                                                       @Valid @RequestBody(required = false) CheckOutRequest request) {
        String userEmail = authentication.getName();
        CheckOutRequest req = request == null ? new CheckOutRequest() : request;
        AttendanceResponse resp = attendanceService.checkOut(userEmail, req);
        return ResponseEntity.ok(resp);
    }

    /**
     * Get personal attendance history
     */
    @GetMapping("/me")
    public ResponseEntity<List<AttendanceResponse>> myHistory(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(attendanceService.getMyAttendanceHistory(userEmail));
    }

    /**
     * Admin: get attendance for a specific user between dates.
     * Example: /api/attendance/user?email=someone@x.com&start=2025-01-01&end=2025-01-31
     */
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<List<AttendanceResponse>> userAttendance(
            @RequestParam String email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        // Authorization: ensure caller has ADMIN role (or implement method level security)
        // You can add @PreAuthorize("hasAuthority('ADMIN')") if you enable method security.

        return ResponseEntity.ok(attendanceService.getAttendanceForUser(email, start, end));
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<AttendanceResponse> list = attendanceService.getAllAttendanceRecords();
        return ResponseEntity.ok(list);
    }
}
