package com.protocoloApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.protocoloApp.*"})
@SpringBootApplication
public class ProtocoloAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtocoloAppApplication.class, args);
	}

}
