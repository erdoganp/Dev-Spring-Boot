package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using the constructor
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implements the save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override

    public List<Student> findAll() {
        //create query.Databasede ki kolon ismi olmayacak entity de ki kolon ismi kullanılır

        // TypedQuery<Student> theQuery=entityManager.createQuery("FROM Student order by lastName desc",Student.class); default olarak asc geliyor sıralamada ,desc de yapılabilir
        TypedQuery<Student> theQuery=entityManager.createQuery("FROM Student",Student.class);


        //return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {

        //create query
        TypedQuery<Student> theQuery=entityManager.createQuery(
                "FROM Student WHERE lastName=:theData",Student.class);
        //set query parameters
        theQuery.setParameter("theData",theLastName);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional //database üzerinde data da degişiklik yaptıgımız için transactional annotationını kullanıyoruz
    public void update(Student theStudent) {

        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void delete(Integer id) {

        //retrive the student
        Student theStudent =entityManager.find(Student.class,id);


        //delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        //sonunda execute update dyoruz cunku database üzerinde kendi querymizle degişiklik yapıyoruz
        int numberRowsDeleted=entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numberRowsDeleted;
    }


}
