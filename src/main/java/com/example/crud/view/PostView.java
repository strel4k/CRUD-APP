package com.example.crud.view;

import com.example.crud.controller.PostController;
import com.example.crud.model.Post;
import com.example.crud.model.PostStatus;
import com.example.crud.model.Writer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PostView {

    private final PostController postController;
    private final Scanner scanner;

    public PostView(PostController postController) {
        this.postController = postController;
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        while (true) {
            System.out.println("\nPost menu: ");
            System.out.println("1. Create post");
            System.out.println("2. View post by ID");
            System.out.println("3. View all posts");
            System.out.println("4. Update post");
            System.out.println("5. Delete post");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createPost();
                case 2: getPostById();
                case 3: getAllPosts();
                case 4: updatePost();
                case 5: deletePost();
                case 6: {
                    System.out.println("Exit from app");
                    return;
                }

                default:
                    System.out.println("Invalid. Try again.");
            }
        }
    }
    private void createPost() {
        System.out.print("Enter post ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter post content: ");
        String postContent = scanner.nextLine();

        Post post = new Post(id, postContent, LocalDateTime.now(), LocalDateTime.now(), List.of(), PostStatus.ACTIVE);
        postController.createPost(id, postContent, LocalDateTime.now(), LocalDateTime.now(), List.of(), PostStatus.ACTIVE);
        System.out.println("Post has been created");
    }

    private void getPostById() {
        System.out.print("Enter post ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Post post = postController.getPostById(id);
        System.out.println(post != null ? post : "Post not found.");
    }

    private void getAllPosts() {
        List<Post> posts = postController.getAllPosts();
        posts.forEach(System.out::println);
    }

    private void updatePost() {
        System.out.print("Enter post ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Content: ");
        String content = scanner.nextLine();

        postController.updatePost(id, content, LocalDateTime.now(), LocalDateTime.now(), List.of(), PostStatus.ACTIVE);
        System.out.println("Post has been udpated successfully");
    }

    private void deletePost() {
        System.out.print("Enterr post ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        postController.deletePostById(id);
        System.out.println("Post has been deleted.");
    }
}
