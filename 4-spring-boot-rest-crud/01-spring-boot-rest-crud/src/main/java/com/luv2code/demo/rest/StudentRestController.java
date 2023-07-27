package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define a PostConstruct to load the student data..only once!

    @PostConstruct
    public void loadData(){
        theStudents=new ArrayList<>();
        theStudents.add(new Student("erdogan","pacaci"));
        theStudents.add(new Student("ecem","pacaci"));
        theStudents.add(new Student("duru","pacaci"));

    }



    //define endpoint "/students"
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    //define a endpoint or   "/students/{studentid}" ---return a student by studentid

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //just index into the list--keep simple for now

        //check the studentId again list size


        if((studentId >=theStudents.size()) || (studentId <0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);

        }

        return theStudents.get(studentId);//arraylistin get methodunu kullanıyoruz ve bize student elementini döndürüyor
    }





}
