package com.vladkazakov.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vladkazakov.entities.Role;
import com.vladkazakov.entities.User;
import com.vladkazakov.services.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Role getRole(HttpSession session) {
		String role = (String) session.getAttribute("role");
		String name = (String) session.getAttribute("name");
		if (role == null) {
			role = "guest";
		}
		if (name == null) {
			name = "guest";
		}
		return new Role(role, name);
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Boolean logOut(HttpSession session) {
		session.invalidate();
		session.setAttribute("role", "guest");
		return true;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Boolean login(@RequestBody User user, HttpSession session) {
		System.out.println(user.getLogin() + ";" + user.getPassword());
		user = userService.getByLoginAndPassword(user.getLogin(), user.getPassword());
		System.out.println(user);
		if (user == null) {
			return false;
		} else {
			if (user.getIsAdmin()) {
				session.setAttribute("role", "admin");
			} else {
			session.setAttribute("role", "user");
			}
			session.setAttribute("name", user.getName());
			session.setAttribute("idUser", user.getId());
			return true;
		}
	}

	@RequestMapping(value = "/loginAdmin", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Boolean loginAdmin(@RequestBody User user, HttpSession session) {
		System.out.println(user.getLogin() + ";" + user.getPassword());
		user = userService.getByLoginAndPassword(user.getLogin(), user.getPassword());
		System.out.println(user);
		if (user == null) {
			return false;
		} else {
			if (user.getIsAdmin()) {
				session.setAttribute("role", "admin");
				return true;
			} else {
				return false;
			}
		}
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Boolean registrtion(@RequestBody User user, HttpSession session) {
		System.out.println(user.getLogin() + ";" + user.getPassword());
		User temp = userService.getByLogin(user.getLogin());
		if (temp == null) {
			user = userService.add(user);
			session.setAttribute("role", "user");
			session.setAttribute("name", user.getName());
			session.setAttribute("idUser", user.getId());
			return true;
		} else {
			return false;
		}
	}

}
