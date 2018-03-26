package com.tarun.springbootSOAPexample.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tarun.springbootSOAPexample.soap.bean.Course;

@Component
public class CourseDetailsService {
	private static List<Course> courseList = new ArrayList<>();
	
	static {
		Course course1 = new Course(1, "Spring", " In 10 Steps" );
		courseList.add(course1);
		
		Course course2 = new Course(2, "Spring MVC", " In Few Steps" );
		courseList.add(course2);
		
		Course course3 = new Course(3, "Harry Potter", "Mystical Story" );
		courseList.add(course3);
		
		Course course4 = new Course(4, "Spring Boot", "This is quite interesting" );
		courseList.add(course4);
	}
	
	public Course findById(int id) {
		for(Course course: courseList) {
			if(course.getId() == id) {
				return course;
			}
		}
		
		return null;
	}
	
	public List<Course> findAll(){
		return courseList;
	}
	
	public int deleteById(int id) {
		Iterator<Course> iterator = courseList.iterator();
		
		if(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return 1;
			}
		}
		
		return 0;
	}
}
