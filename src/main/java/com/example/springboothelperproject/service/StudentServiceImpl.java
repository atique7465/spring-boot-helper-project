package com.example.springboothelperproject.service;

import com.example.springboothelperproject.dao.StudentDao;
import com.example.springboothelperproject.entity.StudentEntity;
import com.example.springboothelperproject.dto.Student;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        Optional<StudentEntity> existingEntity = studentDao.getByStudentId(request.getStudentId());
        if (existingEntity.isPresent()) {
            throw new IllegalArgumentException("Student already exist in database with studentId: " + request.getStudentId());
        }

        //as student entity does not exist, we can save the info to database via JPA
        //first covert student dto to jpa entity
        StudentEntity newEntity = getEntityFromDto(request);

        //save the entity
        newEntity = studentDao.save(newEntity);

        //convert the entity to dto and return
        return getDtoFromEntity(newEntity);
    }

    /**
     * Entity to DTO Mapper
     */
    private Student getDtoFromEntity(StudentEntity entity) {

        return Student.builder()
                .studentId(entity.getStudentId())
                .name(entity.getName())
                .gender(entity.getGender())
                .department(entity.getDepartment())
                .build();
    }

    /**
     * DTO to Entity Mapper
     */
    private StudentEntity getEntityFromDto(Student request) {

        return StudentEntity.builder()
                .studentId(request.getStudentId())
                .name(request.getName())
                .gender(request.getGender())
                .department(request.getDepartment())
                .build();
    }

    @Override
    public Student get(String studentId) {

        //get student entity from dao layer
        Optional<StudentEntity> entity = studentDao.getByStudentId(studentId);

        if (entity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found with studentId: " + studentId);
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
    public Student update(String studentId, Student request) {

        //check request validity
        if (!StringUtils.hasLength(studentId)) {
            throw new InvalidRequestStateException("studentId can't be null or empty");
        }

        //if requested studentId in update request dto already assigned to another student, we can't use it further.
        if (!studentId.equals(request.getStudentId())) {
            Optional<StudentEntity> requestedStudentIdEntity = studentDao.getByStudentId(request.getStudentId());
            if (requestedStudentIdEntity.isPresent()) {
                throw new InvalidRequestStateException("Requested studentId : " + request.getStudentId() + " in update request dto is already assigned to another student.");
            }
        }

        //request is valid, proceed to update
        //get student entity from dao layer
        Optional<StudentEntity> existingEntity = studentDao.getByStudentId(studentId);
        if (existingEntity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found to update with studentId: " + studentId);
        }

        //get entity value from Optional
        StudentEntity entityToUpdate = existingEntity.get();

        //as student exist in database, set updated values to the existing entity properties
        entityToUpdate.setStudentId(request.getStudentId());
        entityToUpdate.setName(request.getName());
        entityToUpdate.setGender(request.getGender());
        entityToUpdate.setDepartment(request.getDepartment());

        //save the changes in database
        entityToUpdate = studentDao.save(entityToUpdate);

        //convert the entity to dto and return
        return getDtoFromEntity(entityToUpdate);
    }

    @Override
    public void delete(String studentId) {

        //get student entity from dao layer
        Optional<StudentEntity> existingEntity = studentDao.getByStudentId(studentId);
        if (existingEntity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found to delete with studentId: " + studentId);
        }

        //as student exist in database, delete it
        studentDao.deleteByStudentId(studentId);
    }
}
