package com.webapp.ws_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.webapp.ws_backend.entities.ESP32Records;

@RestController
@RequestMapping
public class ESP32RecordsController {

    private final RecordManager esp32RecordsService;

    public ESP32RecordsController(RecordManager esp32RecordsService) {
        this.esp32RecordsService = esp32RecordsService;
    }

    @GetMapping("/records")
    public List<ESP32Records> getAllRecords() {
        return esp32RecordsService.getRecords();
    }


    // , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUES
    @PostMapping("/records")
    public ResponseEntity<ESP32Records> createRecord(@RequestBody @Valid ESP32Records record) {
        ESP32Records savedRecord = esp32RecordsService.createRecord(record);
        esp32RecordsService.deleteFirstRecord();
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        esp32RecordsService.deleteRecord(id);
        return ResponseEntity.ok().build();
    } 
}