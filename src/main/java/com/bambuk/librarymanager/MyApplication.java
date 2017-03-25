package com.bambuk.librarymanager;

import com.bambuk.librarymanager.model.Book;
import com.bambuk.librarymanager.services.impl.BookServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main starting class for application.
 *
 * @author Den Boyko
 * @version 1.0
 */
public class MyApplication {

    private static String data = "";
    private static String name;
    private static List<Book> books;
    private static BookServiceImpl bookServiceImpl = new BookServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println(ResourceBundle.getBundle("messages").getString("welcome"));

        boolean waitForCommand = true;

        while (waitForCommand) {


            try {
                String command = scanner.nextLine();
                int spaceIndex = command.indexOf(" ");
                if (spaceIndex != -1) {
                    data = command.substring(spaceIndex + 1, command.length());
                    command = command.substring(0, spaceIndex);
                }

                switch (command) {
                    case "help":
                        System.out.println(bookServiceImpl.help());
                        break;

                    case "add":
                        try {
                            add();
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(ResourceBundle.getBundle("messages").getString("wrong"));
                        }
                        break;

                    case "edit":
                        try {
                            edit();
                        } catch (NoSuchElementException e) {
                            System.out.println(ResourceBundle.getBundle("messages").getString("book_not_found"));
                        }
                        break;

                    case "remove":
                        try {
                            remove();
                        } catch (NoSuchElementException e) {
                            System.out.println(ResourceBundle.getBundle("messages").getString("book_not_found"));
                        }
                        break;

                    case "getAll":
                        getAll();
                        break;

                    case "byName":
                        try {
                            byName();
                        } catch (NoSuchElementException e) {
                            System.out.println(ResourceBundle.getBundle("messages").getString("book_not_found"));
                        }
                        break;

                    case "byAuthor":
                        byAuthor();
                        break;

                    case "exit":
                        waitForCommand = false;
                        break;

                    default:
                        System.out.println(ResourceBundle.getBundle("messages").getString("unknown"));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(ResourceBundle.getBundle("messages").getString("bye"));
        System.exit(0);


    }

    private static void add() {
        name = data;
        String[] book = name.split("\"");
        bookServiceImpl.add(new Book(book[1], book[0]));
        System.out.println(name + " " + ResourceBundle.getBundle("messages").getString("success_add"));
    }

    private static void edit() throws IOException {
        name = data;
        System.out.println(ResourceBundle.getBundle("messages").getString("input"));
        String newName = scanner.next();
        bookServiceImpl.update(name, newName);
        System.out.println(name + " " + ResourceBundle.getBundle("messages").getString("success_edit"));
    }

    private static void remove() {
        name = data;
        bookServiceImpl.delete(name);
        System.out.println(name + " " + ResourceBundle.getBundle("messages").getString("success_remove"));
    }

    private static void getAll() {
        books = bookServiceImpl.getAll().stream()
                .sorted(Comparator.comparing(Book::getName)).collect(Collectors.toList());
        if (books.size() != 0) {
            books.forEach(System.out::println);
        } else {
            System.out.println(ResourceBundle.getBundle("messages").getString("book_not_found"));
        }
    }

    private static void byName() {
        name = data;
        System.out.println(bookServiceImpl.getByName(name));
    }

    private static void byAuthor() {
        String author = data;
        books = bookServiceImpl.getByAuthor(author);
        if (books.size() != 0) {
            books.forEach(System.out::println);
        } else {
            System.out.println(ResourceBundle.getBundle("messages").getString("book_not_found"));
        }
    }

}
