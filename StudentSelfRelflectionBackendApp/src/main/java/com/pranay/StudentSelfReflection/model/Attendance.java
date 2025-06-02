package com.pranay.StudentSelfReflection.model;


import jakarta.persistence.*;
import com.pranay.StudentSelfReflection.constants.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * File Name: Attendance.java
 * Entity: Attendance
 * Package: com.pranay.StudentSelfReflection.model
 * Author: pranayramteke
 * Date: 29/04/25
 * Description:
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class 	Attendance extends BaseEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private AttendanceStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER,optional = true,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "student_id", referencedColumnName = "studentId",nullable = true)
	private StudentUser studentUser;


}
