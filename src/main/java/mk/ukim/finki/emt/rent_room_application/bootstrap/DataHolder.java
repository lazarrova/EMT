package mk.ukim.finki.emt.rent_room_application.bootstrap;

import jakarta.annotation.PostConstruct;

import mk.ukim.finki.emt.rent_room_application.model.Country;
import mk.ukim.finki.emt.rent_room_application.model.Host;
import mk.ukim.finki.emt.rent_room_application.model.Housing;
import mk.ukim.finki.emt.rent_room_application.model.enumerations.Category;
import mk.ukim.finki.emt.rent_room_application.repository.CountryRepository;
import mk.ukim.finki.emt.rent_room_application.repository.HostRepository;
import mk.ukim.finki.emt.rent_room_application.repository.HousingRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    // Lists
    public static List<Country> countries = null;
    public static List<Host> hosts = null;
    public static List<Housing> housings = null;

    // Repositories
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final HousingRepository housingRepository;

    public DataHolder(CountryRepository countryRepository, HostRepository hostRepository, HousingRepository housingRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.housingRepository = housingRepository;
    }

    @PostConstruct
    public void init() {
        // Countries
        countries = new ArrayList<>();
        if (this.countryRepository.count() == 0) {
            countries.add(new Country("Canada", "North America"));
            countries.add(new Country("Serbia", "Europe"));
            countries.add(new Country("Macedonia", "Europe"));
            countries.add(new Country("China", "Asia"));
            countries.add(new Country("Argentina", "South America"));

            this.countryRepository.saveAll(countries);
        }

        // Hosts
        hosts = new ArrayList<>();
        if (this.hostRepository.count() == 0) {
            hosts.add(new Host("Serena", "Williams", countries.get(0)));  // USA
            hosts.add(new Host("Angelique", "Kerber", countries.get(1)));  // Germany
            hosts.add(new Host("Amélie", "Mauresmo", countries.get(2)));  // France
            hosts.add(new Host("Naomi", "Osaka", countries.get(3)));  // Japan
            hosts.add(new Host("Maria", "Bueno", countries.get(4))); // Brazil

            this.hostRepository.saveAll(hosts);
        }

        // Housings
        housings = new ArrayList<>();
        if (this.housingRepository.count() == 0) {
            housings.add(new Housing("Hotel Splendid", Category.HOTEL, hosts.get(0), 100)); // Montenegro
            housings.add(new Housing("Esplanade Zagreb", Category.APARTMENT, hosts.get(1), 3)); // Croatia
            housings.add(new Housing("Metropol Palace", Category.MOTEL, hosts.get(2), 20)); // Serbia
            housings.add(new Housing("Hotel Adriatic", Category.HOUSE, hosts.get(3), 15)); // Slovenia
            housings.add(new Housing("Hotel Aleksandar", Category.FLAT, hosts.get(4), 5)); // North Macedonia
            this.housingRepository.saveAll(housings);
        }
    }
}
