package com.protocoloApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protocoloApp.entity.Usuario;

@Repository
public interface UserRepository  extends JpaRepository<Usuario, Integer> {

	
	Usuario findByCpf(String cpf);
	
}


