package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    static String[][] tasks;


    public static void main(String[] args) {

        System.out.println("Please select an option:");
        String[] options = {"add", "remove", "list", "exit"};
        for (String option : options) {
            System.out.println(option);
        }
        csvReading();
        
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        switch (input) {
            case "add":
                addTask();
                break;
            case "list":
                listTasks();
                break;
            case "remove":
                removeTask();
                break;
            case "exit":
                System.out.println("Bye, bye!");
//                System.exit(0);
                return;
            default:
                System.out.println("Please select a correct option.");

        }
    }


    public static void csvReading() {
        File file = new File("/Users/ktrzonek/Workshop 1/tasks.csv");

        List<String> rows = null; //podzielony na linie
        try {
            rows = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tasks = new String[rows.size()][];

        for (int i = 0; i < rows.size(); i++) {
            String[] columns = rows.get(i).split(",");
            tasks[i] = columns;
        }
    }


    public static void addTask() {
        System.out.println("Please add task description");
        Scanner scan = new Scanner(System.in);
        String newTask = scan.nextLine();
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        String[] newRow = new String[3];
        newRow[0] = newTask;

        System.out.println("Please add task due date");
        Scanner scanDue = new Scanner(System.in);
        String dueDate = scanDue.nextLine();
        newRow[1] = dueDate;

        System.out.println("Is your task important: true/false");
        Scanner scanImportant = new Scanner(System.in);
        String isImportant = scanImportant.nextLine();
        newRow[2] = isImportant;

        tasks[tasks.length - 1] = newRow;


    }

    public static void listTasks() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println();
            System.out.print(i + " : ");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j] + " ");
            }
        }
    }


    public static void removeTask() {
        System.out.println("Please select number to remove.");
        Scanner scanRemove = new Scanner(System.in);
        int removeTask = scanRemove.nextInt();

        if (removeTask < 0 || removeTask >= tasks.length) {
            System.out.println("No task found.");
            return;
        }

        tasks = Arrays.copyOf(tasks, tasks.length - 1);

        int index = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (i == removeTask) {
                continue; // Pomijamy zadanie do usunięcia
            }
            tasks[index] = tasks[i];
            index++;
        }
        System.out.println("Value was successfully deleted.");




        //sprawdzanie poprawnosci tablicy
//        for (int i = 0; i < tasks.length; i++) {
//            System.out.println();
//            System.out.print(i + " : ");
//            for (int j = 0; j < tasks[i].length; j++) {
//                System.out.print(tasks[i][j] + " ");
//            }
//        }


    }






}