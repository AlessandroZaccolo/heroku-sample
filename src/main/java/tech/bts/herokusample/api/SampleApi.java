package tech.bts.herokusample.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class SampleApi {

    private MongoCollection<Document> words;

    @Autowired
    public SampleApi(@Value("${mongoUri}") String mongoUri) {

        final MongoClient mongoClient = MongoClients.create(mongoUri);
        final MongoDatabase database = mongoClient.getDatabase("test");
        this.words = database.getCollection("words");
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from sample app";
    }

    // insert?word=dog
    @GetMapping("/insert")
    public String insertWord(@RequestParam String word) {

        final Document doc = new Document()
                .append("word", word)
                .append("date", new Date());

        words.insertOne(doc);

        return "Word inserted: " + word;
    }

    // insert?word=dog
    @GetMapping("/list")
    public List<String> listWords() {

        final List<String> result = new ArrayList<>();

        for (Document doc : words.find()) {
            result.add(doc.getString("word"));
        }

        return result;
    }
}
