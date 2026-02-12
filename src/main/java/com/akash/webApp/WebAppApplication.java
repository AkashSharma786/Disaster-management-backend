package com.akash.webApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.akash.webApp.Controller.ReportController;

@SpringBootApplication
public class WebAppApplication {

	@Autowired
	private ReportController reportController;
	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

}
