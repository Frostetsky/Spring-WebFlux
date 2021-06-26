package ru.job4j.weatherreactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.job4j.weatherreactive.entity.Weather;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final Map<Integer, Weather> weathers = new ConcurrentHashMap<>();
    {
        weathers.put(1, new Weather(1, "Moscow", 34));
        weathers.put(2, new Weather(2, "St. Petersburg", 31));
        weathers.put(3, new Weather(3, "Saratov", 30));
        weathers.put(4, new Weather(4, "Smolensk", 31));
        weathers.put(5, new Weather(5, "Kiev", 28));
        weathers.put(6, new Weather(6, "Minsk", 26));
    }

    public Mono<Weather> findById(Integer id) {
        return Mono.justOrEmpty(weathers.get(id));
    }

    public Flux<Weather> findAll() {
        return Flux.fromIterable(weathers.values());
    }

    public Mono<Weather> findCityWithMaxTemperature() {
        Weather weather = Collections.max(weathers.values(), Comparator.comparing(Weather::getTemperature));
        return Mono.justOrEmpty(weather);
    }

    public Flux<Weather> findCityByTemperatureCriteria(Integer temperature) {
        return Flux.fromIterable(weathers.values()
                .stream()
                .filter(city -> city.getTemperature() > temperature)
                .collect(Collectors.toList()));
    }
}
