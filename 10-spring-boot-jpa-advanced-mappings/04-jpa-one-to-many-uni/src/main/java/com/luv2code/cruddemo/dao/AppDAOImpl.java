package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
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

        //get the courses
        List<Course> courses=tempInstructor.getCourses();

        //break association of all courses for the instructor
        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

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

    /**
     * Join  fetch bir eager olarak tasarlanır eger bir instructorun coursu varsa join fetch methodunu cagırıyoruz
     * */
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query burada referans aldıgımız nesneleri kullanıyoruz.yani Instructor sınıdfında courses ve instructorDetail referansı oldugu için
        //i.courses ve i.instructorDetail ile onlara erisebiliyoruz
        TypedQuery<Instructor> query =entityManager.createQuery(
                                                    "select i from Instructor i "
                                                            + "JOIN FETCH i.courses "
                                                            + "JOIN FETCH i.instructorDetail "
                                                            + "where i.id = :data",Instructor.class);

        query.setParameter("data",theId);

        //execute the query
        Instructor instructor=query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);


    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {

        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //get the course by id
        Course tempCourse=entityManager.find(Course.class,theId);

        //delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCouse) {
        entityManager.persist(theCouse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        //create Query
        TypedQuery<Course> query=entityManager.createQuery("select c from Course c "
                                                                   +"JOIN FETCH c.reviews "
                                                                    +"where c.id = :data", Course.class);


        query.setParameter("data",theId);


        //execute the query
        Course course=query.getSingleResult();

        return course;

    }
}










