package be.intec.querilesscms.models;

import javax.persistence.Entity;

@Entity
public class BrewerMock extends Brewer{

    public BrewerMock() {

        setName("nameBrewerMock");
        setAddress("MockAddress");
        setCity("MockCity");
        setZip("MockZip");
        setTurnover(0L);

    }

}
