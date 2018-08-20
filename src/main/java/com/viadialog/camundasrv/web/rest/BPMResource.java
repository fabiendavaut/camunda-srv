package com.viadialog.camundasrv.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;
import com.viadialog.camundasrv.bpm.BPMOverviewDTO;
import com.viadialog.camundasrv.messaging.ConsumerProducerChannel;
import com.viadialog.camundasrv.messaging.ConsumerProducerService;
import com.viadialog.camundasrv.messaging.MyCommandDTO;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/bpm")
public class BPMResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RuntimeService runtimeService;
    private final HistoryService historyService;
    private final ConsumerProducerService consumerProducerService;
    private final ConsumerProducerChannel consumerProducerChannel;


    public BPMResource(RuntimeService runtimeService, ConsumerProducerService consumerProducerService, ConsumerProducerChannel consumerProducerChannel, HistoryService historyService) {
        this.runtimeService = runtimeService;
        this.consumerProducerService = consumerProducerService;
        this.consumerProducerChannel = consumerProducerChannel;
        this.historyService = historyService;
    }

    @PostMapping("/messages")
    @Timed
    public ResponseEntity<Void> sendMessage() {

        try {

            for (Integer i = 0; i < 100; i++) {

                TimeUnit.SECONDS.sleep(1);

                runtimeService.startProcessInstanceByMessage("startMsg", UUID.randomUUID().toString(), ImmutableMap.of("date", LocalDateTime.now()));

            }
        } catch (InterruptedException e) {

        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/count")
    @Timed
    public ResponseEntity<BPMOverviewDTO> count() {


        BPMOverviewDTO bpmMOverviewDTO = new BPMOverviewDTO()
            .input("historyService.createHistoricTaskInstanceQuery().processFinished().count()", historyService.createHistoricTaskInstanceQuery().processFinished().count())
            .input("historyService.createHistoricTaskInstanceQuery().processUnfinished().count()", historyService.createHistoricTaskInstanceQuery().processUnfinished().count())
            .input("historyService.createHistoricTaskInstanceQuery().count()", historyService.createHistoricTaskInstanceQuery().count())
            .input("historyService.createHistoricTaskInstanceQuery().finished().count()", historyService.createHistoricTaskInstanceQuery().finished().count())
            .input("historyService.createHistoricTaskInstanceQuery().unfinished().count()", historyService.createHistoricTaskInstanceQuery().unfinished().count())
            .input("historyService.createHistoricProcessInstanceQuery().completed()", historyService.createHistoricProcessInstanceQuery().completed().count())
            .input("historyService.createHistoricProcessInstanceQuery().finished().count()", historyService.createHistoricProcessInstanceQuery().finished().count())
            .input("historyService.createHistoricProcessInstanceQuery().unfinished().count()", historyService.createHistoricProcessInstanceQuery().unfinished().count())
            .input("historyService.createHistoricProcessInstanceQuery().active().count()", historyService.createHistoricProcessInstanceQuery().active().count());


        return ResponseEntity.ok().body(bpmMOverviewDTO);
    }


    @PostMapping("/ping")
    @Timed
    public ResponseEntity<Void> sendPing() {

        MyCommandDTO myCommandDTO = new MyCommandDTO().label("myLabel").executionId("1");

        consumerProducerChannel.sendCommand().send(MessageBuilder.withPayload(myCommandDTO).build());

        return ResponseEntity.noContent().build();
    }


}
