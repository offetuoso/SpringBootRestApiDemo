package com.example.demo.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

@RestController
public class UserProfileController {
	
	private Map<String, UserProfile> userMap;
	
	@PostConstruct
	public void init() {
		userMap = new HashMap<String, UserProfile>();
		userMap.put("1", new UserProfile("1", "홍길동", "111-1111", "서울시 영등포구 신길1동"));
		userMap.put("2", new UserProfile("2", "김근로", "111-1112", "서울시 영등포구 신길2동"));
		userMap.put("3", new UserProfile("3", "박영업", "111-1113", "서울시 영등포구 신길3동"));
	}
	
	@GetMapping("/users/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		return userMap.get(id);
		
	}
	
	@GetMapping("/users/all")
	public List<UserProfile> getUserProfile() {
		
		return new ArrayList<UserProfile>(userMap.values());
		
	}
	
	@PutMapping("/users/{id}")
	public void putUserProfile(@PathVariable("id") String id
                                 , @RequestParam("name") String name
                                 , @RequestParam("phone") String phone
                                 , @RequestParam("address") String address) {
		
		UserProfile userProfile = new UserProfile(id,name,phone,address);
		userMap.put(id,userProfile);
		
	}
	
	@PostMapping("/users/{id}")
	public void postUserProfile(@PathVariable("id") String id
								, @RequestParam("name") String name
								, @RequestParam("phone") String phone
								, @RequestParam("address") String address) {
		
		UserProfile userProfile = userMap.get(id);
		userProfile.setName(name);
		userProfile.setPhone(phone);
		userProfile.setAddress(address);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		
		userMap.remove(id);
		
	}
}

