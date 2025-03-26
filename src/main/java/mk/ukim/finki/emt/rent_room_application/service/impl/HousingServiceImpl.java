package mk.ukim.finki.emt.rent_room_application.service.impl;

import mk.ukim.finki.emt.rent_room_application.model.Housing;
import mk.ukim.finki.emt.rent_room_application.model.dto.HousingDto;
import mk.ukim.finki.emt.rent_room_application.repository.HousingRepository;
import mk.ukim.finki.emt.rent_room_application.service.HostService;
import mk.ukim.finki.emt.rent_room_application.service.HousingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousingServiceImpl implements HousingService {

    private final HousingRepository housingRepository;
    private final HostService hostService;

    public HousingServiceImpl(HousingRepository housingRepository, HostService hostService) {
        this.housingRepository = housingRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Housing> findAll() {
        return this.housingRepository.findAll();

    }

    @Override
    public Optional<Housing> findById(Long id) {
        return this.housingRepository.findById(id);
    }

    @Override
    public Optional<Housing> save(HousingDto housing) {
        if(housing.getCategory() != null && hostService.findById(housing.getHost()).isPresent()){
            return Optional.of(
                    housingRepository.save(new Housing(housing.getName(), housing.getCategory(),
                            hostService.findById(housing.getHost()).get(), housing.getNumRooms()))
            );
        }
        return Optional.empty();
    }



    @Override
    public Optional<Housing> update(Long id, HousingDto housing) {
        return this.housingRepository.findById(id)
                .map(existingAccommodation -> {
                    if(housing.getName() != null){
                        existingAccommodation.setName(housing.getName());
                    }
                    if (housing.getCategory()!= null){
                        existingAccommodation.setCategory(housing.getCategory());
                    }
                    if(housing.getHost()!=null && hostService.findById(housing.getHost()).isPresent()){
                        existingAccommodation.setHost(hostService.findById(housing.getHost()).get());
                    }
                    if(housing.getNumRooms() != null){
                        existingAccommodation.setNumRooms(housing.getNumRooms());
                    }
                    return housingRepository.save(existingAccommodation);
                });
    }

    @Override
    public void deleteById(Long id) {
        this.housingRepository.deleteById(id);
    }

    @Override
    public Optional<Housing> markAsRented(Long id) {
        return housingRepository.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return housingRepository.save(accommodation);
        });    }
}
