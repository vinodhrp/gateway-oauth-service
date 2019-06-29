package com.spring.gateway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.gateway.entity.AuthGroup;

@Repository
public interface AuthGroupRespository  extends JpaRepository<AuthGroup, Long>{
	
	public List<AuthGroup> findByEmail(String email);

}
