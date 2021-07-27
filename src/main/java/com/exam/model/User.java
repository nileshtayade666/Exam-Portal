package com.exam.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String username ;
	private String password;
	private String firstname;
	private String lastname ;
	private String email;
	private String phone;
	private boolean enabled =true ;
	private String profile;
	
	@OneToMany(cascade = CascadeType.ALL, fetch =FetchType.EAGER ,mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles =new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Authority> authorities = new HashSet<>();
		
		this.userRoles.forEach(userrole->{
			authorities.add(new Authority(userrole.getRole().getRoleName()));
		});
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


}
