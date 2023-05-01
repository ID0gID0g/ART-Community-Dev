package tools;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Objects;

public class DataBase {
    public static void main(String[] args) {
        find(Collection.collection_arts, "id","123");
        delete(Collection.collection_arts, "id", "456");
    }
    private static final MongoDatabase database = MongoClients.create(
            Objects.requireNonNull(Config.getConfig("data_base_url")))
            .getDatabase(Objects.requireNonNull(Config.getConfig("data_base_name"))
            );

    public static void find(String collectionType, String heading, String value){
        Document searchQuery = new Document();
        searchQuery.put(heading, value);
        FindIterable<Document> cursor = database.getCollection(collectionType).find(searchQuery);

        try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
            while (cursorIterator.hasNext()) {
                System.out.println(cursorIterator.next());
            }
        }
    }
    public static void add(String collectionType, Document document){
        database.getCollection(collectionType).insertOne(document);
    }
    public static void delete(String collectionType, String heading, String value){
        Document searchQuery = new Document();
        searchQuery.put(heading, value);

        database.getCollection(collectionType).deleteOne(searchQuery);
    }
}
