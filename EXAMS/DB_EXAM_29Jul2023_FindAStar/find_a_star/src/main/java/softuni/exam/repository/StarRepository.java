package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByName(String name);

    List<Star> findAllByIdNotInAndStarTypeOrderByLightYearsAsc(Set<Long> allObservedStarsIds, StarType starType);


    //   List<Star> findAllByStarType_In(StarType starType, Set<Star> setOfNamesOfAllStarsBeingObserved);
}
