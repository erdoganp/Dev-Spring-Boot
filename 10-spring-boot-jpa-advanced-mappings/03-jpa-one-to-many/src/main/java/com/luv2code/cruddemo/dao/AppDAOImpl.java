package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define fields for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor tempInstructor=entityManager.find(Instructor.class,theId);

        //delete the instructor
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve the instructor detail
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class,theId);

        //remove the associated object reference
        //break-bi directional link

        /**
        * instructordetail den silme işlemi gerceklestirdiğimizde sadece instructordetail silinsin diye cascadetype e remove eklemiyoruz.
         * bununla beraber remove islemi gerceklestirmeden once instructorın  instructordetail id sini nulluyoruz ve sonrasında silme islemi gerceklestiriyoruz
        */
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {
        //create the query
        TypedQuery<Course> query=entityManager.createQuery(
                              "from Course where instructor.id=:data", Course.class);
        query.setParameter("data",theId);

        //execute the query
        List<Course> courses =query.getResultList();
        return courses;
    }
}
