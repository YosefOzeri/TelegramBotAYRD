import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import java.util.Scanner;

public class CatFactApi {
  //  private String fact;
    //private int length;


    public void request(){
//        System.out.println("Here is your cat fact:");
//        GetRequest getRequest = Unirest.get("https://catfact.ninja/fact");
                try {
            GetRequest getRequest = Unirest.get("https://catfact.ninja/fact");
            HttpResponse<String> response = getRequest.asString();
            System.out.println(response.getStatus());
            System.out.println("--------");
            String  json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            CatFactApi catFactApi = objectMapper.readValue(json, new TypeReference<>() {});
            objectMapper.readValue(json, new TypeReference<>() {});
            System.out.println(catFactApi);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(result);






    }
}
