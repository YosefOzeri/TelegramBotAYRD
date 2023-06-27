import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import java.util.Scanner;

public class NumbersApi {
    private String str;

    public static void main(String[] args) {
        request();
    }

    public static void request(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("For which number would you like to receive information?" +
                "If you are interested in a random number please write random");
        String number = scanner.nextLine();
        System.out.println("Write down the desired category from the options above: math,trivia,year,data ");
        String category = scanner.nextLine();
        System.out.println("numbersapi.com/"+number+"/"+category);

        try {
            GetRequest getRequest = Unirest.get("numbersapi.com/"+number+"/"+category);
            HttpResponse<String> response = getRequest.asString();
            System.out.println(getRequest.asString().getBody());
         //   System.out.println(response.getBody());
            //System.out.println(response.getStatusText());
        //    System.out.println(response.getBody());
          //  System.out.println(response.getRawBody());
            System.out.println("--------");
            String  json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            NumbersApi numberApi = objectMapper.readValue(json, new TypeReference<>() {});
           objectMapper.readValue(json, new TypeReference<>() {});
            System.out.println(numberApi);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
       // System.out.println(result);





    }

//    @Override
//    public String toString() {
//        return "NumbersApi{" +
//                "str='" + str + '\'' +
//                '}';
//    }
}
