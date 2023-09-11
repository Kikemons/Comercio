package com.comercio;

import com.comercio.model.Usuario;
import com.comercio.services.UsuarioServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ComercioApplicationTests {
	@Autowired
	private UsuarioServices usuarioServices;

	@Test
	void crearUsuario() {
		Usuario us= new Usuario();
		us.setPassword("123");
		us.setNombre("juan");
		us.setEmail("juan@gmail.com");

		Usuario retorno=usuarioServices.crear(us);



	}

}
