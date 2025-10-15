package com.example.service;

import com.example.dto.CheckInRequest;
import com.example.dto.CheckOutRequest;
import com.example.impl.AttendanceServiceImpl;
import com.example.dto.AttendanceResponse;
import com.example.model.Attendance;
import com.example.model.User;
import com.example.repository.AttendanceRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AttendanceServiceImplTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
    }

    @Test
    void testCheckIn_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(attendanceRepository.findByUserAndDate(any(), any())).thenReturn(Optional.empty());

        CheckInRequest req = new CheckInRequest();
        AttendanceResponse resp = attendanceService.checkIn("test@example.com", req);

        assertNotNull(resp);
        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

    @Test
    void testDoubleCheckIn_Prevented() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Attendance existing = new Attendance();
        existing.setUser(user);
        existing.setDate(LocalDate.now());
        existing.setCheckInTime(LocalDateTime.now().toLocalTime());
        existing.setCheckInTimestamp(LocalDateTime.now());
        existing.setCheckOutTime(null);
        existing.setCheckOutTimestamp(null);

        when(attendanceRepository.findByUserAndDate(user, LocalDate.now())).thenReturn(Optional.of(existing));

        assertThrows(IllegalStateException.class, () -> attendanceService.checkIn("test@example.com", new CheckInRequest()));
    }

    @Test
    void testCheckOut_Success() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(LocalDate.now());
        attendance.setCheckInTime(LocalDateTime.now().minusHours(4).toLocalTime());
        attendance.setCheckInTimestamp(LocalDateTime.now().minusHours(4));
        attendance.setCheckOutTime(null);
        attendance.setCheckOutTimestamp(null);

        when(attendanceRepository.findByUserAndDate(user, LocalDate.now())).thenReturn(Optional.of(attendance));

        CheckOutRequest req = new CheckOutRequest();
        AttendanceResponse resp = attendanceService.checkOut("test@example.com", req);

        assertNotNull(resp);
        assertNotNull(attendance.getCheckOutTime());
        assertNotNull(attendance.getCheckOutTimestamp());
    }

    @Test
    void testDoubleCheckOut_Prevented() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(LocalDate.now());
        attendance.setCheckInTime(LocalDateTime.now().minusHours(4).toLocalTime());
        attendance.setCheckInTimestamp(LocalDateTime.now().minusHours(4));
        attendance.setCheckOutTime(LocalDateTime.now().toLocalTime());
        attendance.setCheckOutTimestamp(LocalDateTime.now());

        when(attendanceRepository.findByUserAndDate(user, LocalDate.now())).thenReturn(Optional.of(attendance));

        assertThrows(IllegalStateException.class, () -> attendanceService.checkOut("test@example.com", new CheckOutRequest()));
    }

    @Test
    void testGetMyAttendanceHistory() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(attendanceRepository.findAllByUserOrderByDateDesc(user)).thenReturn(List.of(new Attendance()));

        List<AttendanceResponse> list = attendanceService.getMyAttendanceHistory("test@example.com");
        assertEquals(1, list.size());
    }
}
