package tech.bts.herokusample.api;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class SampleApi {

    private final MongoCollection<Document> words;
    
    public SampleApi(@Value("${mongoUri}") String mongoUri){
        MongoClient mongoClient = MongoClients.create(mongoUri);
        MongoDatabase database = mongoClient.getDatabase("test");
        this.words = database.getCollection("words");

    }

    //@RequestMapping(method = RequestMethod.GET, path = "/hello")
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from sample app";
    }

    @GetMapping("/insert")
    public Object insertWord(@RequestParam String word){

        Document appDoc = new Document().append("word", word).append("date", new Date());

        words.insertOne(appDoc);

        return word;

    }

    @GetMapping("/list")
    public List<Object> listWords(){

        List<Object> result = new ArrayList<>();

        for (Document docApp : words.find()){
            result.add(docApp.get("word"));
        }
        return result;
    }
}
