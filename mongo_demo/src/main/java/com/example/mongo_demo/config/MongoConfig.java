package com.example.mongo_demo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MongoConverter;


/**
 *
 */
@Configuration
public class MongoConfig {
    /*
    在开发过程中，如果com.mongodb.WriteResult任何 MongoDB 操作返回的包含错误，记录或抛出异常是很方便的。
    在开发过程中忘记这样做是很常见的，然后最终得到一个看起来运行成功的应用程序，而实际上，数据库并没有按照您的预期进行修改。
    您可以将 的WriteResultChecking属性设置为MongoTemplate以下值之一：EXCEPTION或NONE，分别用于抛出Exception或不执行任何操作。
    默认值是使用 的WriteResultChecking值NONE。
     */
    @Bean
    MongoTemplate mongoTemplate(MongoDatabaseFactory factory, MongoConverter converter) {
        MongoTemplate mongoTemplate = new MongoTemplate(factory, converter);
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return mongoTemplate;
    }

}
