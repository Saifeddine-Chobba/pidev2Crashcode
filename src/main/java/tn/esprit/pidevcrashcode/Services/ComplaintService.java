package tn.esprit.pidevcrashcode.Services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevcrashcode.Entities.Complaint;
import tn.esprit.pidevcrashcode.Repositories.ComplaintRepository;

import java.util.List;

@Service
@Slf4j
public class ComplaintService  implements IComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;
    @Override
    public List<Complaint> retrieveAllComplaint() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint addComplaint(Complaint cc) {
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
}
