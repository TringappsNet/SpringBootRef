package com.springboot.springbootref.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBook(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public boolean deleteBook(Long id) {

        if(getBook(id)!= null){
            bookRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
    public Book editBook(Long id,Book book){
        Book book1 = getBook(id);
        if(book1 != null){
            book1.setName(book.getName());
            book1.setAuthor(book.getAuthor());
            book1.setEdition(book.getEdition());
            return bookRepository.save(book1);
        }
        else{
            return null;
        }
    }
}
