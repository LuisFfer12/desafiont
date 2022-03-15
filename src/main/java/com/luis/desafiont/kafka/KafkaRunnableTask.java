package com.luis.desafiont.kafka;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaRunnableTask implements Runnable {

    private String message;

    private KafkaProducer producer;

    public KafkaRunnableTask(String message,KafkaProducer producer){
        this.message = message;
        this.producer = producer;
    }

    @Override
    public void run() {
        producer.sendMessage(message);
    }
}
