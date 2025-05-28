package com.example.taskmanagmentsystem.Repositories;
//
//import com.example.taskmanagmentsystem.entities.User;
//import com.example.taskmanagmentsystem.utils.GeneralRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends GeneralRepository<User> {
//    User findByLogin(String login);
//    User findByEmail(String email);
//    boolean existsByLogin(String login);
//    boolean existsByEmail(String email);
//}
import com.example.taskmanagmentsystem.entities.Project;
import com.example.taskmanagmentsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login); // Wyszukaj po loginie
    boolean existsByLogin(String login); // Sprawdź czy login istnieje

}
