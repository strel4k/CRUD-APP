package com.example.crud.view;

import com.example.crud.controller.WriterController;
import com.example.crud.model.PostStatus;
import com.example.crud.model.Writer;

import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController;
    private final Scanner scanner;

    public WriterView(WriterController writerController) {
        this.writerController = writerController;
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        while (true) {
            System.out.println("\nWriter menu:");
            System.out.println("1. Create writer");
            System.out.println("2. View writer by ID");
            System.out.println("3. View All writers");
            System.out.println("4. Update writer");
            System.out.println("5. Delete writer");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createWriter();
                case 2: getWriterById();
                case 3: getAllWriters();
                case 4: updateWriter();
                case 5: deleteWriter();
                case 6: {
                    System.out.println("Exit from app");
                    return;
                }

                default:
                    System.out.println("Invalid. Try again");
            }
        }
    }

    private void createWriter() {
        System.out.print("Enter writer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        Writer writer = new Writer(id, firstName, lastName, List.of(), PostStatus.ACTIVE);
        writerController.createWriter(id, firstName, lastName, List.of(), PostStatus.ACTIVE);
        System.out.println("Writer has been createrd succesfully");
    }

    private void getWriterById() {
        System.out.print("Enter Writer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Writer writer = writerController.getWriterById(id);
        System.out.println(writer != null ? writer : "Writer nit foudn.");
    }

    private void getAllWriters() {
        List<Writer> writers = writerController.getAllWriters();
        writers.forEach(System.out::println);
    }

    private void updateWriter() {
        System.out.print("Enter writer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter New last name: ");
        String lastName = scanner.nextLine();

        writerController.updateWriter(id, firstName, lastName, List.of(), PostStatus.ACTIVE);
        System.out.println("Writer has been updated successfully");
    }

    private void deleteWriter() {
        System.out.print("Enter Writer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        writerController.deleteWriterById(id);
        System.out.println("Writer has been deleted.");
    }
}
