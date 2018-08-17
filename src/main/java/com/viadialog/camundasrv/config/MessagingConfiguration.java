package com.viadialog.camundasrv.config;

import com.viadialog.camundasrv.messaging.ConsumerProducerChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Configures Spring Cloud Stream support.
 *
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for more information.
 */
@EnableBinding(value = {ConsumerProducerChannel.class})
public class MessagingConfiguration {
}
