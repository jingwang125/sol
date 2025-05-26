package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void register(UserDto userDto) {
		// 입력값 검증
		if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("이메일을 입력해주세요.");
		}
		if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
			throw new IllegalArgumentException("비밀번호를 입력해주세요.");
		}
		if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("이름을 입력해주세요.");
		}
		
		// 이메일 형식 검증
		if (!userDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
		}
		
		// 비밀번호 길이 검증
		if (userDto.getPassword().length() < 6) {
			throw new IllegalArgumentException("비밀번호는 6자 이상이어야 합니다.");
		}
		
		// 이메일 중복 체크
		int emailCount = userMapper.countByEmail(userDto.getEmail());
		logger.info("Checking email: {}, Count: {}", userDto.getEmail(), emailCount);
		
		if (emailCount > 0) {
			throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
		}
		
		// 비밀번호 암호화
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		// 사용자 저장
		try {
			userMapper.insertUser(userDto);
			logger.debug("User registered successfully: {}", userDto.getEmail());
		} catch (Exception e) {
			logger.error("Error registering user: {}", e.getMessage(), e);
			throw new RuntimeException("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
		}
	}
	
	public UserDto login(String email, String password) {
		// 입력값 검증
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("이메일을 입력해주세요.");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("비밀번호를 입력해주세요.");
		}
		// 사용자 조회
		UserDto user = userMapper.findByEmail(email);
		
		if (user == null) {
			logger.warn("Login failed: User not found for email: {}", email);
			throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
		}
		
		// 비밀번호 검증
		boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
		logger.info("Password matches: {}", passwordMatches);
		
		if (!passwordMatches) {
			logger.warn("Login failed: Password mismatch for email: {}", email);
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		
		return user;
	}
	
}
