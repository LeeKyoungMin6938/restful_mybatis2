package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Emp;
import com.example.repository.EmpMapper;
@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Override
	public void insertEmp(Emp emp) {
		empMapper.InsertEmp(emp);
	}

	@Override
	public void updateEmp(Emp emp) {
		empMapper.UpdateEmp(emp);
	}

	@Override
	public void deleteByEmpno(long empno) {
		empMapper.DeleteByEmpno(empno);
	}

	@Override
	public void deleteAll() {
		empMapper.DeleteAll();
	}

	@Override
	public List<Emp> findAll() {
		return empMapper.FindAll();
	}

	@Override
	public Emp selectByEmpno(long empno) {
		return empMapper.FindByEmpno(empno);
	}

	@Override
	public boolean isuserExist(Emp emp) {
		if(emp==null) {
			return false;
		}else {
			return true;
		}
	}

}
