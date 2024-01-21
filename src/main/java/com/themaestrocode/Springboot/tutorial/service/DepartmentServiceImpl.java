package com.themaestrocode.Springboot.tutorial.service;

import com.themaestrocode.Springboot.tutorial.entity.Department;
import com.themaestrocode.Springboot.tutorial.error.DepartmentNotFoundException;
import com.themaestrocode.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);

        // if the department is not found, this Exception is thrown
        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Found!");
        }

        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department departmentFromDB = departmentRepository.findById(departmentId).get();

        //checking if the department name was passed for update.
        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            //if the name attribute was passed, then update it through the departmentFromDD object, which we used to get the
            //actual department that is being updated. Same goes for the departmentAddress and departmentCode as well.
            departmentFromDB.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            departmentFromDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            departmentFromDB.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(departmentFromDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
