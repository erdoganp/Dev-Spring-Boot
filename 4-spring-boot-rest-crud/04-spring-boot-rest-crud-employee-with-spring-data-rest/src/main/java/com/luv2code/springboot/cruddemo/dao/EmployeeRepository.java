package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//@RepositoryRestResource(path="members") --eger bu sekilde tanımlarsak api a members ile erişiyoruz.
//yani Rest Datanın özelliği olan bas harfi kucuk ve sonuna s takısı gelen entity ismi yerine Rest resource da belirtilen path üzerinden erişim oluyor
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
                                                        //EntityType,Primarykey

    //that's it .no need to any write code

}
