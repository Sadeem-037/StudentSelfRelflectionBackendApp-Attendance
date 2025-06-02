package com.pranay.StudentSelfReflection.controller;

import com.pranay.StudentSelfReflection.model.Course;
import com.pranay.StudentSelfReflection.model.Topic;
import com.pranay.StudentSelfReflection.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * File Name: AdminController.java
 * Entity: AdminController
 * Package: com.pranay.StudentSelfReflection.controller
 * Author: pranayramteke
 * Date: 29/04/25
 * Description:
 */

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController
{
	
	@Autowired
	CourseService courseService;
	
	// POST http://localhost:8080/api/v1/admin/create-course
	@PostMapping("/create-course")
	public ResponseEntity<Course> createCourse(@RequestBody Course course)
	{
		Course courseResponse = courseService.saveCourse(course);
		return new ResponseEntity<Course>(courseResponse, HttpStatus.CREATED);
	}

	// PATCH http://localhost:8080/api/v1/admin/update-course/1
	@PatchMapping("/update-course/{id}")
	public ResponseEntity<Course> updateCourseDetails(@PathVariable("id") Long id,@RequestBody Course course)
	{
		Course courseResponse = courseService.updateCourse(id,course);
		return new ResponseEntity<Course>(courseResponse, HttpStatus.OK);
	}

	// PATCH http://localhost:8080/api/v1/admin/update-topic?courseId={id}&topicId={id}
	@PatchMapping("/update-topic")
	public ResponseEntity<Course> updateTopicDetails(@RequestParam("courseId") Long courseId, @RequestParam("topicId") Long topicId, @RequestBody Topic topic)
	{
		Course courseResponse = courseService.updateCourseTopicByTopicId(courseId, topicId, topic);
		return new ResponseEntity<Course>(courseResponse, HttpStatus.OK);
	}
	
	// DELETE http://localhost:8080/api/v1/admin/delete-course?courseId={id}
	@DeleteMapping("/delete-course")
	public ResponseEntity<String> deleteCourseById(@RequestParam Long courseId)
	{
		String response = courseService.deleteCourseByCourseId(courseId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	// POST http://localhost:8080/api/v1/admin/add-topic?courseId={id}
	@PostMapping("add-topic")
	public ResponseEntity<Course> addTopicToCourseWithCourseId(@RequestParam Long courseId, @RequestBody Topic topic)
	{
		Course updatedCourseWithTopics = courseService.addTopicToExistingCourseWithCourseId(courseId,topic);
		return new ResponseEntity<>(updatedCourseWithTopics,HttpStatus.OK);
	}
}
