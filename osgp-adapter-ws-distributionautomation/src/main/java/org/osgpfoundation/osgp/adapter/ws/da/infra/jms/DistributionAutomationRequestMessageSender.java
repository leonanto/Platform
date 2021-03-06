/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.osgpfoundation.osgp.adapter.ws.da.infra.jms;

import com.alliander.osgp.domain.core.exceptions.ArgumentNullOrEmptyException;
import com.alliander.osgp.shared.infra.jms.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Class for sending distribution automation request messages to a queue
 */
public class DistributionAutomationRequestMessageSender {
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionAutomationRequestMessageSender.class);

    /**
     * Autowired field for distribution automation requests jms template
     */
    @Autowired
    @Qualifier("wsDistributionAutomationOutgoingRequestsJmsTemplate")
    private JmsTemplate distributionautomationRequestsJmsTemplate;

    /**
     * Method for sending a request message to the queue
     *
     * @param requestMessage
     *            The DistributionAutomationRequestMessage request message to send.
     * @throws ArgumentNullOrEmptyException
     */
    public void send(final DistributionAutomationRequestMessage requestMessage) throws ArgumentNullOrEmptyException {
        LOGGER.debug("Sending distribution automation request message to the queue");

        if (requestMessage.getMessageType() == null) {
            LOGGER.error("MessageType is null");
            throw new ArgumentNullOrEmptyException("MessageType");
        }
        if (StringUtils.isBlank(requestMessage.getOrganisationIdentification())) {
            LOGGER.error("OrganisationIdentification is blank");
            throw new ArgumentNullOrEmptyException("OrganisationIdentification");
        }
        if (StringUtils.isBlank(requestMessage.getDeviceIdentification())) {
            LOGGER.error("DeviceIdentification is blank");
            throw new ArgumentNullOrEmptyException("DeviceIdentification");
        }
        if (StringUtils.isBlank(requestMessage.getCorrelationUid())) {
            LOGGER.error("CorrelationUid is blank");
            throw new ArgumentNullOrEmptyException("CorrelationUid");
        }

        this.sendMessage(requestMessage);
    }

    /**
     * Method for sending a request message to the da requests queue
     *
     * @param requestMessage
     *            The DistributionAutomationRequestMessage request message to send.
     */
    private void sendMessage(final DistributionAutomationRequestMessage requestMessage) {
        LOGGER.info("Sending message to the da requests queue");

        this.distributionautomationRequestsJmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(final Session session) throws JMSException {
                final ObjectMessage objectMessage = session.createObjectMessage(requestMessage.getRequest());
                objectMessage.setJMSCorrelationID(requestMessage.getCorrelationUid());
                objectMessage.setJMSType(requestMessage.getMessageType().toString());
                objectMessage.setStringProperty(Constants.ORGANISATION_IDENTIFICATION,
                        requestMessage.getOrganisationIdentification());
                objectMessage.setStringProperty(Constants.DEVICE_IDENTIFICATION,
                        requestMessage.getDeviceIdentification());
                return objectMessage;
            }
        });
    }
}
