package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Contact file")
    public  String file;

    @Parameter(names = "-d", description = "Contact dataFormat")
    public  String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generatorContact(count);
        if (format.equals("cav")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try ( Writer writer = new FileWriter(file)){
            writer.write(json);
        }

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try ( Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }

    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write((String.format("%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
                        contact.getNickname(), contact.getAddress(), contact.getEmail(), contact.getHomeTelephone())));
            }
        }
    }

    private static List<ContactData> generatorContact(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Name %s", i))
                    .withLastName(String.format("LastName %s", i)).withNickname(String.format("Nickname %s", i))
                    .withAddress(String.format("Address %s", i)).withEmail(String.format("Email %s", i))
                    .withHomeTelephone(String.format("HomeTelephone %s", i)));
        }
        return contacts;
    }
}