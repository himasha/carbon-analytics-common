/*
*  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.event.output.adapter.mqtt;

import org.wso2.carbon.event.output.adapter.core.*;
import org.wso2.carbon.event.output.adapter.mqtt.internal.util.MQTTEventAdapterConstants;

import java.util.*;

public class MQTTEventAdapterFactory extends OutputEventAdapterFactory {
    private ResourceBundle resourceBundle =
            ResourceBundle.getBundle("org.wso2.carbon.event.output.adapter.mqtt.i18n.Resources", Locale.getDefault());

    @Override
    public String getType() {
        return MQTTEventAdapterConstants.ADAPTER_TYPE_MQTT;
    }

    @Override
    public List<String> getSupportedMessageFormats() {
        List<String> supportedMessageFormats = new ArrayList<String>();
        supportedMessageFormats.add(MessageType.XML);
        supportedMessageFormats.add(MessageType.JSON);
        supportedMessageFormats.add(MessageType.TEXT);
        return supportedMessageFormats;
    }

    @Override
    public List<Property> getStaticPropertyList() {

        List<Property> staticPropertyList = new ArrayList<Property>();
        //Broker Url
        Property brokerUrl = new Property(MQTTEventAdapterConstants.ADAPTER_CONF_URL);
        brokerUrl.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_URL));
        brokerUrl.setRequired(true);
        brokerUrl.setHint(resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_URL_HINT));

        //Broker Username
        Property userName = new Property(MQTTEventAdapterConstants.ADAPTER_CONF_USERNAME);
        userName.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_USERNAME));
        userName.setRequired(false);
        userName.setHint(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_USERNAME_HINT));

        //Broker Password
        Property password = new Property(MQTTEventAdapterConstants.ADAPTER_CONF_PASSWORD);
        password.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_PASSWORD));
        password.setRequired(false);
        password.setHint(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_PASSWORD_HINT));

        //Broker clear session
        Property clearSession = new Property(MQTTEventAdapterConstants.ADAPTER_CONF_CLEAN_SESSION);
        clearSession.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_CLEAN_SESSION));
        clearSession.setRequired(false);
        clearSession.setOptions(new String[]{"true", "false"});
        clearSession.setDefaultValue("true");
        clearSession.setHint(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_CLEAN_SESSION_HINT));

        //Broker clear session
        Property keepAlive = new Property(MQTTEventAdapterConstants.ADAPTER_CONF_KEEP_ALIVE);
        keepAlive.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_CONF_KEEP_ALIVE));
        keepAlive.setRequired(false);

        staticPropertyList.add(brokerUrl);
        staticPropertyList.add(userName);
        staticPropertyList.add(password);
        staticPropertyList.add(clearSession);
        staticPropertyList.add(keepAlive);

        return staticPropertyList;

    }

    @Override
    public List<Property> getDynamicPropertyList() {
        List<Property> dynamicPropertyList = new ArrayList<Property>();

        // set topic
        Property topicProperty = new Property(MQTTEventAdapterConstants.ADAPTER_MESSAGE_TOPIC);
        topicProperty.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_MESSAGE_TOPIC));
        topicProperty.setRequired(true);

        // set clientId
        Property clientId = new Property(MQTTEventAdapterConstants.ADAPTER_MESSAGE_CLIENTID);
        clientId.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_MESSAGE_CLIENTID));
        clientId.setRequired(true);

        // set Quality of Service
        Property qos = new Property(MQTTEventAdapterConstants.ADAPTER_MESSAGE_QOS);
        qos.setDisplayName(
                resourceBundle.getString(MQTTEventAdapterConstants.ADAPTER_MESSAGE_QOS));
        qos.setRequired(false);
        qos.setOptions(new String[]{"0", "1", "2"});
        qos.setDefaultValue("1");

        dynamicPropertyList.add(topicProperty);
        dynamicPropertyList.add(clientId);
        dynamicPropertyList.add(qos);

        return dynamicPropertyList;
    }

    @Override
    public OutputEventAdapter createEventAdapter(OutputEventAdapterConfiguration eventAdapterConfiguration,
                                                 Map<String, String> globalProperties) {
        return new MQTTEventAdapter(eventAdapterConfiguration, globalProperties);
    }
}
