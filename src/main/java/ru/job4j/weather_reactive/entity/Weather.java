package ru.job4j.weather_reactive.entity;

import java.util.Objects;

public class Weather {
    private Integer id;

    private String city;

    private Integer temperature;

    public Weather() {
    }

    public Weather(String city, Integer temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public Weather(Integer id, String city, Integer temperature) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(id, weather.id)
                && Objects.equals(city, weather.city)
                && Objects.equals(temperature, weather.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, temperature);
    }
}
