package com.library.repository;

import com.library.annotations.FileDesc;
import com.library.entity.User;

import java.io.*;
import java.util.List;

public class UserRepository implements Repository<User,Integer>{

    private String fileName;
    public UserRepository() {
        Class<User> userClass= User.class;
        if(userClass.isAnnotationPresent(FileDesc.class)){
         FileDesc annotation= userClass.getAnnotation(FileDesc.class);
          this.fileName= annotation.filename();
        }

    }

    @Override
    public List<User> findAll() throws IOException {
        try(BufferedReader br=new BufferedReader(new FileReader(fileName))){
            String line;
            while((line= br.readLine())!=null){
                System.out.println(line);
            }
        }

        return List.of();
    }

    @Override
    public void save(User user) throws IOException {

      try(BufferedWriter bw=new BufferedWriter(new FileWriter(fileName,true))){
          bw.write(user.toString());
      }

    }


    public static void main(String[]args) throws IOException {
       UserRepository userRepository= new UserRepository();
//       userRepository.save(new User(4,"Joseph"));
        userRepository.findAll();
    }
}
