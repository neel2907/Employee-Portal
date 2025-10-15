package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.dto.AttendanceResponse;
import com.example.dto.CheckInRequest;
import com.example.dto.CheckOutRequest;

public interface AttendanceService {

    /**
     * Employee check-in operation.
     * @param userEmail logged-in user's email
     * @param request optional request details
     * @return attendance response after successful check-in
     */
    AttendanceResponse checkIn(String userEmail, CheckInRequest request);

    /**
     * Employee check-out operation.
     * @param userEmail logged-in user's email
     * @param request optional request details
     * @return attendance response after successful check-out
     */
    AttendanceResponse checkOut(String userEmail, CheckOutRequest request);

    /**
     * Get the logged-in employee's full attendance history.
     * @param userEmail logged-in user's email
     * @return list of attendance records for that employee
     */
    List<AttendanceResponse> getMyAttendanceHistory(String userEmail);

    /**
     * Get attendance for a specific user within a date range (Admin feature).
     * @param userEmail target user's email
     * @param start start date filter
     * @param end end date filter
     * @return list of attendance records for the specified user
     */
    List<AttendanceResponse> getAttendanceForUser(String userEmail, LocalDate start, LocalDate end);

    /**
     * Get attendance records for all users (Admin view).
     * @return list of all attendance records
     */
    List<AttendanceResponse> getAllAttendanceRecords();
}
