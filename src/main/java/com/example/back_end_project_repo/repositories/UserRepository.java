package com.example.back_end_project_repo.repositories;

import com.example.back_end_project_repo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
