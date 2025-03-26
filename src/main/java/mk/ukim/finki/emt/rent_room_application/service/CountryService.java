package mk.ukim.finki.emt.rent_room_application.service;

import mk.ukim.finki.emt.rent_room_application.model.Country;
import mk.ukim.finki.emt.rent_room_application.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(Country country);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);
}
