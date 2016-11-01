package crud.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {
    @Column(name = "FIRST_NAME")
    private String firstName;
        
    @Column(name = "LAST_NAME")
    private String lastName;
        
    @Column(name = "MIDDLE_NAME") 
    private String middleName;

    public Name() {
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;    
    }

    public String getLastName() {
        return lastName;    
    }
   
    public String getMiddleName() {
        return middleName;    
    }

    public void setFirstName(String firstName) {   
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
}
