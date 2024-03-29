package ru.yusdm.stud.lesson_8_collections_continue.homework.book.service;

import ru.yusdm.stud.lesson_8_collections_continue.homework.book.domain.Book;
import ru.yusdm.stud.lesson_8_collections_continue.homework.book.repo.BookRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> result = findBookByField(book -> book.getName().trim().equalsIgnoreCase(name));
        if (result.size() == 0) {
            System.out.println("Books not found with name: " + name);
        }
        return result;
    }

    @Override
    public List<Book> findByPublishYear(Integer publishYear) {
        List<Book> result = findBookByField(book -> book.getPublishYear() == publishYear);
        if (result.size() == 0) {
            System.out.println("Books not found with publish year: " + publishYear);
        }
        return result;
    }


    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public int count() {
        return bookRepo.count();
    }

    @Override
    public void print() {
        bookRepo.print();
    }

    @Override
    public void delete(Book book) {
        bookRepo.delete(book);
    }

    @Override
    public Long add(Book book) {
        return bookRepo.add(book);
    }

    @Override
    public Book findById(Long bookId) {
        Optional<Book> result = bookRepo.findById(bookId);
        if (!result.isPresent()) {
            System.out.println("Book not exist with ID " + bookId.toString());
        }
        return result.get();
    }

    @Override
    public Book[] findBooksByAuthorAsArray(long authorId) {
        return bookRepo.findBooksByAuthorAsArray(authorId);
    }

    @Override
    public List<Book> findBooksByAuthorAsList(long authorId) {
        return bookRepo.findBooksByAuthorAsList(authorId);
    }

    public List<Book> findBookByField(Predicate<Book> lambda) {
        return bookRepo.getAllBooks().stream()
                .filter((book) -> lambda.test(book))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> sortByName(List<Book> books) {
        return bookRepo.sort(books, Comparator.comparing(b -> String.valueOf(b.getName())));
    }

    @Override
    public List<Book> sortByPublishYear(List<Book> books) {
        return bookRepo.sort(books, Comparator.comparingInt(Book::getPublishYear));
    }
}
