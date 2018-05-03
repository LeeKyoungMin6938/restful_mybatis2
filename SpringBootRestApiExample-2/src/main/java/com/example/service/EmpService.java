package com.example.service;

import java.util.List;

import com.example.model.Emp;

public interface EmpService {
	public void insertEmp(Emp emp);
	public void updateEmp(Emp emp);
	public void deleteByEmpno(long empno);
	public void deleteAll();
	public List<Emp> findAll();
	public Emp selectByEmpno(long empno);
	public boolean isuserExist(Emp emp);	
	
}
