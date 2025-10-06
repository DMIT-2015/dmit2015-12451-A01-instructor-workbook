package dmit2015.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class WeatherForecast {

    @Id
    @Column(name = "weatherforecastid", nullable = false,  unique = true)
    private String id;
    private String city;
    private LocalDate date =  LocalDate.now().plusDays(1);
    private int temperatureCelsius;

    public int getTemperatureFahrenheit() {
        return (int) (32 + temperatureCelsius / 0.5556);
    }

    // copy constructor
    public WeatherForecast(WeatherForecast other) {
        setId(other.getId());
        setCity(other.getCity());
        setDate(other.getDate());
        setTemperatureCelsius(other.getTemperatureCelsius());
    }

    public static WeatherForecast copyOf(WeatherForecast other) {
        return new WeatherForecast(other);
    }

    // create a new WeatherForecast using fake data
    public static WeatherForecast of(Faker faker) {
        var newWeatherForecast = new WeatherForecast();
        newWeatherForecast.setId(UUID.randomUUID().toString());
        newWeatherForecast.setDate( LocalDate.now().plusDays(faker.number().numberBetween(1, 7)));
        newWeatherForecast.setCity(faker.address().city());
        newWeatherForecast.setTemperatureCelsius(faker.number().numberBetween(-35, 35));
        return newWeatherForecast;
    }

}
