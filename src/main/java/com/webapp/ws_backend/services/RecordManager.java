package com.webapp.ws_backend.services;

import java.util.List;

import com.webapp.ws_backend.repositories.ESP32RecordsRepository;
import com.webapp.ws_backend.entities.ESP32Records;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RecordManager {

    private final ESP32RecordsRepository esp32RecordsRepository;

    public RecordManager(ESP32RecordsRepository esp32RecordsRepository, ApplicationEventPublisher eventPublisher) {
        this.esp32RecordsRepository = esp32RecordsRepository;
    }

    public List<ESP32Records> getRecords() {
        return esp32RecordsRepository.findAll();
    }

    public ESP32Records createRecord(ESP32Records record) {
        return esp32RecordsRepository.save(record);
    }

    public void deleteFirstRecord() {
        List<ESP32Records> allRecords = esp32RecordsRepository.findAll();
        if (!allRecords.isEmpty()) {
            if(allRecords.size()>=10)
            {
                ESP32Records firstRecord = allRecords.get(0);
                esp32RecordsRepository.deleteById(firstRecord.getId());
            }
        }
    }

    public void deleteRecord(Long id) {
        esp32RecordsRepository.deleteById(id);
    }

}