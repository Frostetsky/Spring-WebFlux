package ru.job4j.weather_reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import ru.job4j.weather_reactive.entity.Weather;
import ru.job4j.weather_reactive.service.WeatherService;

import java.time.Duration;

@RestController
public class WeatherController {

    @Autowired
    private final WeatherService weathers;

    public WeatherController(WeatherService weathers) {
        this.weathers = weathers;
    }

    @GetMapping(value = "/allWeathers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Weather> findAll() {
        Flux<Weather> data = weathers.findAll();
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }

    @GetMapping(value = "/getWeatherCity/{id}")
    public Mono<Weather> findById(@PathVariable Integer id) {
        return weathers.findById(id);
    }

    @GetMapping(value = "/hottest")
    public Mono<Weather> findCityWithMaxTemperature() {
        return weathers.findCityWithMaxTemperature();
    }

    @GetMapping(value = "cityGreatThen/{temperature}")
    public Flux<Weather> findCityByTemperatureCriteria(@PathVariable Integer temperature) {
        Flux<Weather> data =  weathers.findCityByTemperatureCriteria(temperature);
        Flux<Long> delay = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(data, delay).map(Tuple2::getT1);
    }
}
