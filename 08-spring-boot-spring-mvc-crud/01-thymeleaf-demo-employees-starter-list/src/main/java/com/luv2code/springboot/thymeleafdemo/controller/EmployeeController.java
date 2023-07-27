package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	EmployeeService employeeService;

	@Autowired //tek bir constructor varsa Autowired e gerek yok fakat standart acısından koyulabilir buradaki gibi
	public EmployeeController(EmployeeService theeEmployeeService){
		employeeService=theeEmployeeService;
	}

	// add mapping for "/list
	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		//get Employee from the Db
		List<Employee> theEmployees=employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees",theEmployees);

		return "employees/list-employees"; //burada list-employees htmlinin path ini veriyoruz ve bu sekilde view yapıyoruz
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		//create model attribute to bind the form data

		Employee theEmployee=new Employee();
		theModel.addAttribute("employee",theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

		//get the employee from the service
		Employee theEmployee=employeeService.findById(theId);


		//set employee in the model prepopulate the form
		theModel.addAttribute("employee",theEmployee);

		//send over to our form

		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		//save the employee
		employeeService.save(theEmployee);

		//use a redirect prevent to dublicate submission
		 return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){

		//delete the employee
		employeeService.deleteById(theId);

		//redirect to the /employee/list
		return  "redirect:/employees/list";


	}

}









