package com.inditex.germanheinz;

import com.inditex.germanheinz.init.StreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.inditex.germanheinz.repository")
@EntityScan(basePackages = {"com.inditex.germanheinz.entity"})
@ComponentScan({"com.inditex.germanheinz","com.inditex.germanheinz.service","com.inditex.germanheinz.service.impl"})
public class DemoApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

	private final StreamInitializer streamInitializer;
	public DemoApplication(StreamInitializer initializer) {
		this.streamInitializer = initializer;
	}

	public static void main(String[] args) {SpringApplication.run(DemoApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("App starts...");
		streamInitializer.init();
	}

}
