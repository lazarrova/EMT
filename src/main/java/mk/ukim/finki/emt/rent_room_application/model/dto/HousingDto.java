package mk.ukim.finki.emt.rent_room_application.model.dto;

import mk.ukim.finki.emt.rent_room_application.model.enumerations.Category;

public class HousingDto {

    private String name;

    private Category category;

    private Long host;

    private Integer numRooms;

    private boolean isRented;


    public HousingDto(String name, Category category, Long host, Integer numRooms, boolean isRented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = isRented;
    }

    public boolean isRented() {
        return isRented;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getHost() {
        return host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }
}
