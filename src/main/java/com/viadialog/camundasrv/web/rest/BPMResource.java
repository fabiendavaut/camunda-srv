package com.viadialog.camundasrv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;
import com.viadialog.camundasrv.messaging.ConsumerProducerChannel;
import com.viadialog.camundasrv.messaging.ConsumerProducerService;
import com.viadialog.camundasrv.messaging.MyCommandDTO;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/bpm")
public class BPMResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RuntimeService runtimeService;
    private final ConsumerProducerService consumerProducerService;
    private final ConsumerProducerChannel consumerProducerChannel;

    public BPMResource(RuntimeService runtimeService, ConsumerProducerService consumerProducerService, ConsumerProducerChannel consumerProducerChannel) {
        this.runtimeService = runtimeService;
        this.consumerProducerService = consumerProducerService;
        this.consumerProducerChannel = consumerProducerChannel;
    }

    @PostMapping("/messages")
    @Timed
    public ResponseEntity<Void> sendMessage(@RequestBody String message) {

        runtimeService.startProcessInstanceByMessage(message, UUID.randomUUID().toString(), ImmutableMap.of("date", LocalDateTime.now()));

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ping")
    @Timed
    public ResponseEntity<Void> sendPing() {

        MyCommandDTO myCommandDTO = new MyCommandDTO().label("myLabel").executionId("1");

        consumerProducerChannel.sendCommand().send(MessageBuilder.withPayload(myCommandDTO).build());

        return ResponseEntity.noContent().build();
    }


}
