package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DaysOfWeek;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Set<Forecast> findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DaysOfWeek dayOfWeek, Integer city_population);

    Optional<Forecast> findAllByCityAndDayOfWeek(City city, DaysOfWeek dayOfWeek);
}
