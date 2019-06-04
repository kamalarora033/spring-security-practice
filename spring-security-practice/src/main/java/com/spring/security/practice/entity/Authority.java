package com.spring.security.practice.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spring.security.practice.utils.AuthorityType;

@Entity
@Table(name = "authority")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorityId;

	@Enumerated(EnumType.STRING)
	private AuthorityType authorityName;

	public Authority() {
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public AuthorityType getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(AuthorityType authorityName) {
		this.authorityName = authorityName;
	}

}
