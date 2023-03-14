package tn.esprit.pidevcrashcode.Controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/chatbot")
public class ChatbotController {
//    @Autowired
//    IChatbotService chatbotService;

//    @SneakyThrows
//    @GetMapping("/test")
//    public ResponseEntity<String> getResponse(){
//        String input = "see order";
//        SavedModelBundle model = SavedModelBundle.load("C:\\Users\\saif\\Desktop\\my_chatbot_model.h5", "serve");
//
//        Session.Runner runner = model.session().runner();
//        Tensor inputTensor = Tensor.create(input.getBytes("UTF-8"));
//        runner.feed("input_node", inputTensor);
//        runner.fetch("output_node");
//        List<Tensor> output = runner.run();
//
//
//
//    }

//    @GetMapping("/test2")
//    public String predictActivity() throws Exception {
//
//    }
}
