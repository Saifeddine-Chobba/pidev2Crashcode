package tn.esprit.pidevcrashcode.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Complaint;
import tn.esprit.pidevcrashcode.Entities.TypeComplaint;

import java.util.List;

public interface IComplaintService {
    List<Complaint> retrieveAllComplaint();

    Complaint addComplaint(Complaint cc);

    void deleteComplaint(Integer id);

    Complaint updateComplaint(Complaint cc);

    Complaint retrieveComplaint(Integer id);

    int MostClaimedService(String service);

    void AddComplaintAndAssignToUser(Complaint  complaint,int idUser);

    List<String> getSentiment (String text);

    public String getOverallSentiment(Complaint idComplaint);
    public String getSectorSentiment(TypeComplaint sector);

    public List<List<String>> getSectorsWithSentiments();

}
