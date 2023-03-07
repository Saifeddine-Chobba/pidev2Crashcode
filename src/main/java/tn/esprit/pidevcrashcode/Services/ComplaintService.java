package tn.esprit.pidevcrashcode.Services;


import edu.stanford.nlp.ling.CoreAnnotations;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Complaint;
import tn.esprit.pidevcrashcode.Entities.Status;
import tn.esprit.pidevcrashcode.Entities.TypeComplaint;
import tn.esprit.pidevcrashcode.Entities.User;
import tn.esprit.pidevcrashcode.Repositories.ComplaintRepository;
import tn.esprit.pidevcrashcode.Repositories.UserRepository;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;

import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class ComplaintService  implements IComplaintService {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ComplaintRepository complaintRepository;
    @Override
    public List<Complaint> retrieveAllComplaint() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint addComplaint(Complaint cc) {
      //  userService.getCurrentUser().getUsername();
        return  complaintRepository.save(cc);
    }

    @Override
    public void deleteComplaint(Integer id) {
        complaintRepository.deleteById(id);
    }

    @Override
    public Complaint updateComplaint(Complaint cc) {
        return complaintRepository.save(cc);
    }

    @Override
    public Complaint retrieveComplaint(Integer id) {
        return complaintRepository.findById(id).get();
    }

    @Override
    public int MostClaimedService(String service) {
        int numberclaims=0;
        List<Complaint> complaints=complaintRepository.findAll();
        for (Complaint c: complaints){
            if (c.getTypeComplaint().toString().equals(service)){
                numberclaims++;
            }
        }
        return numberclaims;

    }

    public void AddComplaintAndAssignToUser(Complaint  complaint,int idUser){
        User user=userRepository.findById(idUser).get();
        complaint.setUsername(user.getUsername());
        complaintRepository.save(complaint);

   }

    @Override
    public List<String> getSentiment(String text) {
        // Initialize the pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // Create an Annotation object
        List<String> sentiments = new ArrayList<>();
        CoreDocument coreDocument = new CoreDocument(text);
        pipeline.annotate(coreDocument);
        for (CoreSentence sentence : coreDocument.sentences()){
            String sentiment = sentence.sentiment();
            sentiments.add(sentiment);
        }
        return sentiments;
    }

    @Override
    public String getOverallSentiment(Complaint complaint) {
            List<String> sentiments = getSentiment(complaint.getDescription());
            int negative = Collections.frequency(sentiments,"Negative");
            int neutral = Collections.frequency(sentiments,"Neutral");
            int positive = Collections.frequency(sentiments,"Positive");
        if (sentiments.size() == 0) {return "Neutral";}

        float sentimentScore = (negative*(-1) + positive*1)/sentiments.size();
            int finalScore = Math.round(sentimentScore);
            if (finalScore == 0){
                return "Neutral";
            } else if (finalScore == -1) {
                return "Negative";
            }
            else if (finalScore == 1){
                return "Positive";
            }
            else {
                return "Bad Logic: check code logic";
            }

    }
    @Override
    public String getSectorSentiment(TypeComplaint sector){
        List<Complaint> sectorCompalaints = complaintRepository.findByTypeComplaint(sector);
        List<String> sentiments = new ArrayList<>();
        for (Complaint complaint : sectorCompalaints){
            sentiments.add(complaint.getSentiment());
        }

        int negative = Collections.frequency(sentiments,"Negative");
        int positive = Collections.frequency(sentiments,"Positive");
        if (sentiments.size() == 0) {return "Neutral";}

        float sentimentScore =(float) ((negative*(-1) + positive));


        if (sentimentScore == 0){
            return "Neutral";
        }
        else if (sentimentScore  <0 ) {
            return "Negative";
        }
        else if (sentimentScore >0){
            return "Positive";
        }
        else {
            return "Bad Logic: check code logic";
        }
    }
    @Override
    public List<List<String>> getSectorsWithSentiments(){
        List<List<String>> sectorsAndSentiments = new ArrayList<>();

        for (TypeComplaint sector : TypeComplaint.values()){
            sectorsAndSentiments.add(Arrays.asList(sector.name(),getSectorSentiment(sector)));
        }
        return sectorsAndSentiments;
    }

    @Override
    public void updateStatus(Complaint complaint, Status status) {
        complaint.setStatus(status);
        addComplaint(complaint);
    }

    @Override
    public Complaint respondToComplaint(Complaint complaint, String response) {
        complaint.setResponse(response);
        updateStatus(complaint,Status.Resolved);
        addComplaint(complaint);
        return complaint;
    }

}
