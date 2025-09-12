package com.solvd.instagram;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.instagram.models.User;
import com.solvd.instagram.models.UsersXML;
import com.solvd.instagram.models.WrapperJSON;
import com.solvd.instagram.parser.UserStaxReader;
import com.solvd.instagram.parser.UserStaxWriter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        final Logger logger = LogManager.getLogger(Main.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        WrapperJSON jsonWrapper = mapper.readValue(new File("src/main/resources/User.json"), WrapperJSON.class);

        mapper.writeValue(new File("src/main/resources/User_output.json"), jsonWrapper);




        JAXBContext context = JAXBContext.newInstance(UsersXML.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file =  new File("src/main/resources/User.xml");
        UsersXML usersXML = (UsersXML) unmarshaller.unmarshal(file);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(usersXML,new File("src/main/resources/User_output.xml"));





        List<User> users = UserStaxReader.readUsers("/Users/dashaviarokha/Downloads/Instagram/src/main/resources/User.xml");
        for (User user : users) {
            System.out.println(user);
        }
        UserStaxWriter.writeUsers(users, "/Users/dashaviarokha/Downloads/Instagram/src/main/resources/Users_output.xml");

    }
}
