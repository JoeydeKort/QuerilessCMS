package be.intec.querilesscms.models;

import javax.persistence.Entity;

@Entity
public class CategoryMock extends Category{

    public CategoryMock() {

        setTitle("mockTitle");
        setSlug("mockSlug");

    }


}
