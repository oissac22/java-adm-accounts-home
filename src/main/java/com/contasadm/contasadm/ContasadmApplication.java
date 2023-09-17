package com.contasadm.contasadm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.contasadm.contasadm.db.StartDB;

@SpringBootApplication
public class ContasadmApplication {

	public static void main(String[] args) {
		StartDB.start();
		SpringApplication.run(ContasadmApplication.class, args);
	}

}
