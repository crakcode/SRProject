package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	

	// get all employees
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}		
	
		@GetMapping("/user/list")
		public Page<User> getUserList() {
			Pageable pageIndex = PageRequest.of(0, 5,Sort.by("id"));
			Page<User> userlist = userRepository.findAll(pageIndex);
			return userlist;
		}
	
	
//	@PostMapping("/user/login")
//	public ResponseEntity<Object> login(@RequestBody User user) {
//		String Accountid= user.getAccountid();
//		String Password=user.getPassword();
//		User[] uservo=userRepository.findByAccountidAndPassword(Accountid, Password);
//		
//		HashMap<String,String> map=new HashMap();
//		
//		map.put("accountid",uservo[0].getAccountid());
//		map.put("name",uservo[0].getName());
//			
//		if(uservo!=null) {
//			return new ResponseEntity<Object>(map,HttpStatus.OK);
//		}
//		else {
//			return ResponseEntity.ok(null);
//		}
//	}
//	
	@PostMapping("/user/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
		String Accountid= user.getAccountid();
		String Password=user.getPassword();
		User[] uservo=userRepository.findByAccountidAndPassword(Accountid, Password);
		
		HashMap<String,String> map=new HashMap();
		
		map.put("accountid",uservo[0].getAccountid());
		map.put("name",uservo[0].getName());
			
		if(uservo!=null) {
			return ResponseEntity.ok(map);
		}
		else {
			return ResponseEntity.ok(null);
		}
	}

	
	// create employee rest api
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	
	
	
	
	
	//find id 추후 name phone을 받아서 email을 반환하면됨
	
	@PostMapping("/user/findid")
	public ResponseEntity<Map<String, String>>  getUserById(@RequestBody User user) {
		String Accountid= user.getAccountid();
		String Name=user.getName();
		User[] uservo=userRepository.findByAccountidAndName(Accountid, Name);
		System.out.println(uservo[0].getName());
		System.out.println(uservo);
		
		HashMap<String,String> map=new HashMap();

		map.put("password",uservo[0].getPassword());
		System.out.println(map.get("password"));

		if(uservo!=null) {
			return ResponseEntity.ok(map);
		}
		else {
			return ResponseEntity.ok(null);
		}
	}
	
	
	//
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserFindId(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		System.out.println(user.getAccountid());
		return ResponseEntity.ok(user);
	}

	
	
	//update할때 초기화 화면에서 eamil로 조회하는 form 작성
	
	@GetMapping("user/findpw/{accountid}")
	public ResponseEntity<User> getUserFindPw(@PathVariable Long accountid) {
		User user = userRepository.findByAccountid(accountid);
		System.out.println(user.getAccountid());
		return ResponseEntity.ok(user);
	}

	
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userDetail){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		user.setAccountid(userDetail.getAccountid());
		user.setName(userDetail.getName());
		user.setPassword(userDetail.getPassword());
		System.out.println("login method");
		User updateUser=userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	}
	
	

		 
}
	
	
	



