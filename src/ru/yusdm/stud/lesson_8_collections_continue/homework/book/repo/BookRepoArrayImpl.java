package ru.yusdm.stud.lesson_8_collections_continue.homework.book.repo;

import ru.yusdm.stud.lesson_8_collections_continue.homework.author.domain.Author;
import ru.yusdm.stud.lesson_8_collections_continue.homework.book.domain.Book;
import ru.yusdm.stud.lesson_8_collections_continue.homework.storage.ArrayStorage;
import ru.yusdm.stud.lesson_8_collections_continue.homework.common.repo.*;

import java.util.ArrayList;
import java.util.List;

public class BookRepoArrayImpl implements BookRepo, BaseRepo<Book> {

    @Override
    public int count() {
        return ArrayStorage.getTotalBooks();
    }

   @Override
    public void print() {
        for (Book book : ArrayStorage.getAllBooks()) {
            if (book != null) {
                System.out.println(book.toString());
            }
        }
    }

   @Override
    public void delete(Book book) {
        ArrayStorage.removeBook(book);
    }

   @Override
    public Long add(Book book) {
        ArrayStorage.addBook(book);
        return book.getId();
    }

    @Override
    public Book[] findBooksByAuthorAsArray(long authorId) {
        return findBooksByAuthorAsList(authorId).toArray(new Book[0]);
    }

    @Override
    public List<Book> findBooksByAuthorAsList(long authorId) {
        List<Book> found = new ArrayList<>();

        for (Book book : ArrayStorage.getAllBooks()) {

            for (Author a : book.getAuthors()) {
                if (Long.valueOf(authorId).equals(a.getId())) {
                    found.add(book);
                    break;
                }
            }

        }

        return found;
    }
}
