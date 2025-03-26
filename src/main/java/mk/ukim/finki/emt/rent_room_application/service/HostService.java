package mk.ukim.finki.emt.rent_room_application.service;

import mk.ukim.finki.emt.rent_room_application.model.Host;
import mk.ukim.finki.emt.rent_room_application.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(Host host);

    Optional<Host> update(Long id, Host host);

    void deleteById(Long id);
}
