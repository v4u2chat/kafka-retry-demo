package com.prepwork.kreadwritemsg.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

// @Component
@Slf4j
public class KListenerWithExceptioHandler {

    // @KafkaListener(topics = "mdsg_sg_obs_eidv_checks",
    //                 groupId = "eidv_check_consumers",
    //                 containerFactory = "vConsumerFactoryForException", 
    //                 id = "QSE-01")
    // public void consume(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws Exception{

       
    // }

}
