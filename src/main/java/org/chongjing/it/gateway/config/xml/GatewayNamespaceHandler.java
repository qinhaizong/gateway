package org.chongjing.it.gateway.config.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2017/6/15/015.
 */
public class GatewayNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("gateway", new GatewayBeanDefinitionParser());
    }
}
