package com.pranay.StudentSelfReflection.services;

import com.pranay.StudentSelfReflection.constants.AttendanceStatus;
import com.pranay.StudentSelfReflection.model.Attendance;
import com.pranay.StudentSelfReflection.model.StudentUser;
import com.pranay.StudentSelfReflection.repository.AttendanceRepository;
import com.pranay.StudentSelfReflection.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AttendanceService
{

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    StudentRepository studentRepository;

    public Attendance saveAttendance(Long studentId, String status ){

        // TimeStamp Before The Actual Operation  (@Before)
        Instant startTime = Instant.now();
        System.out.println("saveAttendance() method started at : " + startTime);


        // Student Id --> Student Object
        Optional<StudentUser> optionalStudent = studentRepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        StudentUser student = optionalStudent.get();

        // String TO ENUM
        AttendanceStatus attendanceStatus;
        try {
            attendanceStatus = AttendanceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid attendance status: " + status);
        }

        // Create Attendance Object
        Attendance attendance = new Attendance();
        attendance.setDate(LocalDate.now());
        attendance.setStatus(attendanceStatus);

        // Attendance-> Student
        attendance.setStudentUser(student);


        // TimeStamp After The Actual Operation (@After)
        Instant endTime = Instant.now(); // 35 ms
        System.out.println("saveAttendance() method finished at : " + endTime);
        System.out.println("saveAttendance() method time elapsed : " + Duration.between(startTime, endTime));

        // Step 5: Save Attendance
        return attendanceRepository.save(attendance);
    }


    // Get all attendance records
    public List<Attendance> getAllAttendance() {

        // TimeStamp Before The Actual Operation  (@Before)
        Instant startTime = Instant.now();
        System.out.println("saveAttendance() method started at : " + startTime);

        // TimeStamp After The Actual Operation (@After)
        Instant endTime = Instant.now(); // 35 ms
        System.out.println("saveAttendance() method finished at : " + endTime);
        System.out.println("saveAttendance() method time elapsed : " + Duration.between(startTime, endTime));

        return attendanceRepository.findAll();
    }


     // Update attendance record by studentId and date
    public Attendance updateAttendanceByStudentIdAndDate(Long studentId, LocalDate date, AttendanceStatus newStatus) {

        // TimeStamp Before The Actual Operation  (@Before)
        Instant startTime = Instant.now();
        System.out.println("saveAttendance() method started at : " + startTime);

        // Step 1: Find the StudentUser
        StudentUser student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Step 2: Find Attendance by student and date
        Attendance attendance = (Attendance) attendanceRepository.findByStudentUserAndDate(student, date)
                .orElseThrow(() -> new RuntimeException("Attendance not found for student on date: " + date));

        // Step 3: Update and save
        attendance.setStatus(newStatus);

        // TimeStamp After The Actual Operation (@After)
        Instant endTime = Instant.now(); // 35 ms
        System.out.println("saveAttendance() method finished at : " + endTime);
        System.out.println("saveAttendance() method time elapsed : " + Duration.between(startTime, endTime));

        return attendanceRepository.save(attendance);
    }

    // Get attendance for a specific student by ID
    public List<Attendance> getAttendanceByStudentId(Long studentId) {

        // TimeStamp Before The Actual Operation  (@Before)
        Instant startTime = Instant.now();
        System.out.println("saveAttendance() method started at : " + startTime);


        StudentUser student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));


        // TimeStamp After The Actual Operation (@After)
        Instant endTime = Instant.now(); // 35 ms
        System.out.println("saveAttendance() method finished at : " + endTime);
        System.out.println("saveAttendance() method time elapsed : " + Duration.between(startTime, endTime));

        return attendanceRepository.findByStudentUser_StudentId(student.getStudentId());
    }

    //  Get attendance for a specific student by ID for month
    public List<Attendance> getAttendanceForMonth(Long studentId, int month, int year) {

        // TimeStamp Before The Actual Operation  (@Before)
        Instant startTime = Instant.now();
        System.out.println("saveAttendance() method started at : " + startTime);

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // TimeStamp After The Actual Operation (@After)
        Instant endTime = Instant.now(); // 35 ms
        System.out.println("saveAttendance() method finished at : " + endTime);
        System.out.println("saveAttendance() method time elapsed : " + Duration.between(startTime, endTime));

        return attendanceRepository.findByStudentUserStudentIdAndDateBetween(studentId, start, end);
    }

    //  Get Overall Attendance in Percentage

    public double getOverallAttendancePercentage(Long studentId) {

        // TimeStamp Before The Actual Operation  (@Before)
        Instant startTime = Instant.now();
        System.out.println("saveAttendance() method started at : " + startTime);

        List<Attendance> records = attendanceRepository.findByStudentUser_StudentId(studentId);

        if (records.isEmpty()) {
            return 0.0;
        }

        long presentCount = records.stream()
                .filter(att -> att.getStatus() == AttendanceStatus.PRESENT)
                .count();

        // TimeStamp After The Actual Operation (@After)
        Instant endTime = Instant.now(); // 35 ms
        System.out.println("saveAttendance() method finished at : " + endTime);
        System.out.println("saveAttendance() method time elapsed : " + Duration.between(startTime, endTime));

        return (presentCount * 100.0) / records.size();
    }
}


