package com.exercicio.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercicio.course.entities.Post;
import com.exercicio.course.repositories.PostRepository;
import com.exercicio.course.services.exceptions.ResourceNotFoundException;


@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll() {
		return repository.findAll();
	}
	
	public Post findById(Long id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public Post insert(Post obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Post update(Long id, Post obj ) {
		Post entity = repository.getReferenceById(id);
		updatePost(entity, obj);
		return repository.save(entity);
	}
	
	public void updatePost(Post entity, Post obj) {
		entity.setTitle(obj.getTitle());
		entity.setContent(obj.getContent());
	}
}
