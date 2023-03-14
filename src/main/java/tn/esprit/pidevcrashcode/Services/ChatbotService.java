package tn.esprit.pidevcrashcode.Services;

import com.google.gson.JsonObject;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class ChatbotService
//        implements IChatbotService
{
//    private final StanfordCoreNLP pipeline;
//
//    public ChatbotService() {
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
//        this.pipeline = new StanfordCoreNLP(props);
//    }
//
//    @Override
//    public String processPrompt(String prompt) {
//        // Tokenize the input sentence
//        List<CoreLabel> tokens = tokenize(prompt);
//        // Use POS tagging to label each word in the sentence
//        List<CoreLabel> taggedTokens = tagTokens(tokens);
//        // Use NER to identify named entities in the sentence
//        SemanticGraph dependencies = extractDependencies(taggedTokens);
//        // Use the identified named entities and POS tags to determine the user's intent
//        String intent = determineIntent(dependencies);
//        // Call the appropriate backend method based on the user's intent and return the results
//        String result = callBackendMethod(intent);
//        return result;
//    }
//
//    @Override
//    public List<CoreLabel> tokenize(String prompt) {
//        // Create StanfordCoreNLP object with tokenize annotator
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//        // Set up tokenizer factory
//        TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.PTBTokenizerFactory.newCoreLabelTokenizerFactory("");
//
//        // Tokenize input text
//        List<CoreLabel> tokens = new ArrayList<>();
//        Annotation document = new Annotation(prompt);
//        pipeline.annotate(document);
//        List<CoreLabel> coreLabels = document.get(CoreAnnotations.TokensAnnotation.class);
//        for (CoreLabel coreLabel : coreLabels) {
//            tokens.add(coreLabel);
//        }
//
//        return tokens;
//    }
//
//    @Override
//    public List<CoreLabel> tagTokens(List<CoreLabel> tokens) {
//        // Create annotation object from input tokens
//        Annotation document = new Annotation("");
//        document.set(CoreAnnotations.TokensAnnotation.class, tokens);
//        // Annotate document
//        pipeline.annotate(document);
//        // Return annotated tokens
//        return document.get(CoreAnnotations.TokensAnnotation.class);
//    }
//
//
//    @Override
//    public SemanticGraph extractDependencies(List<CoreLabel> taggedTokens) {
//        // Create sentence annotation from tokens
//        Annotation sentenceAnnotation = new Annotation("");
//        sentenceAnnotation.set(CoreAnnotations.TokensAnnotation.class, taggedTokens);
//        pipeline.annotate(sentenceAnnotation);
//
//        // Get the semantic graph from the sentence annotation
//        CoreMap sentence = sentenceAnnotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
//        SemanticGraph semanticGraph = sentence.get(SemanticGraphCoreAnnotations.EnhancedPlusPlusDependenciesAnnotation.class);
//
//        return semanticGraph;
//    }
//
//    @Override
//    public String determineIntent(SemanticGraph dependencies) {
//        return null;
//    }
//
//    @Override
//    public String callBackendMethod(String intent) {
//        return null;
//    }
//
//    @Override
//    public String respondWithText(String intent) {
//        return null;
//    }
//
//    @Override
//    public String formatInput(String prompt) {
//        return null;
//    }

//    @Override
//    public String formatInput(String input) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "https://api.openai.com/v1/..."; // replace with my API endpoint
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer <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>");
//
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("input", input);
//
//        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            JSONObject responseBody = new JSONObject(response.getBody());
//            // parse the response as needed
//        } else {
//            // handle error response
//        }
//
//    }
}
