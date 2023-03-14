package tn.esprit.pidevcrashcode.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Location;
import tn.esprit.pidevcrashcode.Entities.Ord;
import tn.esprit.pidevcrashcode.Services.ILocationService;
import tn.esprit.pidevcrashcode.Services.LocationService;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    String apiKey = "AzhrmszmMRVqjdEvr043KGbFiuK4n285";
    @Autowired
    private LocationService locationService;


    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody Location location) {
        Optional<Location> locationToUpdate = locationService.findLocationById(id);
        if (locationToUpdate.isPresent()) {
            location.setIdLocation(id);
            locationService.updateLocation(locationToUpdate.get(), location);
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/route")
    public ResponseEntity<?> getBestRoute() {
// Set up the request body
        List<Map<String, Object>> waypoints = new ArrayList<>();

        float[] longi ={10.395396f, 10.401396f, 10.405032f, 10.395032f, 10.396921f};
        float[] latt = {35.79113f, 35.7923f, 35.79245f, 35.793144f, 35.803677f};
        List<Float> longList = new ArrayList<Float>();
        for (float f : longi) {
            longList.add(f);
        }
        List<Float> lattList = new ArrayList<Float>();
        for (float f : latt) {
            lattList.add(f);
        }
        for(int i = 0; i < 5; i++){
            Map<String,Float> coords = new HashMap<>();
            coords.put("longitude",longList.get(i));
            coords.put("latitude",lattList.get(i));
            Map<String,Object> waypoint = new HashMap<>();
            waypoint.put("point", coords);
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
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestObject, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://api.tomtom.com/routing/waypointoptimization/1?key=AzhrmszmMRVqjdEvr043KGbFiuK4n285", requestEntity, String.class);
        String responseBody = responseEntity.getBody();
// Parse the response body as needed
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = null;
        try {
            response = objectMapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return  ResponseEntity.ok(response);

    }

}
