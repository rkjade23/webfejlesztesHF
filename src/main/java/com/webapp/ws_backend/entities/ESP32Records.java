package com.webapp.ws_backend.entities;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "esp32_weather_records")
public class ESP32Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wind_speed", nullable = false, length = 3)
    private int windSpeed;

    @Column(name = "wind_direction", nullable = false, length = 3)
    private int windDirection;

    @Column(name = "temperature", nullable = false)
    private float temperature;

    @Column(name = "pressure", nullable = false)
    private float pressure;

    @Column(name = "humidity", nullable = false)
    private float humidity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", updatable = false, nullable = false)
    private Instant createdAt;

    public ESP32Records() {
    }

    public ESP32Records(int windSpeed, int windDirection, float temperature, float pressure, float humidity) {
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    } 
}