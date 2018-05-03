package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Emp;
import com.example.service.EmpService;
import com.example.util.CustomErrorType;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/ky")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	
	@Autowired
	EmpService empService;
	
	//모든 사원 조회
	@RequestMapping(value="/emp/",method=RequestMethod.GET)
	public ResponseEntity<List<Emp>> listAllEmp(){
		logger.info("모든 사원 검색");
		
		List<Emp> emps = empService.findAll();
		if(emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Emp>>(emps, HttpStatus.OK);
	}
	
	//사번으로 사원 조회
	@RequestMapping(value="/emp/{empno}",method = RequestMethod.GET)
	public ResponseEntity<?> getEmp(@PathVariable("empno") long empno){
		logger.info("사번으로 사원을 검색하빈다.",empno);
		Emp emp = empService.selectByEmpno(empno); //사번으로 검색한 사원이 emp에담김
		if(emp == null) {
			logger.error("일치하는 사원이 없습니다.");
			return new ResponseEntity(new CustomErrorType("사번 "+empno+" 번에 해당 사원 없음"),HttpStatus.NOT_FOUND);			
		}
		return new ResponseEntity<Emp>(emp,HttpStatus.OK);
	}
	
	//사원생성
	@RequestMapping(value = "/emp/",method = RequestMethod.POST)
	public ResponseEntity<?> createEmp (@RequestBody Emp emp, UriComponentsBuilder ucBuilder){
		logger.info("새로운 사원 생성",emp);
		
		if(empService.isuserExist(emp)) {
			logger.error("사원생성불가. 이미 있는 사원.",emp.getEname());
			return new ResponseEntity(new CustomErrorType("생성할수없습니다."),HttpStatus.CONFLICT);
		}
		empService.insertEmp(emp);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/ky/emp/{empno}").buildAndExpand(emp.getEmpno()).toUri());;
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	//사원 수정
	@RequestMapping(value = "/emp/{empno}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmp(@PathVariable("empno") long empno,@RequestBody Emp emp){
		logger.info("사원정보를 수정합니다.",empno);
		Emp currentEmp = emp;
		
		if(currentEmp==null) {
			logger.error("찾는 사원이 없습니다.",empno);
			return new ResponseEntity(new CustomErrorType("찾는ㅅ ㅏ원이 없습니다."),HttpStatus.NOT_FOUND);
		}
		
		currentEmp.setEname(emp.getEname());
		currentEmp.setSal(emp.getSal());
		currentEmp.setHiredate(emp.getHiredate());
		currentEmp.setJob(emp.getJob());
		
		empService.updateEmp(currentEmp);
		return new ResponseEntity<Emp>(currentEmp, HttpStatus.OK);
	}
	
	
	//사원 삭제
	@RequestMapping(value = "/emp/{empno}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmp(@PathVariable("empno") long empno){
		logger.info("사번에 맞는 사용자를 삭제합니다.",empno);
		Emp emp = empService.selectByEmpno(empno);
		if(emp == null) {
			logger.error("삭제할 사원을 찾지 못했습니다.");
			return new ResponseEntity(new CustomErrorType("삭제할 사원이 없습니다."+empno),HttpStatus.NOT_FOUND);
			
		}
		empService.deleteByEmpno(empno);
		return new ResponseEntity<Emp>(HttpStatus.NO_CONTENT);
	}
	
	//모든 사원 삭제.
	@RequestMapping(value="/emp/", method= RequestMethod.DELETE)
	public ResponseEntity<Emp> deleteAll(){
		logger.info("모든 사원을 삭제합니다.");
		empService.deleteAll();
		return new ResponseEntity<Emp>(HttpStatus.NO_CONTENT);
	}
}
