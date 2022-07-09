package br.com.alura.forum.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@Profile(value = {"prod", "test"})
public class HelloController {

	@ResponseBody
	@GetMapping
	public String hello() {
		return "Hello World!";
	}
}
