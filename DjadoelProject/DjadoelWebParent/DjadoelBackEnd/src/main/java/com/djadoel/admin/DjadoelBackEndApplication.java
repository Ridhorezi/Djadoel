package com.djadoel.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.djadoel.common.entity", "com.djadoel.admin.user"})
public class DjadoelBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DjadoelBackEndApplication.class, args);
	}

}
