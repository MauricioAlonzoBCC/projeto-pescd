package br.ufscar.pescd;

import br.ufscar.pescd.model.Usuario;

import br.ufscar.pescd.services.UsuarioService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class PescdApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(
						PescdApplication.class,
						args
				);

		UsuarioService usuarioService =
				context.getBean(UsuarioService.class);

		Usuario admin = new Usuario(
				null,
				"Administrador",
				List.of("ROLE_ADMINISTRADOR"),
				"admin",
				"123"
		);

		usuarioService.salvar(admin);

		Usuario aluno = new Usuario(
				null,
				"Aluno",
				List.of("ROLE_ALUNO"),
				"aluno",
				"123"
		);

		usuarioService.salvar(aluno);

		Usuario secretario = new Usuario(
				null,
				"Secretario",
				List.of("ROLE_SECRETARIO"),
				"secretario",
				"123"
		);

		usuarioService.salvar(secretario);

		Usuario supervisor = new Usuario(
				null,
				"Supervisor",
				List.of("ROLE_SUPERVISOR"),
				"supervisor",
				"123"
		);

		usuarioService.salvar(supervisor);

		Usuario responsavel = new Usuario(
				null,
				"Responsavel",
				List.of("ROLE_RESPONSAVEL"),
				"responsavel",
				"123"
		);

		usuarioService.salvar(responsavel);

	}
}