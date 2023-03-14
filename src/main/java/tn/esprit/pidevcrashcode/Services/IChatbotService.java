package tn.esprit.pidevcrashcode.Services;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;

import java.util.List;

public interface IChatbotService {

//    StanfordCoreNLP getPipeline();

    String processPrompt(String prompt);

    List<CoreLabel> tokenize(String formattedPrompt);

    List<CoreLabel> tagTokens(List<CoreLabel> tokens);

    SemanticGraph extractDependencies(List<CoreLabel> taggedTokens);

    String determineIntent(SemanticGraph dependencies);

    String callBackendMethod(String intent);

    String respondWithText(String intent);

    String formatInput(String prompt);
}
