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

    /**
     * Step 01: check if the student already exist with same studentId. if exist throw exception.
     * Step 02: if step 01 pass, we can proceed to save the student info. first map/convert the DTO to JPA entity
     * Step 03: Save the entity by calling dao layer
     * Step 04: map/convert saved entity to DTO and return
     */
    @Override
    public Student save(Student request) {

        //Step 01
        Optional<StudentEntity> existingEntity = studentDao.getByStudentId(request.getStudentId());
        if (existingEntity.isPresent()) {
            throw new IllegalArgumentException("Student already exist in database with studentId: " + request.getStudentId());
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
                .studentId(entity.getStudentId())
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
                .studentId(request.getStudentId())
                .name(request.getName())
                .gender(request.getGender())
                .department(request.getDepartment())
                .build();
    }

    /**
     * Step 01: get student entity from dao layer
     * Step 02: if entity is null, throw exception that the queried student not found in database
     * Step 03: if step 02 pass, that means student found in database, map/convert found entity to DTO and return
     */
    @Override
    public Student get(String studentId) {

        //Step 01
        Optional<StudentEntity> entity = studentDao.getByStudentId(studentId);

        //Step 02
        if (entity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found with studentId: " + studentId);
        }

        //Step 03
        return getDtoFromEntity(entity.get());
    }

    /**
     * Step 01: get all student entities from dao layer
     * Step 02: map/convert entities to DTO and save to result
     * Step 03: return result
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
     * Step 01: if argument studentId is null, throw exception.
     * Step 02: if requested studentId in update request dto already assigned to another student, throw exception as we can't use it further.
     * Step 03: if step 01 & 02 pass, we can proceed to update the student info. first get the entity with studentId from dao layer, if not found throw exception.
     * Step 04: if step 03 pass, get entity value from optional, set updated properties from DTO to entity.
     * Step 05: Save the entity by calling dao layer.
     * Step 06: map/convert saved entity to DTO and return.
     */
    @Override
    public Student update(String studentId, Student request) {

        //Step 01
        if (!StringUtils.hasLength(studentId)) {
            throw new InvalidRequestStateException("studentId can't be null or empty");
        }

        //Step 02 [This step can also be done by catching unique constraint violation exception from DB layer,
        //it's a design decision, for now we won't go that far, just a basic check to keep our logic simple]
        if (!studentId.equals(request.getStudentId())) {
            Optional<StudentEntity> requestedStudentIdEntity = studentDao.getByStudentId(request.getStudentId());
            if (requestedStudentIdEntity.isPresent()) {
                throw new InvalidRequestStateException("Requested studentId : " + request.getStudentId() + " in update request dto is already assigned to another student.");
            }
        }

        //Step 03
        Optional<StudentEntity> existingEntity = studentDao.getByStudentId(studentId);
        if (existingEntity.isEmpty()) {
            throw new PropertyNotFoundException("Student not found to update with studentId: " + studentId);
        }

        //Step 04
        StudentEntity entityToUpdate = existingEntity.get();
        entityToUpdate.setStudentId(request.getStudentId());
        entityToUpdate.setName(request.getName());
        entityToUpdate.setGender(request.getGender());
        entityToUpdate.setDepartment(request.getDepartment());

        //Step 05
        entityToUpdate = studentDao.save(entityToUpdate);

        //Step 06
        return getDtoFromEntity(entityToUpdate);
    }

    @Override
    public void delete(String studentId) {
        studentDao.deleteByStudentId(studentId);
    }
}
