package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeRestController {
    //quick and dirty:inject employee dao
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }
    //expose "employees" and return list of employess
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee=employeeService.findById(employeeId);
        if(theEmployee == null){
            throw  new RuntimeException("Employee id not found - " +employeeId);
        }
         return  theEmployee;


    }


    //add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        //also just incase they pass an id in JSON... set id to 0
        //this is to force a save of new item ...instead of update

        theEmployee.setId(0);//insert işlemi yaptıgımız için baslangıcta 0 assign ediyoruz.entitymanager daki merge işlemi 0 ı yeni kayıt olarak algılıyor

        Employee dbEmployee=employeeService.save(theEmployee);

        return dbEmployee;//database den gelen employee e return ediyor
    }

    //add mapping for PUT /employees -update existing employee
    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee=employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for DELETE /employees/{employeeId} --delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee=employeeService.findById(employeeId);

        //throw an exception if null

        if(tempEmployee ==null){
            throw  new RuntimeException("Employee ıd not found" + employeeId);
        }

        employeeService.deleteById(employeeId);

        return  "Deleted employee id - " + employeeId;
    }



}
