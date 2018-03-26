package com.tarun.springbootSOAPexample.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tarun.springbootSOAPexample.soap.bean.Course;
import com.tarun.springbootSOAPexample.soap.service.CourseDetailsService;
import com.tarun.springbootSOAPexample.soap.service.CourseDetailsService.Status;

import in28minutes.courses.CourseDetails;
import in28minutes.courses.DeleteCourseDetailsRequest;
import in28minutes.courses.DeleteCourseDetailsResponse;
import in28minutes.courses.GetAllCourseDetailsRequest;
import in28minutes.courses.GetAllCourseDetailsResponse;
import in28minutes.courses.GetCourseDetailsRequest;
import in28minutes.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndPoint {
	
	@Autowired
	CourseDetailsService theService;
	
	@PayloadRoot(namespace="http://in28minutes/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload 
			GetCourseDetailsRequest request) {
		
		Course course = theService.findById(request.getId());
		
		
		return mapCourseDetails(course);
	
	}
	
	@PayloadRoot(namespace="http://in28minutes/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload 
			GetAllCourseDetailsRequest request) {
		
		List<Course> courseList = theService.findAll();
		
		return mapAllCourseDetails(courseList);
	
	}
	
	@PayloadRoot(namespace="http://in28minutes/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload 
			DeleteCourseDetailsRequest request) {
		
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		
		Status status = theService.deleteById(request.getId());
		response.setStatus(mapStatus(status));
		
		return response;
	
	}


	private in28minutes.courses.Status mapStatus(Status status) {
		if(status == Status.FAILURE) {
			return in28minutes.courses.Status.FAILURE;
		}
		return in28minutes.courses.Status.SUCCESS;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courseList) {
		
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(Course course: courseList) {
			
			response.getCourseDetails().add(mapCourse(course));
		}
		return response;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}


	//returns a courseDetails
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

}
