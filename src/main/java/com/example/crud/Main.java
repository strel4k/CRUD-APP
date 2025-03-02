package com.example.crud;

import com.example.crud.controller.LabelController;
import com.example.crud.controller.PostController;
import com.example.crud.controller.WriterController;
import com.example.crud.repository.*;
import com.example.crud.view.LabelView;
import com.example.crud.view.PostView;
import com.example.crud.view.WriterView;
import java.time.LocalDateTime.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WriterRepository writerRepository = new GsonWriterRepositoryImpl();
        PostRepository postRepository = new GsonPostRepositoryImpl();
        LabelRepository labelRepository = new GsonLabelRepositoryImpl();

        WriterController writerController = new WriterController(writerRepository);
        PostController postController = new PostController(postRepository);
        LabelController labelController = new LabelController(labelRepository);

        WriterView writerView = new WriterView(writerController);
        PostView postView = new PostView(postController);
        LabelView labelView = new LabelView(labelController);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Manage Writers");
                System.out.println("2. Manage Posts");
                System.out.println("3. Manage Labels");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> writerView.displayMenu();
                    case 2 -> postView.displayMenu();
                    case 3 -> labelView.displayMenu();
                    case 4 -> {
                        System.out.println("Exiting application...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        }
    }
}
