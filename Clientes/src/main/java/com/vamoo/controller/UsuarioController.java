package com.vamoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vamoo.entities.Usuario;
import com.vamoo.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag( name = "Usuarios", description = "API REST DE GERENCIAMENTO DE USUÁRIOS")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService UsuarioService;

	@Autowired
	public UsuarioController(UsuarioService UsuarioService) {
		this.UsuarioService = UsuarioService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza por id")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id) {
		Usuario Usuario = UsuarioService.getUsuarioById(id);
		if (Usuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	@Operation (summary = "Apresenta todos os usuários")
	public ResponseEntity<List<Usuario>> buscaTodasLigacoesControl() {
		List<Usuario> Usuario = UsuarioService.getAllUsuarios();
		return ResponseEntity.ok(Usuario);
	}

	@PostMapping("/")
	@Operation (summary = "Cadastro Usuário")
	public ResponseEntity<Usuario> saveUsuarioControl(@RequestBody @Valid Usuario Usuario) {
		Usuario saveUsuario = UsuarioService.saveUsuario(Usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUsuario);
	}

	@PutMapping("/{id}")
	@Operation (summary ="Altera um user")
	public ResponseEntity<Usuario> alteraUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario Usuario) {
		Usuario alteraUsuario = UsuarioService.changeUsuario(id, Usuario);

		if (alteraUsuario != null) {
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um user")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean delete = UsuarioService.deleteUsuario(id);
		if (delete) {
			return ResponseEntity.ok().body("O produto foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}
}