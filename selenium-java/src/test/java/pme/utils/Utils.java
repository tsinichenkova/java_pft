package pme.utils;

import io.restassured.path.json.JsonPath;
import net.lightbody.bmp.core.har.Har;
import pme.test.TestBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils extends TestBase {
    public static String getContent (String name) throws FileNotFoundException {
        Har har = app.proxy().getHar();
        try {
            har.getLog().getEntries().removeIf(x-> !x.getRequest().getUrl().contains(name));
            har.writeTo(new File("har.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileReader json = new FileReader("har.json");
        return (JsonPath.from(json).getString("log.entries[0].response.content.text"));
    }

    public static String getRequest (String name) throws FileNotFoundException {
        Har har = app.proxy().getHar();
        try {
            har.getLog().getEntries().removeIf(x-> !x.getRequest().getUrl().contains(name));
            har.writeTo(new File("har.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileReader json = new FileReader("har.json");
        return (JsonPath.from(json).getString("log.entries[0].request.postData.text"));
    }

    public static void cancelCareEvent() {
        try {
            String request = Utils.getRequest("getCareEventHistory");
            String careEventId = JsonPath.from(request).getString("careEventId");
            app.http().cancelCareEvent(careEventId);
            System.out.println("Завершено клиническое событие: "+careEventId);

        } catch (IOException c) {
            c.printStackTrace();
        }
    }
}
