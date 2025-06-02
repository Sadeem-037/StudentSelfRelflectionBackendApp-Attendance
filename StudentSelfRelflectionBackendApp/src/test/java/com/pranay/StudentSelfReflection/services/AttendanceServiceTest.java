package com.pranay.StudentSelfReflection.services;

import com.pranay.StudentSelfReflection.constants.AttendanceStatus;
import com.pranay.StudentSelfReflection.model.Attendance;
import com.pranay.StudentSelfReflection.model.StudentUser;
import com.pranay.StudentSelfReflection.repository.AttendanceRepository;
import com.pranay.StudentSelfReflection.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@EnableJpaRepositories
class AttendanceServiceTest {

    @Autowired
    AttendanceService attendanceService;

    @Test
    void testSaveAttendanceOfStudentForStudentIdAndStatus()
    {
        attendanceService.saveAttendance(3L,"PRESENT");
    }


}