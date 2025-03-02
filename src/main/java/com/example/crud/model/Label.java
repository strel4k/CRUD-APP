package com.example.crud.model;

public class Label {
    private Integer id;
    private String name;
    private PostStatus status;

    public Label(Integer id, String name, PostStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public PostStatus getStatus() { return status; }
    public void setStatus(PostStatus status) { this.status = status; }
}
