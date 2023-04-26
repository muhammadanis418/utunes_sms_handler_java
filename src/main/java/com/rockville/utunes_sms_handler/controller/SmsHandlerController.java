package com.rockville.utunes_sms_handler.controller;

import com.rockville.utunes_sms_handler.service.SmsHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SmsHandlerController {
    @Autowired
    private SmsHandlerService smsHandlerService;

    @GetMapping("/la2la")
    public void pushMessage(@RequestParam(name = "route") String route, @RequestParam(name = "dest") String destination,
                            @RequestParam(name = "source") String source, @RequestParam(name = "message") String message,
                            @RequestParam(name = "enc") String encoding,
                            @RequestParam(required = false) String silent, @RequestParam(required = false) String passThrough,
                            @RequestParam(required = false) String ack_Required) {
        log.info("Route: {}, destination: {}, source: {}, message:{}", route, destination, source, message);
        smsHandlerService.pushMessageInQueue(route, destination, source, message);
    }
}
