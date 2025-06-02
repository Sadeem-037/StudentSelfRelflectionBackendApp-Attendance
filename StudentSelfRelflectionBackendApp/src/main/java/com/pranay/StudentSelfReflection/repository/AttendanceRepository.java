package com.pranay.StudentSelfReflection.repository;

import com.pranay.StudentSelfReflection.model.Attendance;
import com.pranay.StudentSelfReflection.model.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long>
{

    List<Attendance> findByStudentUser_StudentId(Long studentId);

    Optional<Object> findByStudentUserAndDate(StudentUser student, LocalDate date);

    List<Attendance> findByStudentUserStudentIdAndDateBetween(Long studentId, LocalDate startDate, LocalDate endDate);
}
