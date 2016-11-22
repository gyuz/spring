package crud.core.service;
 
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import crud.core.model.Person;
import crud.core.model.Role;
 
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
 
    @Autowired
    private PersonService userService;
     
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        DataParser dataParser = new DataParser();
        Person user = userService.findBySso(dataParser.stringToInt(ssoId));
        Set<Role> userRoles = new HashSet();
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        } else {
            userRoles = user.getRoles();
        }
            return new org.springframework.security.core.userdetails.User(user.getId()+"", user.getPassword(), user.getActive(), true, true, true, getGrantedAuthorities(userRoles));
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(Set<Role> userRoles){
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
    }
}
