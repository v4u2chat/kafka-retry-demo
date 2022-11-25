package com.prepwork.kreadwritemsg.consumer;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KListener {

    @RetryableTopic(attempts = "2", backoff = @Backoff(delay = 60000), autoCreateTopics = "true", autoStartDltHandler="false")    //backoff = @Backoff(delay = 5000, multiplier = 3.0),
    @KafkaListener(topics = "mdsg_sg_obs_eidv_checks",groupId = "eidv_check_consumers") // ,containerFactory = "vConsumerFactory"
    public void consume(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws Exception{

        log.info("==============================================================");
        log.info("topic name : {}", topic);
        log.info("consumed message is : {}", msg);

        if(msg.contains("STILL_PROCESSING")){
            // if("mdsg_sg_obs_eidv_checks".equals(topic)){
                log.info("This Message \"{}\"has to be reprocessed again.", msg);
                throw new Exception("This Message has to be reprocessed again.");
            // }else {
            //     log.info("Processed on REATTEMPT...You see I got this msg from retry topic ....\nI understand this Demo sucks...But, this is what I wanted to show lah! :)", msg);
            // }
            
        }

        log.info("msg consumed successfully...no need to retry this msg : {}", msg);
        log.info("==============================================================");

    }

    @DltHandler
    public void dlt(String msg, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        System.out.println("Dead Message : "+msg +" from "+topic);
    }

}
