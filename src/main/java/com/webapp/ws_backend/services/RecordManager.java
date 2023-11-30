package com.webapp.ws_backend.services;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import com.webapp.ws_backend.repositories.ESP32RecordsRepository;
import com.webapp.ws_backend.entities.ESP32Record;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordManager {

    private final ESP32RecordsRepository esp32RecordsRepository;

    public RecordManager(ESP32RecordsRepository esp32RecordsRepository, ApplicationEventPublisher eventPublisher) {
        this.esp32RecordsRepository = esp32RecordsRepository;
    }

    @Transactional(readOnly = true)
    public List<ESP32Record> getRecords() {
        return esp32RecordsRepository.findAll();
    }

    @Transactional(readOnly = false)
    public ESP32Record createRecord(ESP32Record record) {
        return esp32RecordsRepository.save(record);
    }

    @Transactional(readOnly = false)
    public ESP32Record createRecordAndDeleteFirst(ESP32Record record) {
        ESP32Record savedRecord = createRecord(record);
        deleteFirstRecord();
        return savedRecord;
    }

    @Transactional(readOnly = false)
    public void deleteFirstRecord() {
        List<ESP32Record> allRecords = esp32RecordsRepository.findAll();
        if (!allRecords.isEmpty()) {
            if (allRecords.size() >= 10) {
                ESP32Record firstRecord = allRecords.get(0);
                esp32RecordsRepository.deleteById(firstRecord.getId());
            }
        }
    }

    @Transactional(readOnly = false)
    public void deleteRecord(Long id) {
        esp32RecordsRepository.deleteById(id);
    }

    public ESP32Record getLastRecord() {
        final var random = new Random();
        return esp32RecordsRepository.findTop1ByOrderByCreatedAtDesc().orElse(
                new ESP32Record(0, 0, 0.0f, 0.0f, 0.0f, Instant.now())
        );
    }
}