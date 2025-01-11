package com.runtracker.repositories;

import com.runtracker.models.Activity;
import com.runtracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
   List<Activity> findByPengguna(User user);
}
