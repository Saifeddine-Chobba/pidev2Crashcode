package tn.esprit.pidevcrashcode.Services;

import com.fasterxml.jackson.databind.JsonNode;
import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Entities.Location;
import tn.esprit.pidevcrashcode.Entities.Ord;

import java.util.List;
import java.util.Optional;

public interface ILocationService {

    void saveLocation(Location location);

    void deleteLocation(Location location);

    void updateLocation(Location locationOld , Location locationNew);

    Optional<Location> findLocationById(int id);

    Optional<Location> findLocationByCoordinates(float longitude, float lattitude);

    Optional<List<Location>> findLocationsByState(String state);

    Optional<List<Location>> findLocationsByTown(String town);

    Optional<Location> findLocationByName(String name);

    JsonNode getBestRoute(List<Ord> dailyMissions, Deposit deposit) throws InterruptedException;

    double distanceBetween(Location l1 , Location l2);

    List<Location> getAllByLongitude(float longitude);

    Location reverseGeoccode(float longitude , float latitude);


}
