package com.rockville.utunes_sms_handler.service;

import com.google.gson.Gson;
import com.rockville.utunes_sms_handler.dto.SmsHandler;
import com.rockville.utunes_sms_handler.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsHandlerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.sms.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.sms.queueRoutingKey}")
    private String routingKey;
    @Autowired
    private Gson gson;

    public void pushMessageInQueue(String route, String destination, String source, String message) {
        try {
            SmsHandler smsHandler = SmsHandler.builder()
                    .destination(destination)
                    .source(92 + Utility.normalizeMsisdn(source))
                    .messageBody(message.getBytes())
                    .messageLength(message.length())
                    .optionalParamCount(0)
                    .messageId(0)
                    .sequenceNum(12391681)
                    .esmClass("0")
                    .messageEncoding("0")
                    .sourceTon(1)
                    .sourceNpi(1)
                    .destinationTon(2)
                    .destinationNpi(1)
                    .priority(0).
                    registeredDelivery(0)
                    .LA(route)
                    .build();

            String result = gson.toJson(smsHandler);
            log.info("SmsHandler JSON: {}", result);
            rabbitTemplate.convertAndSend(exchange, routingKey, result);
        } catch (Exception e) {
            log.error("Exception during pushing in queue: {}", e);
        }
    }
}
