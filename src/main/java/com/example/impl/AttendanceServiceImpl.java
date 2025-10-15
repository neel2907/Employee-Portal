package com.example.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.AttendanceResponse;
import com.example.dto.CheckInRequest;
import com.example.dto.CheckOutRequest;
import com.example.exceptions.ResourceConflictException;
import com.example.exceptions.ResourceNotFoundException;
import com.example.model.Attendance;
import com.example.model.User;
import com.example.repository.AttendanceRepository;
import com.example.repository.UserRepository;
import com.example.service.AttendanceService;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AttendanceResponse checkIn(String userEmail, CheckInRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        LocalDate today = LocalDate.now();
        attendanceRepository.findByUserAndDate(user, today).ifPresent(a -> {
            if (a.getCheckInTime() != null && a.getCheckOutTime() == null) {
                throw new ResourceConflictException("Already checked in for today.");
            }
        });

        Attendance attendance = attendanceRepository.findByUserAndDate(user, today)
                .orElseGet(() -> {
                    Attendance a = new Attendance();
                    a.setUser(user);
                    a.setDate(today);
                    return a;
                });

        LocalDateTime now = LocalDateTime.now();
        attendance.setCheckInTime(now.toLocalTime());
        attendance.setCheckInTimestamp(now);
        attendance.setCheckOutTime(null);
        attendance.setCheckOutTimestamp(null);
        attendance.setTotalHours(null);

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDto(saved);
    }

    @Override
    public AttendanceResponse checkOut(String userEmail, CheckOutRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByUserAndDate(user, today)
                .orElseThrow(() -> new ResourceNotFoundException("No check-in found for today."));

        if (attendance.getCheckOutTime() != null) {
            throw new ResourceConflictException("Already checked out for today.");
        }

        LocalDateTime now = LocalDateTime.now();
        attendance.setCheckOutTime(now.toLocalTime());
        attendance.setCheckOutTimestamp(now);

        if (attendance.getCheckInTimestamp() == null) {
            attendance.setTotalHours(0.0);
        } else {
            Duration duration = Duration.between(attendance.getCheckInTimestamp(), now);
            double hours = duration.toMinutes() / 60.0;
            attendance.setTotalHours(Math.round(hours * 100.0) / 100.0);
        }

        Attendance saved = attendanceRepository.save(attendance);
        return mapToDto(saved);
    }

    @Override
    public List<AttendanceResponse> getMyAttendanceHistory(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return attendanceRepository.findAllByUserOrderByDateDesc(user)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponse> getAttendanceForUser(String userEmail, LocalDate start, LocalDate end) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (start == null) start = LocalDate.of(1970, 1, 1);
        if (end == null) end = LocalDate.now();

        return attendanceRepository.findAllByUserAndDateBetweenOrderByDateDesc(user, start, end)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ New method to get all attendance records (admin or dashboard view)
    public List<AttendanceResponse> getAllAttendanceRecords() {
        return attendanceRepository.findAll(Sort.by(Sort.Direction.DESC, "date"))
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private AttendanceResponse mapToDto(Attendance a) {
        AttendanceResponse dto = new AttendanceResponse();
        dto.setId(a.getId());
        dto.setUserId(a.getUser().getId());
        dto.setUserEmail(a.getUser().getEmail());
        dto.setDate(a.getDate());
        dto.setCheckInTime(a.getCheckInTime());
        dto.setCheckOutTime(a.getCheckOutTime());
        dto.setTotalHours(a.getTotalHours());
        return dto;
    }
}
