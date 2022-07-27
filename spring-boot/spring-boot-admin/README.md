# Aplicativo Forum

Projeto Forum Alura para estudos da Formação em <b>Spring Framework</b>.

Para rodar via CMD temos que exportar as variaveis com a URL, username, password e secret para ter mais "segurança".

```java
java -jar "-Dspring.profiles.active=prod" -DFORUM_DATABASE_URL=jdbc:h2:mem:alura-forum -DFORUM_DATABASE_USERNAME=sa -DFORUM_DATABASE_PASSWORD= -DFORUM_JWT_SECRET=123456 forum.jar
```
<p align="center">
	<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
    	<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
	<img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white" />
	<img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white" /> 
</p>

<ol>
    <li> 
        Criação de uma API Rest simples com framework spring:
        <ul>
            <li> Endpoints. </li>
            <li> Spring Data. </li>
            <li> Post. </li>
            <li> Bean Validation. </li>
            <li> PUT, DELETE e tratamento de erro. </li>
        </ul>
    </li>
    <li> 
        Spring Boot API Rest - Segurança da API, Cache e Monitoramento:
        <ul>
            <li> Paginação e ordenação de recursos. </li>
            <li> Melhorando o desempenho com Spring Cache. </li>
            <li> Proteção com Spring Security. </li>
            <li> Gerando Token com JWT. </li>
            <li> Autenticando via JWT. </li>
            <li> Monitoramento com Spring Boot Actuator. </li>
            <li> Documentação da API com Swagger. </li>
        </ul>
    </li>
    <li> 
        Spring Boot e teste - Testes e deploy:
	<ul>
            <li> Mais segurança. </li>
            <li> Profiles. </li>
            <li> Teste automatizados. </li>
	    <li> Teste automatizados. </li>
	    <li> Deploy. </li>
        </ul>
    </li>
</ol>

Para gerar um WAR devemos alterar o arquivo .pom da seguinte forma:
```maven
	<packaging>war</packaging>
	...
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-tomcat</artifactId>
		<scope>provided</scope>
	</dependency>
```

Devemos alterar também o ForumApplication:
```java
@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport
public class ForumApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ForumApplication.class);
	}

}
```
