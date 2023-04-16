package tn.esprit.pidevcrashcode.Services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Repositories.CampingCenterRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class CampingCenterService implements I_CampingCenterService{

    @Autowired
    CampingCenterRepository CCRepo;

    public List<CampingCenter> findAllCampingCenters(){
        return CCRepo.findAll();
    }

    public CampingCenter AddCampingCenter(CampingCenter CC){
        CCRepo.save(CC);
        return CC;
    }

    public String DeleteCampingCenter(CampingCenter CC){
        CCRepo.delete(CC);
        return "Delete Done !";
    }

    public CampingCenter findCenterById(int id){
        CampingCenter cc = CCRepo.findById(id).get();
        return cc;
    }

    public void deleteCampingCenterById(int id) {
        CCRepo.deleteById(id);
    }

    public boolean pdfContainsAnyWords(MultipartFile pdfFile, List<String> words) {
        boolean containsAny = false;
        try {
            PDDocument document = PDDocument.load(pdfFile.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            for (String word : words) {
                if (text.contains(word)) {
                    containsAny = true;
                    break;
                }
            }
            document.close();
            return containsAny;
        }
        catch (IOException e){
            System.err.println("not approprieted document !");
        }
        return containsAny;

    }


}
