package com.viadialog.camundasrv.messaging;

import org.camunda.bpm.engine.BadUserRequestException;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerProducerService {

    private final Logger log = LoggerFactory.getLogger(ConsumerProducerService.class);

    private ProcessEngine processEngine;
    private ConsumerProducerChannel consumerProducerChannel;

    public ConsumerProducerService(ProcessEngine processEngine, ConsumerProducerChannel consumerProducerChannel) {
        this.processEngine = processEngine;
        this.consumerProducerChannel = consumerProducerChannel;
    }

//    @StreamListener(ConsumerProducerChannel.RECEIPT_CHANNEL)
//    public synchronized void consumeReceipt(MyReceiptDTO myReceiptDTO) {
//
//        log.debug("Received message: {}.", myReceiptDTO);
//
////        processEngine.getRuntimeService().signal(myReceiptDTO.getExecutionId());
//        Integer i = Integer.valueOf(myReceiptDTO.getExecutionId()) + 1;
//
//        // sendCommand(new MyCommandDTO().label("MyLabel").executionId(i.toString()));
//        MyCommandDTO myCommandDTO = new MyCommandDTO().label("MyLabel").executionId(i.toString());
//
//        this.consumerProducerChannel.sendCommand().send(MessageBuilder.withPayload(myCommandDTO).build());
//    }

    @StreamListener(ConsumerProducerChannel.RECEIPT_CHANNEL)
    public synchronized void consumeReceipt(GenericDTO genericDTO) {

        log.debug("Received message: {}.", genericDTO);

        if (genericDTO instanceof MyReceiptDTO) {

//            try {



                processEngine.getRuntimeService().signal(((MyReceiptDTO) genericDTO).getExecutionId());



//            } catch (BadUserRequestException e) {
//                log.debug("Bad execution Id :" + e.getMessage());
//
//                log.debug("Bad execution Id :" + ((MyReceiptDTO) genericDTO).getExecutionId());
//                log.debug("Execution count :" + processEngine.getRuntimeService().createExecutionQuery().count());
//                log.debug("Available Execution : " + processEngine.getRuntimeService().createExecutionQuery().list());
//
//                throw new RuntimeException("Execution not yet available");
//            }
        }
    }



}
