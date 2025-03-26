package mk.ukim.finki.emt.rent_room_application.service;

import mk.ukim.finki.emt.rent_room_application.model.Housing;
import mk.ukim.finki.emt.rent_room_application.model.dto.HousingDto;
import mk.ukim.finki.emt.rent_room_application.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface HousingService {
    List<Housing> findAll();


    Optional<Housing> findById(Long id);

    Optional<Housing> save(HousingDto housing);

    Optional<Housing> update(Long id, HousingDto housing);

    void deleteById(Long id);

    Optional<Housing> markAsRented(Long id);


}
