package com.vamoo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vamoo.entities.Usuario;
import com.vamoo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository UsuarioRepository;
	

	@Autowired
	public UsuarioService(UsuarioRepository UsuarioRepository) {
		this.UsuarioRepository = UsuarioRepository;
	}

	public List<Usuario> getAllUsuarios() {
		return UsuarioRepository.findAll();
	}

	public Usuario getUsuarioById(Long id) {
		Optional<Usuario> Usuario = UsuarioRepository.findById(id);
		return Usuario.orElse(null);
	}

	public Usuario saveUsuario(Usuario Usuario) {
		return UsuarioRepository.save(Usuario);
	}

	public Usuario changeUsuario(Long id, Usuario changeU) {
		Optional<Usuario> existeUsuario = UsuarioRepository.findById(id);
		if (existeUsuario.isPresent()) {
			changeU.setId(id);
			return UsuarioRepository.save(changeU);
		}
		return null;
	}

	public boolean deleteUsuario(Long id) {
		Optional<Usuario> existeUsuario= UsuarioRepository.findById(id);
		if (existeUsuario.isPresent()) {
			UsuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
