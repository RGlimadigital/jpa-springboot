package com.exercicio.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exercicio.course.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
