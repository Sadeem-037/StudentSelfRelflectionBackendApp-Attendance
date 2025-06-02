package com.pranay.StudentSelfReflection;

import com.pranay.StudentSelfReflection.constants.AttendanceStatus;
import com.pranay.StudentSelfReflection.model.Attendance;
import com.pranay.StudentSelfReflection.model.Roles;
import com.pranay.StudentSelfReflection.model.StudentUser;
import com.pranay.StudentSelfReflection.repository.AttendanceRepository;
import com.pranay.StudentSelfReflection.repository.RoleRepository;
import com.pranay.StudentSelfReflection.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.management.relation.Role;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootTest
@EnableJpaRepositories
class StudentSelfReflectionApplicationTests {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	RoleRepository roleRepository;

	@Test
	void testSaveStudentApplicationUser()
	{
		Roles roles = roleRepository.findById(3L).get();

		StudentUser studentUser = new StudentUser();
		studentUser.setRole(roles);
		studentUser.setRollNumber("S12");
		studentUser.setEmail("pranay@gmail.com");
		studentUser.setConfirmEmail("pranay@gmail.com");
		studentUser.setPwd("1234556");
		studentUser.setConfirmPwd("1234556");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		studentUser.setDateOfBirth(LocalDate.parse("01.05.2002",dateTimeFormatter));

		studentRepository.save(studentUser);
	}


	@Autowired
	AttendanceRepository attendanceRepository;

	@Test
	public void saveAttendance(){

		Long studentId = 3L;
		String status = "PRESENT";

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
		var saved =  attendanceRepository.save(attendance);
		System.out.println("DATA : " + saved.toString());
	}

	@Test
	void contextLoads() {
	}

}
