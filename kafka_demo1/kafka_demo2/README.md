# 一、生产者

## 编译
    cd producer && mvn clean package -pl roducer -am -DskipTests
## 启动
    java -jar producer/target/producer-0.0.1-SNAPSHOT.jar --server.port=8081 


------------------------
# 二、消费者

## 编译
    cd consumer && mvn clean -U package -Dmaven.test.skip=true
## 启动
    java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar --server.port=9001
    java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar --server.port=9002
 


# 三、测试验证：
## 多个消费组
java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar --server.port=9002 --spring.kafka.consumer.group-id="myGroup2" --spring.kafka.consumer.client-id="client2" 