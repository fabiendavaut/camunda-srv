package com.viadialog.camundasrv.bpm;

import com.viadialog.camundasrv.messaging.ConsumerProducerChannel;
import com.viadialog.camundasrv.messaging.MyCommandDTO;
import org.camunda.bpm.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AsynchronousServiceTask extends AbstractBpmnActivityBehavior {

    public static final String EXECUTION_ID = "executionId";

    private ConsumerProducerChannel consumerProducerChannel;

    public AsynchronousServiceTask(ConsumerProducerChannel consumerProducerChannel) {
        super();
        this.consumerProducerChannel = consumerProducerChannel;
    }

    public void execute(final ActivityExecution execution) throws Exception {

        // Build the payload for the message:
        Map<String, Object> payload = new HashMap<String, Object>(execution.getVariables());
        // Add the execution id to the payload:
        payload.put(EXECUTION_ID, execution.getId());

        // Publish a message to the outbound message queue. This method returns after the message has
        // been put into the queue. The actual service implementation (Business Logic) will not yet
        // be invoked:
        // MockMessageQueue.INSTANCE.send(new Message(payload));

        MyCommandDTO myCommandDTO = new MyCommandDTO().executionId(execution.getId());

        consumerProducerChannel.sendCommand().send(MessageBuilder.withPayload(myCommandDTO).build());

    }

    public void signal(ActivityExecution execution, String signalName, Object signalData) throws Exception {

        // leave the service task activity:
        leave(execution);
    }

}
