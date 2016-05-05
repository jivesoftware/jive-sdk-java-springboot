package com.jivesoftware.sdk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleJiveAddOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleJiveAddOnApplication.class, args);
	} // end main
	
//	@Bean
//	public CommandLineRunner loadJiveInstances(JiveInstanceDAO jiveInstanceDAO) {
//		return (args) -> {
//			// Handy for using with applications.properties > spring.jpa.hibernate.ddl-auto=create-drop OR you can use /src/main/resources/(data|schema)-***.sql
//			JiveInstance jiveInstance = new JiveInstance();
//			jiveInstance.setClientId("xxxxx");
//			jiveInstanceDAO.save(jiveInstance);
//		};
//	} // end loadInstances
	
} // end class
