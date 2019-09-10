//package br.com.ryoshino.service;
//
//import br.com.ryoshino.model.ClienteDeserializer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
//import java.util.Properties;
//
//public class CcsKafka {
//
//    public Properties configurationKafka(){
//        String bootstrapServers = "127.0.0.1:9092";
//        String groupId = "my-ccs-application";
//
//        // create consumer configs
//        Properties properties = new Properties();
//        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ClienteDeserializer.class.getName());
//        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
//        return properties;
//    }
//}
