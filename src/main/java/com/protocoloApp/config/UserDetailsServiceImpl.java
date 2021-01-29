package com.protocoloApp.config;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.protocoloApp.dao.UserRepository;
import com.protocoloApp.entity.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String cpf) {
		Usuario user = userRepository.findByCpf(cpf);

		if (user == null) {
			throw new UsernameNotFoundException(cpf);
		}
		return new org.springframework.security.core.userdetails.User(user.getCpf(), user.getPassword(), emptyList());
	}
}
