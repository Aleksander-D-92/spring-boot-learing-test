package com.example.demo._sidetest;


public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int countAll() {
       return this.bookRepository.countAll();
    }

    public int getSize(String str) {
        return this.bookRepository.getSize(str);
    }
}
