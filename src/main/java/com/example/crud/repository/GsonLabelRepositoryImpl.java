package com.example.crud.repository;

import com.example.crud.model.Label;
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

public class GsonLabelRepositoryImpl implements LabelRepository {
    private static final String FILE_PATH = "labels.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    @Override
    public Label getById(Integer id) {
        return getAll().stream()
                .filter(label -> label.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Label> getAll() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Label>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Label save(Label label) {
        List<Label> labels = getAll();
        labels.add(label);
        writeToFile(labels);
        return label;
    }

    @Override
    public Label update(Label label) {
        List<Label> labels = getAll();
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getId().equals(label.getId())) {
                labels.set(i, label);
                writeToFile(labels);
                return label;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> labels = getAll();
        labels.stream()
                .filter(label -> label.getId().equals(id))
                .findFirst()
                .ifPresent(label -> label.setStatus(PostStatus.DELETED));
        writeToFile(labels);
    }

    private void writeToFile(List<Label> labels) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(labels, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}