package be.intec.querilesscms.models;

import javax.persistence.Entity;

@Entity
public class BeerMock extends Beer{

    public BeerMock() {

        setName("nameBeerMock");
        setAlcohol(-0.00);
        setPrice(0.00);
        setStock(0L);
        setBrewer_id(0L);
        setCategory_id(0L);

    }

}
