package be.intec.querilesscms.models;

import javax.persistence.Entity;

@Entity
public class UserMock extends User{

    public UserMock() {

        setFirstName("Joe");
        setLastName("de Kort");
        setUsername("JoJo");
        setEmail("joe@mail.be");
        setPassCode("1985");
        setAddress("Diksmuidelaan");
        setCity("Brussels");
        setZip("1000");

    }

}
