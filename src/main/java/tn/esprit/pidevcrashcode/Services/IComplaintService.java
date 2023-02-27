package tn.esprit.pidevcrashcode.Services;


import tn.esprit.pidevcrashcode.Entities.Complaint;

import java.util.List;

public interface IComplaintService {
    List<Complaint> retrieveAllComplaint();

    Complaint addComplaint(Complaint cc);

    void deleteComplaint(Integer id);

    Complaint updateComplaint(Complaint cc);

    Complaint retrieveComplaint(Integer id);
}
