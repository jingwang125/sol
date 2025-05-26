package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	//로그인 화면이동
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}
	
	//회원가입 화면이동
	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}
	
	//회원가입
	@PostMapping("/register")
	public String register(UserDto userDto, RedirectAttributes redirectAttributes) {
		try {
			userService.register(userDto);
			redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해주세요.");
			return "redirect:/user/login";
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/register";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
			return "redirect:/user/register";
		}
	}
	
	//로그인
	@PostMapping("/login")
	public String login(@RequestParam String email, 
					   @RequestParam String password,
					   HttpSession session,
					   RedirectAttributes redirectAttributes) {
		try {
			UserDto user = userService.login(email, password);
			if (user != null) {
				session.setAttribute("user", user);
				logger.info("User logged in successfully: {}", user.getEmail());
				return "redirect:/board/list";
			} else {
				logger.warn("Login failed: User not found");
				redirectAttributes.addFlashAttribute("errorMessage", "로그인에 실패했습니다.");
				return "redirect:/user/login";
			}
		} catch (IllegalArgumentException e) {
			logger.warn("Login failed: {}", e.getMessage());
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/login";
		} catch (Exception e) {
			logger.error("Login error: ", e);
			redirectAttributes.addFlashAttribute("errorMessage", "로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
			return "redirect:/user/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
