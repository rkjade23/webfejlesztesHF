package com.webapp.ws_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.ws_backend.entities.ESP32Record;

import java.util.Optional;

@Repository
public interface ESP32RecordsRepository extends JpaRepository<ESP32Record, Long> {

    //lekérdezés, ami a legutoljára hozzáadott adatot adja vissza
    Optional<ESP32Record> findTop1ByOrderByCreatedAtDesc();
}
