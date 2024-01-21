package com.themaestrocode.Springboot.tutorial.repository;

import com.themaestrocode.Springboot.tutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Geology and Mineral Science")
                .departmentAddress("Akure")
                .departmentCode("CSC")
                .build();

        testEntityManager.persist(department);
    }

    @Test
    public void whenFindByIdThenReturnDepartment() {
        Department department = departmentRepository.findById(1L).get();

        //if the two are equal, then the department exists and can be fetched from the DB
        assertEquals(department.getDepartmentName(), "Geology and Mineral Science");
    }
}