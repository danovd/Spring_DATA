package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

  //  Optional<Offer> findByPublishedOn(String publishedOn);

    List<Offer> findAllByApartmentApartmentTypeOrderByApartmentAreaDescPriceAsc(ApartmentType apartmentType);


    Optional<Offer> findByPublishedOnAndPriceAndAgentFirstNameAndApartmentId(String publishedOn, BigDecimal price, String name, Long id);
}
