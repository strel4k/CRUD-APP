package com.example.crud.repository;

import com.example.crud.model.PostStatus;
import com.example.crud.model.Writer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private static final String FILE_PATH = "writers.json";
    private final Gson gson;

    public GsonWriterRepositoryImpl() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public Writer getById(Integer id) {
        return getAll().stream()
                .filter(writer -> writer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            if (new File(FILE_PATH).length() == 0) return new ArrayList<>();
            return gson.fromJson(reader, new TypeToken<List<Writer>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> writers = getAll();
        writers.add(writer);
        writeToFile(writers);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writers = getAll();
        for (int i = 0; i < writers.size(); i++) {
            if (writers.get(i).getId().equals(writer.getId())) {
                writers.set(i, writer);
                writeToFile(writers);
                return writer;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        List<Writer> writers = getAll();
        writers.forEach(writer -> {
            if (writer.getId().equals(id)) {
                writer.setStatus(PostStatus.DELETED);
            }
        });
        writeToFile(writers);
    }

    private void writeToFile(List<Writer> writers) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(writers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}