package com.solvd.instagram;

import com.solvd.instagram.models.User;
import com.solvd.instagram.parser.UserStaxReader;
import com.solvd.instagram.parser.UserStaxWriter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import javax.imageio.IIOException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(User.class);

        User user = new User();
        user.setId(1L);
        user.setFirstName("Darya");
        user.setLastName("Viarokha");
        user.setDateOfBirth(LocalDate.of(1976,1,1));
        user.getEmailAddress("darya@gmail.com");
        user.setPhoneNumber("123456789");
        user.setUserTypeId(1L);
        user.setProfileId(1L);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(user, new File("User_output.xml"));

        Unmarshaller unmarshaller = context.createUnmarshaller();
        user = (User) unmarshaller.unmarshal(new File("User_output.xml"));

        System.out.println(user);




//        try {
//            List<User> users = UserStaxReader.readUsers("/Users/dashaviarokha/Downloads/Instagram/src/main/resources/Users.xml");
//
//            for (User user : users) {
//                System.out.println(user);
//            }
//
//            UserStaxWriter.writeUsers(users, "/Users/dashaviarokha/Downloads/Instagram/src/main/resources/Users_output.xml");
//
//        } catch (IIOException e) {
//            throw new XMLStreamException(e);
//        }

    }
}
