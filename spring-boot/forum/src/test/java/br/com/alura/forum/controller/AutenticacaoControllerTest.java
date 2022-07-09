package br.com.alura.forum.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.alura.forum.controller.form.LoginForm;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

	@Autowired
	private MockMvc mockMVC;
	private URI uri = null;
	
	@Test
	public void deveriaDevolverBadRequestEmailInvalido() throws Exception {
		uri = new URI("/auth");
		String json = this.retornaStringJson("invalido@email.com", "123456");

		mockMVC.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void deveriaDevolverBadRequestSenhaInvalida() throws Exception {
		uri = new URI("/auth");
		String json = this.retornaStringJson("aluno@email.com", "12");
		
		mockMVC.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	@Test
	public void deveriaDevolverSuccess() throws Exception {
		uri = new URI("/auth");
		String json = this.retornaStringJson("aluno@email.com", "123456");
		
		mockMVC.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

	private String retornaStringJson(String email, String senha) {
		LoginForm loginForm = new LoginForm();
		loginForm.setEmail(email);
		loginForm.setSenha(senha);
		
		return new Gson().toJson(loginForm);
	}
	
}
