package com.avg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.avg.model.*;
import com.avg.repository.ImsiRepository;

@SpringBootApplication
public class MicroAVG{
	
	
	private static final Logger logger = LoggerFactory.getLogger(MicroAVG.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MicroAVG.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(ImsiRepository imsiRepository) {
		
		return (args) -> {
			Avs avs = new Avs("65342AF5456231234567543218765432",
					"65342AF5456231234567543218765432",
					"65342AF5456231234567543218765432",
					"65342AF5456231234567543218765432",
					"65342AF5456231234567543218765432");

			Avs avs2 = new Avs("65342AF5456231234567543218765433",
					"65342AF5456231234567543218765433",
					"65342AF5456231234567543218765433",
					"65342AF5456231234567543218765433",
					"65342AF5456231234567543218765433");

			imsiRepository.save(new Imsi(100000001, avs));
			try{
				imsiRepository.save(new Imsi(100000002, avs2));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}



			imsiRepository.save(new Imsi(100000003, avs));
			imsiRepository.save(new Imsi(100000004, avs));
			logger.info("The sample data has been generated");


		};
	}

		
	
}

