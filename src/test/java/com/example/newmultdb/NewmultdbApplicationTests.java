package com.example.newmultdb;

import com.example.newmultdb.dataprocess.entities.User;
import com.example.newmultdb.dataprocess.repo.UserRepo;
import com.example.newmultdb.dashboard.entities.Product;
import com.example.newmultdb.dashboard.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewmultdbApplicationTests {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Test
    public void dbCheck(){
        User user = User.builder()
                .name("a")
                .address("b")
                .build();

        Product product = Product.builder()
                .title("c")
                .description("d")
                .build();

        userRepo.save(user);
        productRepo.save(product);
        System.out.println("Data saved");
    }

}
