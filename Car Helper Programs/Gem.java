package com.example;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;





public class Gem {
    static Client client = Client.builder().apiKey("").build();
    String modelText;

    Gem(String model) {
      this.modelText = model;
      
    }

    public String text(String prompt) {
      GenerateContentResponse response =
          client.models.generateContent(
              this.modelText,
              prompt,
              null);
      return response.text();
            
    }
    
}
