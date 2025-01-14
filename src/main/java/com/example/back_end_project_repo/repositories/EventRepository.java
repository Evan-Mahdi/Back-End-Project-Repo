package com.example.back_end_project_repo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back_end_project_repo.models.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
    Optional<Event> findByTitle(String title);

}
