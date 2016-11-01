package crud.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "CONTACT")
public class Contact {
    @Id
    @SequenceGenerator(
        name="CONTACT_SEQ",
        sequenceName="CONTACT_SEQ",
        allocationSize=1
    ) 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CONTACT_SEQ")
    @Column(name = "CONTACT_ID")
    private int contactId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_TYPE")
    private Types contactType;
        
    @Column(name = "CONTACT_DETAILS")
    private String details;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    public Contact() {
    }
    
    public Person getPerson(){
        return person;
    }    
    
    public int getContactId() {
        return contactId;    
    }    

    public Types getContactType() {
        return contactType;    
    }

    public String getDetails(){
        return details;    
    }

    public void setPerson(Person person){
        this.person = person;    
    }    

    public void setContactId(int contactId) {
        this.contactId = contactId;    
    }

    public void setContactType(Types contactType) {
        this.contactType = contactType;
    }
    
    public void setDetails(String details) {
        this.details = details;    
    }
        
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Contact obj2 = (Contact) obj;
        if(this.contactType.equals(obj2.contactType) 
            && this.details.equals(obj2.details)
            && this.person.equals(obj2.person))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( person.getId() + contactType.name() + details ).hashCode();
        return tmp;
    }

}
