package com.fpt.hr.recruitment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@ComponentScan({"com.fpt.hr.recruitment.persistence"})
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Override
    protected String getDatabaseName() {
        return "demo";
    }

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}

    @Override
    protected String getMappingBasePackage() {
        return "com.fpt.hr.recruitment.dao.model";
    }
}
