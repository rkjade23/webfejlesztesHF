package com.webapp.ws_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.ws_backend.entities.ESP32Records;

import java.util.Optional;

@Repository
public interface ESP32RecordsRepository extends JpaRepository<ESP32Records, Long> {
    Optional<ESP32Records> findTop1ByOrderByCreatedAtDesc();
}
