package com.protocoloApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.protocoloApp.entity.Protocolo;

@Repository
public interface ProtocoloDao extends JpaRepository<Protocolo, Integer>{

	
}
