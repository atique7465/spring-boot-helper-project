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

    /**
     * <b><i>[Steps to help you, it's not recommended to write that much of unnecessary details in method document/comment]</i></b><br/><br/>
     *
     * <b>Step 01:</b> check if the student already exist with same id, if exist throw exception.<br/>
     * <b>Step 02:</b> if step 01 pass, we can proceed to save the student info. first map/convert the DTO to JPA entity<br/>
     * <b>Step 03:</b> Save the entity by calling dao layer<br/>
     * <b>Step 04:</b> map/convert saved entity to DTO and return
     */
    @Override
    public Student save(Student request) {

        //Step 01
        Optional<StudentEntity> existingEntity = studentDao.get(request.getId());
        if (existingEntity.isPresent()) {
            throw new IllegalArgumentException("Student already exist in database with id: " + request.getId());
        }

        //Step 02
        StudentEntity newEntity = getEntityFromDto(request);

        //Step 03
        newEntity = studentDao.save(newEntity);

        //Step 04
        return getDtoFromEntity(newEntity);
    }

    /**
     * Entity to DTO Mapper using Student builder
     */
    private Student getDtoFromEntity(StudentEntity entity) {

        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .department(entity.getDepartment())
                .build();
    }

    /**
     * DTO to Entity Mapper using StudentEntity builder
     */
    private StudentEntity getEntityFromDto(Student request) {

        return StudentEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .gender(request.getGender())
                .department(request.getDepartment())
                .build();
    }

    /**
     * <b><i>[Steps to help you, it's not recommended to write that much of unnecessary details in method document/comment]</i></b><br/><br/>
     *
     * <b>Step 01:</b> get student entity from dao layer with id<br/>
     * <b>Step 02:</b> if entity is null, throw exception that the queried student not found in database<br/>
     * <b>Step 03:</b> if step 02 pass, that means student found in database, map/convert found entity to DTO and return
     */
    @Override
    public Student get(Long id) {

        //Step 01
        Optional<StudentEntity> entity = studentDao.get(id);

        //Step 02
        if (entity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found with id: " + id);
        }

        //Step 03
        return getDtoFromEntity(entity.get());
    }

    /**
     * <b><i>[Steps to help you, it's not recommended to write that much of unnecessary details in method document/comment]</i></b><br/><br/>
     *
     * <b>Step 01:</b> get all student entities from dao layer<br/>
     * <b>Step 02:</b> map/convert entities to DTO and save to result<br/>
     * <b>Step 03:</b> return result
     */
    @Override
    public List<Student> getList() {

        List<Student> result = new ArrayList<>();

        //Step 01
        List<StudentEntity> entities = studentDao.getList();

        //Step 02
        for (StudentEntity entity : entities) {
            result.add(getDtoFromEntity(entity));
        }

        //Step 03
        return result;
    }

    /**
     * <b><i>[Steps to help you, it's not recommended to write that much of unnecessary details in method document/comment]</i></b><br/><br/>
     *
     * <b>Step 01:</b> if argument id is mismatch with request DTO id, throw invalid request exception.<br/>
     * <b>Step 02:</b> if step 01 pass, we can proceed to update the student info. first get the entity with id from dao layer, if not found throw exception.<br/>
     * <b>Step 03:</b> if step 02 pass, get entity value from optional, set updated properties from DTO to entity.<br/>
     * <b>Step 04:</b> Save the entity by calling dao layer.<br/>
     * <b>Step 05:</b> map/convert saved entity to DTO and return.
     */
    @Override
    public Student update(Long id, Student request) {

        //Step 01
        if (id.compareTo(request.getId()) != 0) {
            throw new InvalidRequestStateException("Requested ID and Student ID mismatch");
        }

        //Step 02
        Optional<StudentEntity> existingEntity = studentDao.get(id);
        if (existingEntity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found to update with id: " + id);
        }

        //Step 03
        StudentEntity entity = existingEntity.get();
        entity.setName(request.getName());
        entity.setGender(request.getGender());
        entity.setDepartment(request.getDepartment());

        //Step 04
        entity = studentDao.save(entity);

        //Steo 05
        return getDtoFromEntity(entity);
    }

    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }
}
