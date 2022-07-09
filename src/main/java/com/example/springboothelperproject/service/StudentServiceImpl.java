package com.example.springboothelperproject.service;

import com.example.springboothelperproject.dto.Student;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author atiQue
 * @since 09'Jul 2022 at 2:25 PM
 */

@Service
public class StudentServiceImpl implements StudentService {

    //in class student info database in replace of DBMS
    private static final Map<Long, Student> STUDENT_MAP = new HashMap<>();

    @Override
    public Student save(Student request) {

        if (STUDENT_MAP.get(request.getId()) != null) {
            throw new IllegalArgumentException("Student already exist with id: " + request.getId());
        }

        STUDENT_MAP.put(request.getId(), request);

        return STUDENT_MAP.get(request.getId());
    }

    @Override
    public Student get(Long id) {

        if (STUDENT_MAP.get(id) == null) {
            throw new PropertyNotFoundException("Student not found with id: " + id);
        }

        return STUDENT_MAP.get(id);
    }

    @Override
    public List<Student> getList() {
        return STUDENT_MAP.values().stream().toList();
    }

    @Override
    public Student update(Long id, Student request) {

        if (id.compareTo(request.getId()) != 0) {
            throw new InvalidRequestStateException("Requested ID and Student ID mismatch");
        }

        if (STUDENT_MAP.get(id) == null) {
            throw new PropertyNotFoundException("Student not found with id: " + id);
        }

        STUDENT_MAP.put(id, request);

        return STUDENT_MAP.get(id);
    }

    @Override
    public void delete(Long id) {

        if (STUDENT_MAP.get(id) == null) {
            throw new PropertyNotFoundException("Student not found with id: " + id);
        }

        STUDENT_MAP.remove(id);
    }
}
