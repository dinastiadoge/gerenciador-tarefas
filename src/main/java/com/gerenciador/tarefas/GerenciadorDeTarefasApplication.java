package com.gerenciador.tarefas;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;
import com.gerenciador.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GerenciadorDeTarefasApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usarioService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername("admin");
		usuario.setPassword("admin");

		List<Role> roles = new ArrayList<>();
		Role roleAdmin = new Role();
		roleAdmin.setNome(PermissaoEnum.ADMINISTRADOR);
		Role roleUsuario = new Role();
		roleUsuario.setNome(PermissaoEnum.USUARIO);
		roles.add(roleAdmin);
		roles.add(roleUsuario);
		usuario.setRoles(roles);

		usarioService.salvarUsuario(usuario);
	}
}
