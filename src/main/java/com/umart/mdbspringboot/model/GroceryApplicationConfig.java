package com.umart.mdbspringboot.model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.web.bind.annotation.Mapping;

public class GroceryApplicationConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;
    @Override
    protected String getDatabaseName() {
        return database;
    }

//    @Bean
//    @Override
//    public MappingMongoConverter mappingMongoConverter() {
//        List<Converter<?, ?>> converterList = new ArrayList<>();
//        converterList.add(new ContactReadConverter());
//        return new MappingMongoConverter(converterList);
//    }
}
