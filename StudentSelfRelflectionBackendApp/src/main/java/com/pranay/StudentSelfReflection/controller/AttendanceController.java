package com.pranay.StudentSelfReflection.controller;

import com.pranay.StudentSelfReflection.constants.AttendanceStatus;
import com.pranay.StudentSelfReflection.model.Attendance;
import com.pranay.StudentSelfReflection.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance/")
public class AttendanceController
{

    @Autowired
    AttendanceService attendanceService;

    // POST http://localhost:8080/api/v1/attendance/create?studentId={id}&status={status}
    // 1. Create Attendance
    @PostMapping("create")
    public ResponseEntity<Attendance> createAttendance(@RequestParam Long studentId, @RequestParam String status)
    {
        Attendance attendanceResponse = attendanceService.saveAttendance(studentId, status);
        return new ResponseEntity<>(attendanceResponse, HttpStatus.CREATED);
    }


    // GET
    // http://localhost:8080/api/v1/attendance/getAllAttendance
    @GetMapping("getAllAttendance")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    //  http://localhost:8080/api/v1/attendance/getAttendance/studentId={id}
    @GetMapping("getAttendance/{studentId}")
    public List<Attendance> getByStudentId(@PathVariable Long studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }

    //  http://localhost:8080/api/v1/attendance/getAttendanceByMonth/studentId={id}&month={month number}&year={year number}
    @GetMapping("getAttendanceByMonth")
    public ResponseEntity<List<Attendance>> getAttendanceForMonth(
            @RequestParam Long studentId,
            @RequestParam int month,
            @RequestParam int year) {

        List<Attendance> records = attendanceService.getAttendanceForMonth(studentId, month, year);
        return ResponseEntity.ok(records);
    }

    //  http://localhost:8080/api/v1/attendance/percentage?studentId={id}
    @GetMapping("percentage")
    public ResponseEntity<Double> getAttendancePercentage(@RequestParam Long studentId) {
        double percentage = attendanceService.getOverallAttendancePercentage(studentId);
        return ResponseEntity.ok(percentage);
    }

    // PUT http://localhost:8080/api/v1/attendance/update?studentId={id}
    @PutMapping("update")
    public ResponseEntity<Attendance> updateAttendance(
            @RequestParam Long studentId,
            @RequestParam String date,
            @RequestParam AttendanceStatus status) {

        Attendance updated = attendanceService.updateAttendanceByStudentIdAndDate(
                studentId,
                LocalDate.parse(date),
                status
        );

        return ResponseEntity.ok(updated);
    }

    // PATCH


    // DELETE
}
