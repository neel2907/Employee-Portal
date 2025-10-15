package com.example.controller;

import com.example.dto.AttendanceResponse;
import com.example.dto.CheckInRequest;
import com.example.dto.CheckOutRequest;
import com.example.service.AttendanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AttendanceControllerTest {

    @Mock
    private AttendanceService attendanceService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AttendanceController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(authentication.getName()).thenReturn("test@example.com");
    }

    @SuppressWarnings("deprecation")
	@Test
    void testCheckIn() {
        AttendanceResponse mockResp = new AttendanceResponse();
        when(attendanceService.checkIn(anyString(), any())).thenReturn(mockResp);

        ResponseEntity<AttendanceResponse> resp = controller.checkIn(authentication, new CheckInRequest());
        assertEquals(200, resp.getStatusCodeValue());
        verify(attendanceService).checkIn(anyString(), any());
    }

    @SuppressWarnings("deprecation")
	@Test
    void testCheckOut() {
        AttendanceResponse mockResp = new AttendanceResponse();
        when(attendanceService.checkOut(anyString(), any())).thenReturn(mockResp);

        ResponseEntity<AttendanceResponse> resp = controller.checkOut(authentication, new CheckOutRequest());
        assertEquals(200, resp.getStatusCodeValue());
        verify(attendanceService).checkOut(anyString(), any());
    }

    @Test
    void testMyHistory() {
        when(attendanceService.getMyAttendanceHistory(anyString())).thenReturn(List.of(new AttendanceResponse()));
        ResponseEntity<List<AttendanceResponse>> resp = controller.myHistory(authentication);
        assertEquals(1, resp.getBody().size());
    }
}
