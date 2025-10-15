package com.example.repository;

import com.example.model.Attendance;
import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    void testSaveAndFindByUserAndDate() {
        User user = new User();
        user.setId(1L);
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(LocalDate.now());
        attendanceRepository.save(attendance);

        assertThat(attendanceRepository.findByUserAndDate(user, LocalDate.now())).isPresent();
    }
}
