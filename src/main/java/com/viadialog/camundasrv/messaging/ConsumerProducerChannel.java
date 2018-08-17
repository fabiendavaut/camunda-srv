package com.viadialog.camundasrv.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerProducerChannel {


    String COMMAND_CHANNEL = "command";

    String RECEIPT_CHANNEL = "receipt";

    @Input(RECEIPT_CHANNEL)
    SubscribableChannel receiveReceipt();

    @Output(COMMAND_CHANNEL)
    MessageChannel sendCommand();

}
