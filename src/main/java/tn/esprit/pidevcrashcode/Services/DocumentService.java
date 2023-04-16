package tn.esprit.pidevcrashcode.Services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pidevcrashcode.Entities.CampingCenter;
import tn.esprit.pidevcrashcode.Entities.Document;
import tn.esprit.pidevcrashcode.Repositories.DocumentRepository;

import java.io.IOException;
import java.util.List;
@Service
public class DocumentService {

    @Autowired
    DocumentRepository Dr;
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

    public String pdfContainsAnyWord(MultipartFile pdfFile, List<String> words) {
        boolean containsAny = false;
        try {
            PDDocument document = PDDocument.load(pdfFile.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println(text);

        }
        catch (IOException e){
            System.err.println("not approprieted document !");
        }
        return "containsAny";

    }

    public Document AddCampingCenter(Document CC){
        Dr.save(CC);
        return CC;
    }

}
