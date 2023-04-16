package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Cart;
import tn.esprit.pidevcrashcode.Entities.Rating;
import tn.esprit.pidevcrashcode.Services.CampingCenterService;
import tn.esprit.pidevcrashcode.Services.RatingService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController

public class CampingCenterController {

    @Autowired
    private CampingCenterService CCService;

    @Autowired
    RatingService ratingService;

    @GetMapping("/agent/campingcenters")
    public List<CampingCenter> getAllCampingCenters() {
        return CCService.findAllCampingCenters();
    }

    @GetMapping("/agent/{id}")
    public ResponseEntity<CampingCenter> getCampingCenterById(@PathVariable int id) {
        CampingCenter campingCenter = CCService.findCenterById(id);
        if (campingCenter!=null) {
            return new ResponseEntity<>(campingCenter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
    @PostMapping("/campingcenterOwner/add")
    public ResponseEntity<CampingCenter> addCampingCenter(@RequestParam("file")MultipartFile file, @RequestBody CampingCenter cc)throws IOException {
        List<String> words = Arrays.asList("Top","example2");
        CCService.pdfContainsAnyWords(file,words);
        if(CCService.pdfContainsAnyWords(file,words)) {
            cc.setFile(file.getBytes());
            CCService.AddCampingCenter(cc);
        }
        else
            System.err.println("erooooooor");
        return new ResponseEntity<>(cc, HttpStatus.CREATED);
    }*/

    @PostMapping("/campingcenterOwner/add")
    public ResponseEntity<CampingCenter> addCampingCenter( @RequestParam("file")MultipartFile file,
                                                           @RequestParam("files") MultipartFile[] pictures,
                                                           @RequestParam String name,
                                                           @RequestParam int capacity,
                                                           @RequestParam float price,
                                                           @RequestParam float discountPercent)throws IOException {
        List<String> words = Arrays.asList("Top","Service");

        List<String> pictureNames=new ArrayList<>();
        CampingCenter cc = new CampingCenter();
        File uploadDirectory = new File("src/main/resources/static/");
        if(!uploadDirectory.exists()){
            uploadDirectory.mkdirs();
        }
       // CCService.pdfContainsAnyWords(file,words);
        if(CCService.pdfContainsAnyWords(file,words)) {
            for (MultipartFile picture:pictures){
             String pictureName= picture.getOriginalFilename();

             File dest = new File("C:/Users/Hannachi/Desktop/Git/pidev2Crashcode/src/main/resources/uploads/"+pictureName);


             picture.transferTo(dest);

             //Add the file name to the list
                pictureNames.add(pictureName);
            }
            cc.setFile(file.getBytes());
            cc.setPictures(pictureNames);
            cc.setName(name);
            cc.setPrice(price);
            cc.setCapacity(capacity);
            cc.setAvailableSpots(capacity);
          //  ratingService.updateCampsiteRate(cc.getIdCampingCenter());
            cc.setDiscountPercent(discountPercent);
            CCService.AddCampingCenter(cc);


        }
        else
            System.err.println("it seems that you upload invalid ownership certificate !");
        return new ResponseEntity<>(cc, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<CampingCenter> updateCampinCenter(@PathVariable int id, @RequestBody CampingCenter cc) {
        CampingCenter campingCenter = CCService.findCenterById(id);
        if (campingCenter!=null) {

           CCService.AddCampingCenter(campingCenter);
            return new ResponseEntity<>(campingCenter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("client/{id}")
    public String deleteCampCenterById(@PathVariable int id) {

            CCService.deleteCampingCenterById(id);
            return "Campsite Deleted successfully";
        }
    }



