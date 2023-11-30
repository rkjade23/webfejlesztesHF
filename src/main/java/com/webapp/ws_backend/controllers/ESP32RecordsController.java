package com.webapp.ws_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.ws_backend.services.RecordManager;

import jakarta.validation.Valid;

import com.webapp.ws_backend.entities.ESP32Record;

@RestController
@RequestMapping
public class ESP32RecordsController {

    private final RecordManager esp32RecordsService;

    public ESP32RecordsController(RecordManager esp32RecordsService) {
        this.esp32RecordsService = esp32RecordsService;
    }

    //az összes rendelkezésre alló betöltése az adatbázisból
    @GetMapping("/records")
    public List<ESP32Record> getAllRecords() {
        return esp32RecordsService.getRecords();
    }

    //legfrissebb adatsor betöltése az adatbázisból
    @GetMapping("/record")
    public ESP32Record getLastRecord() {
        return esp32RecordsService.getLastRecord();
    }

    //adatsorok létrehozása az adatbázisban és ha már van n darab adat, a legrégebbi adat törlése
    @PostMapping("/records")
    public ResponseEntity<ESP32Record> createRecord(@RequestBody @Valid ESP32Record record) {
        final var savedRecord = esp32RecordsService.createRecordAndDeleteFirst(record);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    //adatsor törlése ID alapján
    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        esp32RecordsService.deleteRecord(id);
        return ResponseEntity.ok().build();
    } 
}