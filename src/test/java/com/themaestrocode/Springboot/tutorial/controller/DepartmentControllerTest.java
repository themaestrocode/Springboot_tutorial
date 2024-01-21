package com.themaestrocode.Springboot.tutorial.controller;

import com.themaestrocode.Springboot.tutorial.entity.Department;
import com.themaestrocode.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("Geology and Mineral Science")
                .departmentAddress("Akure")
                .departmentCode("GMS")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() {
        Department inputDepartment = Department.builder()
                .departmentName("Geology and Mineral Science")
                .departmentAddress("Akure")
                .departmentCode("GMS")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        //mockMvc.perform(MockMvcRequestBuilders.post)
    }

    @Test
    void fetchDepartmentById() {
    }
}