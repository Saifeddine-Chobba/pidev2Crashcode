package tn.esprit.pidevcrashcode.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hibernate.type.MapType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.pidevcrashcode.Entities.Deposit;
import tn.esprit.pidevcrashcode.Entities.Location;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Repositories.LocationRepository;

import java.util.*;

@Service
public class LocationService implements ILocationService {

    String apiKey = "AzhrmszmMRVqjdEvr043KGbFiuK4n285";
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

    @Override
    public void updateLocation(Location locationOld, Location locationNew) {
        locationNew.setIdLocation(locationOld.getIdLocation());
        locationRepository.save(locationNew);
    }

    @Override
    public Optional<Location> findLocationById(int id) {
        return locationRepository.findById(id);
    }

    @Override
    public Optional<Location> findLocationByCoordinates(float longitude, float latitude) {
        return locationRepository.findByLongitudeAndLattitude(longitude, latitude);
    }

    @Override
    public Optional<List<Location>> findLocationsByState(String state) {
        return locationRepository.findAllByState(state);
    }

    @Override
    public Optional<List<Location>> findLocationsByTown(String town) {
        return locationRepository.findAllByTown(town);
    }

    @Override
    public Optional<Location> findLocationByName(String name) {
        return locationRepository.findByName(name);
    }

    @Override
    public JsonNode getBestRoute(List<Ord> dailyMissions, Deposit deposit) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

// Set up the request body
        List<Map<String, Object>> waypoints = new ArrayList<>();

// Add the starting point (deposit) as the first waypoint
        Map<String, Object> startPoint = new HashMap<>();
        Map<String, Float> startPointCoords = new HashMap<>();
        startPointCoords.put("latitude", deposit.getLocation().getLattitude());
        startPointCoords.put("longitude", deposit.getLocation().getLongitude());
        startPoint.put("point", startPointCoords);
        startPoint.put("serviceTimeInSeconds", 0); // No service time at the starting point
        waypoints.add(startPoint);

// Add the delivery locations as waypoints
        for (Ord mission : dailyMissions) {
            Location location = mission.getDeliveryLocation();
            Map<String, Object> waypoint = new HashMap<>();
            Map<String, Float> waypointCoords = new HashMap<>();
            waypointCoords.put("latitude", location.getLattitude());
            waypointCoords.put("longitude", location.getLongitude());
            waypoint.put("point", waypointCoords);
            waypoint.put("serviceTimeInSeconds", 600 ); // Service time at the delivery location
            waypoints.add(waypoint);
        }

        Map<String, Object> options = new HashMap<>();
        options.put("travelMode", "truck"); // Use truck as the travel mode
        options.put("vehicleMaxSpeed", 70); // Set max speed to 90 km/h
        options.put("vehicleWeight", 3000); // Set max weight to 3000 kg
        options.put("vehicleLength", 4.5); // Set vehicle length to 4.5 meters
        options.put("vehicleWidth", 1.8); // Set vehicle width to 1.8 meters
        options.put("vehicleHeight", 2.1); // Set vehicle height to 2.1 meters
        options.put("vehicleCommercial", true); // Set vehicle as commercial
        options.put("outputExtensions", Arrays.asList("travelTimes", "routeLengths")); // Include travel times and route lengths in response
        options.put("traffic", "historical"); // Use historical traffic data
        options.put("departAt", "now"); // Departure time is now
        //optimize time
        Map<String,Object> constraints = new HashMap<>();
        constraints.put("destinationIndex",-1);
        options.put("waypointConstraints",constraints);

// Set up the request object
        Map<String, Object> requestObject = new HashMap<>();
        requestObject.put("waypoints", waypoints);
        requestObject.put("options", options);

// Make the API call and parse the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestObject, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://api.tomtom.com/routing/waypointoptimization/1?key=AzhrmszmMRVqjdEvr043KGbFiuK4n285", requestEntity, String.class);
        String responseBody = responseEntity.getBody();
        Thread.sleep(1000);

// Parse the response body as needed
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = null;
        try {
            response = objectMapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    public double distanceBetween(Location l1, Location l2) {
        final double R = 6371; // Radius of the earth
        //extrat coordibnates
        double lat1 = l1.getLattitude();
        double lon1 = l1.getLongitude();
        double lat2 = l2.getLattitude();
        double lon2 = l2.getLongitude();
        //calculate distance
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return   Math.abs(R * c * 1000); // convert to meters
    }

    @Override
    public List<Location> getAllByLongitude(float longitude) {
        return locationRepository.findAllByLongitude(longitude);
    }

    @Override
    public Location reverseGeoccode(float longitude, float latitude) {

        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "https://api.tomtom.com/search/2/reverseGeocode/";
        String position = latitude+","+longitude;
        String ext = "json";
        String apiKey = this.apiKey;
        String radius = "1000";
        String url = baseUrl + position + "." + ext + "?key=" + apiKey + "&" + "radius=" + radius + "&language=en-US";
        //send request and get response
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        //check if response is valid
        if (responseEntity.getStatusCode().isError()){
            return null;
        }
        //get response body
        String response = responseEntity.getBody();
        if (response == null){
            return  null;
        }
        // Parse the response JSON into a JsonObject
        com.google.gson.JsonObject responseObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject address = responseObject.getAsJsonArray("addresses").get(0).getAsJsonObject().getAsJsonObject("address");
        //fetch data
        String state = address.get("countrySubdivision").getAsString();
        String town = address.get("municipality").getAsString();
        //create location
        Location location = new Location();
        location.setState(state);
        location.setTown(town);
        location.setLongitude(longitude);
        location.setLattitude(latitude);

        return location;
    }
}
