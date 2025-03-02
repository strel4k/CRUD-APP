package com.example.crud.view;

import com.example.crud.controller.LabelController;
import com.example.crud.model.Label;
import com.example.crud.model.Post;
import com.example.crud.model.PostStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LabelView {

    private final LabelController labelController;
    private final Scanner scanner;

    public LabelView(LabelController labelController) {
        this.labelController = labelController;
        this.scanner = new Scanner(System.in);
    }
    public void displayMenu() {
        while (true) {
            System.out.println("\nLabel menu: ");
            System.out.println("1. Create label");
            System.out.println("2. View label by ID");
            System.out.println("3. View all labales");
            System.out.println("4. Update label");
            System.out.println("5. Delete label");
            System.out.println("6. Exit");
            System.out.print("Choose an option");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createLabel();
                case 2: getLabelById();
                case 3: getAllLabels();
                case 4: updateLabel();
                case 5: deleteLabel();
                case 6: {
                    System.out.println("Exit from app");
                    return;
                }

                default:
                    System.out.println("Invalid. Trry again");
            }
        }
    }
    private void createLabel() {
        System.out.print("Enter label ID");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enterr label name");
        String name = scanner.nextLine();

        Label label = new Label(id, name, PostStatus.ACTIVE);
        labelController.createLabel(id, name, PostStatus.ACTIVE);
        System.out.println("Lable has been created");
    }
    private void getLabelById() {
        System.out.print("Enter label ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Label label = labelController.getLabelById(id);
        System.out.println(label != null ? label : "Label not found");
    }
    private void getAllLabels() {
        List<Label> labels = labelController.getAllLabels();
        labels.forEach(System.out::println);
    }
    private void updateLabel() {
        System.out.print("Enter label ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new label name: ");
        String name = scanner.nextLine();

        labelController.updateLabel(id, name, PostStatus.ACTIVE);
        System.out.println("Lable has been updated successful");
    }
    private void deleteLabel() {
        System.out.print("Enter label ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        labelController.deleteLabelById(id);
        System.out.println("Lable has been deleted.");
    }
}
