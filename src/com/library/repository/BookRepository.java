package com.library.repository;

import com.library.annotations.FileDesc;
import com.library.entity.Book;

import java.io.*;
import java.util.List;

public class BookRepository implements Repository<Book,Integer>{
    private String fileName;

    public BookRepository(){
        Class<Book> bookClass= Book.class;
        if(bookClass.isAnnotationPresent(FileDesc.class)){
          FileDesc annotation=  bookClass.getAnnotation(FileDesc.class);
          this.fileName= annotation.filename();
        }
    }


    @Override
    public List<Book> findAll() throws IOException {
        try(BufferedReader br= new BufferedReader(new FileReader(fileName))){
           String line;
           while ((line= br.readLine())!=null){
               System.out.println(line);
           }

        }

        return List.of();
    }

    @Override
    public void save(Book book) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(book.toString());
        }
    }



      public static void main (String[]args) throws IOException {

        BookRepository bookRepository= new BookRepository();
//        bookRepository.save(new Book(1,"Lemuel",1));
          bookRepository.findAll();
        }
    }

