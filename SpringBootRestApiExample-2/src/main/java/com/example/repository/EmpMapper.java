package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.Emp;

@Mapper
public interface EmpMapper {
	@Insert("insert into Emp(ename,job,sal) values(#{ename},#{job},#{sal})")
	public void InsertEmp(Emp emp);
	
	@Update("update Emp set ename=#{ename}, job=#{job}, sal=#{sal} where empno = #{empno}")
	public void UpdateEmp(Emp emp);
	
	@Delete("delete from Emp where empno = #{empno}")
	public void DeleteByEmpno(long empno);
	
	@Delete("delete from Emp")
	public void DeleteAll();
	
	@Select("select * from Emp")
	@ResultType(Emp.class)
	public List<Emp> FindAll();
	
	@Select("select * from Emp where empno = #{empno}")
	public Emp FindByEmpno(long empno);
	
	boolean isUserExist(Emp emp);
}
