package com.example.crud.controller;
import com.example.crud.model.Label;
import com.example.crud.model.PostStatus;
import com.example.crud.repository.LabelRepository;

import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository;

    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label getLabelById(Integer id) {
        return labelRepository.getById(id);
    }

    public List<Label> getAllLabels() {
        return labelRepository.getAll();
    }

    public Label createLabel(Integer id, String name, PostStatus status) {
        Label label = new Label(id, name, status);
        return labelRepository.save(label);
    }

    public Label updateLabel(Integer id, String name, PostStatus status) {
        Label label = new Label(id, name, status);
        return labelRepository.update(label);
    }

    public void deleteLabelById(Integer id) {
        labelRepository.deleteById(id);
    }
}
