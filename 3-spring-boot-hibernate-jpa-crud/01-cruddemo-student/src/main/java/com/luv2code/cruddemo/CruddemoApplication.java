package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO  studentDAO){
		return  runner ->{
			// createStudent(studentDAO);
			
			createMultibleStudent(studentDAO);
			
			//readStudent(studentDAO);

			//queryForStudent(studentDAO);
			
			//queryForStudentByLastName(studentDAO);
			
			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numberRowsDeleted=studentDAO.deleteAll();
		System.out.println("Deleted rows count : " + numberRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId=3;
		System.out.println("Deleting student id : "+studentId);
		studentDAO.delete(studentId);

	}

	private void updateStudent(StudentDAO studentDAO) {

		//retrive Student based on the id:primary key

		int studentId=1;
		System.out.println("Getting student with id : " +studentId);
		Student myStudent=studentDAO.findById(studentId);


		//Change first name to "Scooby"
		System.out.println("Updating Student ....");
		myStudent.setFirstName("Scooby");


		//Updte the Student
		studentDAO.update(myStudent);

		//display the update student
		System.out.println("Updated the Student  " + myStudent);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {

		//get list of student
		List<Student> theStudents=studentDAO.findByLastName("Pacaci");

		//display of students
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {

		//get a list of student
		List<Student> theStudents=studentDAO.findAll();

		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);

		}
	}

	private void readStudent(StudentDAO studentDAO) {

		//create a student
		System.out.println("creating a new student object...");
		Student tempStudent=new Student("Paul","Walker","paul@luv2code.com");

		//save the student
		System.out.println("saving the temp student to Database..");
		studentDAO.save(tempStudent);


		//display id of the saved student
		int theId=tempStudent.getId();
		System.out.println("saved student generated id: " + theId);


		//retrieve student based on the id:primary key
		System.out.println("retrieve student with  id: " +theId);
		Student myStudent=studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: "+ myStudent);
	}

	private void createMultibleStudent(StudentDAO studentDAO) {
		//create multible student
		System.out.println("Creating 3 student object ...");
		Student tempStudent1=new Student("John","Doe","john@luv2code.com");
		Student tempStudent2=new Student("Erdogan","Pacaci","erdogan@luv2code.com");
		Student tempStudent3=new Student("Ecem","Pacaci","ecem@luv2code.com");

		//save the student object
		System.out.println("saving the student objectss");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);



	}

	private void createStudent(StudentDAO studentDAO) {

		//create student object
		System.out.println("Creating new student object ...");
		Student tempStudent=new Student("Paul","Doe","paul@luv2code.com");

		//save the student object
		System.out.println("saving the student ...");
		studentDAO.save(tempStudent);

		//display id of saved object
		System.out.println("saved student .generated id : " +tempStudent.getId());
	}
}
