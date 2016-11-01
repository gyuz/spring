package crud.core.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id 
    @SequenceGenerator(
        name="ROLE_ID_SEQ",
        sequenceName="ROLE_ID_SEQ",
        allocationSize=1
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_ID_SEQ")
    @Column(name = "ROLE_ID")
    private int roleId;
    
    @Column(name = "ROLE_NAME")
    private String roleName;
    
    @ManyToMany(mappedBy = "roles",
                fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Person> persons;    

    public Role(){
        persons = new HashSet<Person>();
    }
    
    public Role(String roleName){
        setRoleName(roleName);    
    }
       
    public Set<Person> getPersons(){
        return persons;
    }
    
    public int getRoleId(){
        return roleId;    
    }    

    public String getRoleName(){
        return roleName;    
    }
    
    public void setRoleId(int roleId){
        this.roleId = roleId;    
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;        
    }
    
    public void setPersons(Set persons){
        this.persons = persons;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Role obj2 = (Role) obj;
        if(this.roleName.equals(obj2.roleName))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = ( roleName ).hashCode();
        return tmp;
    }
}
