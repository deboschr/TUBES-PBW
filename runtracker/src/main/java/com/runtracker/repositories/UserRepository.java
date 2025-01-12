package com.runtracker.repositories;

import com.runtracker.models.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   @EntityGraph(attributePaths = { "activities" })
   @Query("SELECT u FROM User u WHERE u.email = :email")
   Optional<User> findByEmail(@Param("email") String email);

}
