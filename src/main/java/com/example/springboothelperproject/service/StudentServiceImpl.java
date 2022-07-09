package com.example.springboothelperproject.service;

import com.example.springboothelperproject.dao.StudentDao;
import com.example.springboothelperproject.entity.StudentEntity;
import com.example.springboothelperproject.dto.Student;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 2:25 PM
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student save(Student request) {

        //check if the student already exist. if exist throw exception.
        //get entity from dao layer
        Optional<StudentEntity> existingEntity = studentDao.get(request.getId());
        if (existingEntity.isPresent()) {
            throw new IllegalArgumentException("Student already exist in database with id: " + request.getId());
        }

        //as student entity does not exist, we can save the info to database via JPA
        //first covert student dto to jpa entity
        StudentEntity newEntity = getEntityFromDto(request);

        //save the entity
        newEntity = studentDao.save(newEntity);

        //convert the entity to dto and return
        return getDtoFromEntity(newEntity);
    }

    private Student getDtoFromEntity(StudentEntity entity) {

        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .department(entity.getDepartment())
                .build();
    }

    private StudentEntity getEntityFromDto(Student request) {

        return StudentEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .gender(request.getGender())
                .department(request.getDepartment())
                .build();
    }

    @Override
    public Student get(Long id) {

        //get student entity from dao layer
        Optional<StudentEntity> entity = studentDao.get(id);

        if (entity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found with id: " + id);
        }

        //convert the entity to dto and return
        return getDtoFromEntity(entity.get());
    }

    @Override
    public List<Student> getList() {

        List<Student> result = new ArrayList<>();

        //get student entities fro dao layer
        List<StudentEntity> entities = studentDao.getList();

        //convert all entities to dto and store in result
        for (StudentEntity entity : entities) {
            result.add(getDtoFromEntity(entity));
        }

        return result;
    }

    @Override
    public Student update(Long id, Student request) {

        //check request validity
        if (id.compareTo(request.getId()) != 0) {
            throw new InvalidRequestStateException("Requested ID and Student ID mismatch");
        }

        //get student entity from dao layer
        Optional<StudentEntity> existingEntity = studentDao.get(id);
        if (existingEntity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found to update with id: " + id);
        }

        StudentEntity entity = existingEntity.get();

        //as student exist in database, set updated values to the existing entity properties
        entity.setName(request.getName());
        entity.setGender(request.getGender());
        entity.setDepartment(request.getDepartment());

        //save the changes in database
        entity = studentDao.save(entity);

        //convert the entity to dto and return
        return getDtoFromEntity(entity);
    }

    @Override
    public void delete(Long id) {

        //get student entity from dao layer
        Optional<StudentEntity> existingEntity = studentDao.get(id);
        if (existingEntity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found to delete with id: " + id);
        }

        //as student exist in database, delete it
        studentDao.delete(id);
    }
}
