package com.example.notifiertest.processor;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import java.util.Properties;
import java.util.function.Supplier;

public class KafkaTest1 implements Supplier<String> {

    private final String topic;
    private final Producer<String, String> producer;

    public KafkaTest1(String topic, String bootstrapServers) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        this.topic = topic;
        this.producer = new KafkaProducer<>(props);
    }

    @Override
    public String get() {
        String message = "Test1";
        producer.send(new ProducerRecord<>(topic, message));
        return message;
    }

    public void close() {
        producer.close();
    }
}