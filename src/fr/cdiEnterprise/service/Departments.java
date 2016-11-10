package fr.cdiEnterprise.service;

import java.util.ArrayList;

import fr.cdiEnterprise.model.Department;

/*
* ArrayList of departments
* 
* @author: Anaïs
* @version: 24/10/2016
*/


public class Departments extends ArrayList<Department> {

	private static final long serialVersionUID = 1L;

	public Department getDepartment(String name) {

		Department departmentFound = null;
		
		for (Department department : this) {
			if (department.getDepartmentName().equals(name)) {
				departmentFound = department;
			}
		}
		return departmentFound;
	}
}