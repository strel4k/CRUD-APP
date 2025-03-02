package com.example.crud.controller;

import com.example.crud.model.Post;
import com.example.crud.model.PostStatus;
import com.example.crud.model.Writer;
import com.example.crud.repository.WriterRepository;

import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    public Writer getWriterById(Integer id) {
        return writerRepository.getById(id);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }

    public Writer createWriter(Integer id, String firstName, String lastName, List<Post> posts, PostStatus status) {
        Writer writer = new Writer(id, firstName, lastName, posts, status);
        return writerRepository.save(writer);
    }

    public Writer updateWriter(Integer id, String firstName, String lastName, List<Post> posts, PostStatus status) {
        Writer writer = new Writer(id, firstName, lastName, posts, status);
        return writerRepository.update(writer);
    }

    public void deleteWriterById(Integer id) {
        writerRepository.deleteById(id);
    }
}
