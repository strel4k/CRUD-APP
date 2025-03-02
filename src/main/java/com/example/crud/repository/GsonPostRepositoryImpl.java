package com.example.crud.repository;

import com.example.crud.model.Post;
import com.example.crud.model.PostStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {
    private static final String FILE_PATH = "posts.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    @Override
    public Post getById(Integer id) {
        return getAll().stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Post> getAll() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Post>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Post save(Post post) {
        List<Post> posts = getAll();
        posts.add(post);
        writeToFile(posts);
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> posts = getAll();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId().equals(post.getId())) {
                posts.set(i, post);
                writeToFile(posts);
                return post;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        List<Post> posts = getAll();
        posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .ifPresent(post -> post.setStatus(PostStatus.DELETED));
        writeToFile(posts);
    }

    private void writeToFile(List<Post> posts) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
