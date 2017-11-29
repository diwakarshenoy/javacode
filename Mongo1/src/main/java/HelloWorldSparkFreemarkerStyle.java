import spark.*;  
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreemarkerStyle {  
//    public static void main(String[] args) {  
//        final Configuration configuration = new Configuration();  
//        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");  
//        Spark.get(new Route("/") {  
//            @Override  
//            public Object handle(final Request request, final Response response) {  
//                StringWriter writer = new StringWriter();  
//                try {  
//                    Template helloTemplate = configuration.getTemplate("hello.ftl");  
//                    Map<String, Object> helloMap = new HashMap<String, Object>();  
//                    helloMap.put("name", "FreeMarker");  
//                    helloTemplate.process(helloMap, writer);  
//                    System.out.println(writer);  
//                } catch (Exception e) {  
//                    halt(500);  
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.  
//                }  
//                return writer;  
//            }  
//        });  
//    }
	
	
	public static void main(String[] args) throws UnknownHostException {  
        final Configuration configuration = new Configuration();  
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));  
        DB database = client.getDB("m101");  
        final DBCollection collection = database.getCollection("hw1");

        Spark.get(new Route("/") {  
            @Override  
            public Object handle(final Request request, final Response response) {  
                StringWriter writer = new StringWriter();  
                try {  
                    Template helloTemplate = configuration.getTemplate("hello.ftl");  
                    DBObject document = collection.findOne();

                    helloTemplate.process(document, writer);  
                    System.out.println(writer);  
                } catch (Exception e) {  
                    halt(500);  
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.  
                }  
                return writer;  
            }  
        });  
    }
	
	
}