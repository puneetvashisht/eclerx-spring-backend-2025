package com.pv.spring_cloud_server_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServerDemoApplication.class, args);
	}

}
