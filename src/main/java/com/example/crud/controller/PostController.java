package com.example.crud.controller;

import com.example.crud.model.Label;
import com.example.crud.model.Post;
import com.example.crud.model.PostStatus;
import com.example.crud.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(Integer id) {
        return postRepository.getById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post createPost(Integer id, String content, LocalDateTime created, LocalDateTime updated, List<Label> labels, PostStatus status) {
        Post post = new Post(id, content, created, updated, labels, status);
        return postRepository.save(post);
    }

    public Post updatePost(Integer id, String content, LocalDateTime created, LocalDateTime updated, List<Label> labels, PostStatus status) {
        Post post = new Post(id, content, created, updated, labels, status);
        return postRepository.update(post);
    }

    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }
}
