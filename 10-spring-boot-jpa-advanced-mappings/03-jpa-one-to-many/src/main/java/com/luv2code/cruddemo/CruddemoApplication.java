package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return  runner ->{
		 	//createInstructor(appDAO);

			 //findInstructor(appDAO);

			//deleteInstructor(appDAO);

			//findInstructorDetail(appDAO);

			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourses(appDAO);
			
			//findInstructorWithCourses(appDAO);
			
			findCoursesForInstructors(appDAO);
		};
	}

	private void findCoursesForInstructors(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor=appDAO.findInstructorById(theId);


		System.out.println("tempInstructor : "+ tempInstructor);

		//find courses for instructors
		System.out.println("Finding courses for instructor id: " +theId);
		List<Course> courses=appDAO.findCourseByInstructorId(theId);

		//associate the objects// lazy initialization oldugu içn burada set işlemi yapıyoruz
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println("DONE");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor=appDAO.findInstructorById(theId);

		System.out.println("tempInstructor : "+ tempInstructor);
		System.out.println("the associated courses : " + tempInstructor.getCourses());

		System.out.println( "DONEE");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create instructor

		Instructor tempInstructor=new Instructor("Ecem","PACACI","pacacciecem@gmail.com");

		//create Instructor detail

		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com","Shopping");

		//assosiate the object

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//create some Courses
		Course tempCourse1=new Course("Air Guitar -the Ultimate Guide");
		Course tempCourse2=new Course("The Pinball Masterclass");

		//add course to instructor
		//NOTE:this will also save the courses
		//because of cascade type.persist
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save the instructor
		System.out.println("saving the instructor : "+ tempInstructor);
		System.out.println("the courses : " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("DONE!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=4;
		System.out.println("deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);
		System.out.println("DONE");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get the instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(theId);

		//print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		//print associated instructor
		System.out.println("the associated instructor : " + tempInstructorDetail.getInstructor());

		System.out.println("DONE");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting instructor id : " + theId);

		appDAO.deleteInstructorById(theId);
		System.out.println("DONE");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=2;
		System.out.println("Finding instructor id : "+theId);

		Instructor tempInstructor=appDAO.findInstructorById(theId);

		System.out.println("tempInstructor :" + tempInstructor);
		System.out.println("the associate instructorDetail only :" + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
		/*
		//create the instructor


		Instructor tempInstructor=new Instructor("Chad","Darby","darby@gmail.com");

		//create Instructor detail

		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com","I love to Code");

		//assosiate the object
		*/


		//create the instructor (ikinci kez instructor ve instructordetail üretiyoruz ve onetoone olarak db de kontrol ediyoruz)
		//


		Instructor tempInstructor=new Instructor("Erdogan","PACACI","pacacierdogan@gmail.com");

		//create Instructor detail

		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.youtube.com","I love to Code2");

		//assosiate the object




		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		//
		//NOTE:this will also save detail objects
		//Because of CascadeType.ALL
		//

		System.out.println("saving the instructor:  "+tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("DONE");
	}
}
