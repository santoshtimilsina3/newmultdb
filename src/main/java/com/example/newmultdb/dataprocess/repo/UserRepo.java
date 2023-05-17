package com.example.newmultdb.dataprocess.repo;

import com.example.newmultdb.dataprocess.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
