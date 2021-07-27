package com.exam.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Role {

	@Id
	private Long roleId;
	private String roleName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	@JsonIgnore
	private Set<UserRole> userRoles =new HashSet<>();


	
	
}
