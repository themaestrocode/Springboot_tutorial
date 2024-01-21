package com.themaestrocode.Springboot.tutorial.service;

import com.themaestrocode.Springboot.tutorial.entity.Department;
import com.themaestrocode.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Computer Science")
                .departmentAddress("Akungba-Akoko")
                .departmentCode("CSC")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("Computer Science")).thenReturn(department);
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
    }

    @Test //Test for findDepartmentByName
    @DisplayName("Get data based on valid Department name")
    public void whenValidDepartmentNameThenDepartmentFound() {
        String departmentName = "Computer Science";

        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test // Test for saveDepartment
    @DisplayName("Save department when department object passed")
    public void whenSuccessfulSaveDepartment() {
        Department department = new Department(1L, "Computer Science", "Akungba-Akoko", "CSC");

        Department savedDepartment = departmentService.saveDepartment(department);

        assertNotNull(savedDepartment);
        assertEquals(department, savedDepartment);
    }
}