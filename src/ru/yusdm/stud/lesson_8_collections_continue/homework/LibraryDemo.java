package ru.yusdm.stud.lesson_8_collections_continue.homework;

import ru.yusdm.stud.lesson_8_collections_continue.homework.author.service.AuthorService;
import ru.yusdm.stud.lesson_8_collections_continue.homework.book.service.BookService;
import ru.yusdm.stud.lesson_8_collections_continue.homework.initializer.datainitializer.BasicDataInitializer;
import ru.yusdm.stud.lesson_8_collections_continue.homework.initializer.datainitializer.DataInitializerFactory;
import ru.yusdm.stud.lesson_8_collections_continue.homework.initializer.datainitializer.DataInitializerType;
import ru.yusdm.stud.lesson_8_collections_continue.homework.initializer.serviceinitializer.ServiceInitializer;
import ru.yusdm.stud.lesson_8_collections_continue.homework.initializer.serviceinitializer.ServicesHolder;
import ru.yusdm.stud.lesson_8_collections_continue.homework.storage.StorageType;

import static ru.yusdm.stud.lesson_8_collections_continue.homework.initializer.datainitializer.DataInitializerType.*;
import static ru.yusdm.stud.lesson_8_collections_continue.homework.storage.StorageType.*;

public class LibraryDemo {

    public static void main(String[] args) throws Exception {
        try {
            StorageType storageType = COLLECTION;
            DataInitializerType dataInitializerType = FROM_TXT_FILE;

            ServicesHolder servicesHolder = new ServiceInitializer().initServices(storageType);
            BasicDataInitializer dataInitializer = DataInitializerFactory.getDataInitializer(dataInitializerType, servicesHolder);
            dataInitializer.initData();

            BookService bookService = servicesHolder.getBookService();
            AuthorService authorService = servicesHolder.getAuthorService();

            bookService.print();
            authorService.print();
            System.out.println("-------------------");
            System.out.println(bookService.findByName("mir").toString());
/*
            Path tempFile = Files.createTempFile("my-file", ".txt");

            LibraryExportData lib = new LibraryExportData();
            String path = tempFile.toAbsolutePath().toString();
            lib.exportAll(path , servicesHolder);

            System.out.println("EXPORT FILE IS " + path); */
            bookService.findByPublishYear(2002);
            System.out.println(bookService.findById((long) 2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
