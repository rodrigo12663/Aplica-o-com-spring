package com.example.algamoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.repository.CategoriaRepositorio;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepositorio.findAll();
	}
	
	//@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	//public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
	
		//Categoria categoriaSalvar=categoriaRepositorio.save(categoria);
		
		
		
		//if (categoriaSalvar!=null) {
		//	return ResponseEntity.ok( categoriaSalvar);
		//}
		//else {
		//	return ResponseEntity.badRequest().build();
		//}
	//}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria,HttpServletResponse response) {
		Categoria categoriaSalvar=categoriaRepositorio.save(categoria);
		
		URI uri=ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(categoriaSalvar.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return categoriaSalvar!=null?ResponseEntity.created(uri).body(categoriaSalvar):ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public Optional<Categoria> buscarCategoria(@PathVariable Long id) {
		return categoriaRepositorio.findById(id);
	}
	
}
