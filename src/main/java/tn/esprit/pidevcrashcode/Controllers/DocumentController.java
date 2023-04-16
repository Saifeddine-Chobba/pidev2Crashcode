package tn.esprit.pidevcrashcode.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Document;
import tn.esprit.pidevcrashcode.Services.DocumentService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class DocumentController {

    @Autowired
    DocumentService Ds;
    @PostMapping("/adddocument")
    public String addCampingCenter(@RequestParam("file")MultipartFile file)throws IOException {
        List<String> words = Arrays.asList("Top","khlil");
      //  Ds.pdfContainsAnyWords(file,words);
          if(Ds.pdfContainsAnyWords(file,words)) {
             Ds.pdfContainsAnyWord(file,words);
               System.out.println("jawek behi");
        }
         else
              System.err.println("jawekmahouch behi");
        return "Successfully file uploaded";
    }
}
