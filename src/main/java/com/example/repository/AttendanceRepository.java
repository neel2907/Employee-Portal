package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Attendance;
import com.example.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    /**
     * Find attendance record for a specific user on a given date.
     */
    Optional<Attendance> findByUserAndDate(User user, LocalDate date);

    /**
     * Get all attendance records for a user, sorted by most recent first.
     */
    List<Attendance> findAllByUserOrderByDateDesc(User user);

    /**
     * Get all attendance records for a specific date (all users).
     */
    List<Attendance> findAllByDate(LocalDate date);

    /**
     * Get attendance records for a specific user between start and end dates.
     */
    List<Attendance> findAllByUserAndDateBetweenOrderByDateDesc(User user, LocalDate start, LocalDate end);

    /**
     * Admin: Get all attendance records between start and end dates (for all users).
     */
    List<Attendance> findAllByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end);
}
