package tn.esprit.pidevcrashcode.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevcrashcode.Entities.Complaint;
import tn.esprit.pidevcrashcode.Services.IComplaintService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/complaint")
public class ComplaintControleur {
IComplaintService iComplaintService;
    @GetMapping("/retriveAllComplaint")
    public List<Complaint> getComplaint(){
        List<Complaint> lc=iComplaintService.retrieveAllComplaint();
        return lc;
    }
    @GetMapping("/retriveComplaint/{complaintId}")
    public Complaint retriveComplaint(@PathVariable("complaintId")Integer complaintId){
        return iComplaintService.retrieveComplaint(complaintId);
    }
    @PostMapping("/addComplaint")
    public Complaint addComplaint(@RequestBody  Complaint c ){
        Complaint complaint= iComplaintService.addComplaint(c);
        return complaint ;
    }
    @PutMapping("/updateComplaint")
    public Complaint updateComplaint(@RequestBody Complaint c){
        Complaint complaint= iComplaintService.updateComplaint(c);
        return complaint;
    }
    @DeleteMapping("/delateComplaint/{complaintid}")
    public void  delateComplaint (@PathVariable ("complaintid") Integer complaintid) {
        iComplaintService.deleteComplaint(complaintid);
    }
}
