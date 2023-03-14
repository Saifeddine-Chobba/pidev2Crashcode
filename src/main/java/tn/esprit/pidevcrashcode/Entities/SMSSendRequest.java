package tn.esprit.pidevcrashcode.Entities;
import lombok.Data;

@Data
public class SMSSendRequest {

    private String destinationSMSNumber;
    private String smsMessages;
}
