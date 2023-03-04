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
    public Complaint addComplaint( @RequestBody  Complaint c ){
        String sentiment = iComplaintService.getOverallSentiment(c);
        c.setSentiment(sentiment);
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

    @PostMapping("/add-assignComplaint/{id-user}")
    public void addAndAssignComplaint(@RequestBody  Complaint c ,@PathVariable("id-user")int idUser){
    iComplaintService.AddComplaintAndAssignToUser(c,idUser);
    }
    @GetMapping("/MostClaimedService/{TypeComplain}")
    public int MostClaimedService(@PathVariable("TypeComplain")String service){
        return iComplaintService.MostClaimedService(service);
    }
    @GetMapping("/getSentiment")
    public List<String> getSentiment (@RequestBody String text){
        return iComplaintService.getSentiment(text);
    }
    @GetMapping("/getSectorsWithSentiments")
    public List<List<String>> getSectorsWithSentiments(){
        return iComplaintService.getSectorsWithSentiments();
    }




}
