package org.chongjing.it.gateway.config.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15/015.
 */
public class GatewayBeanDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Map<String, Object> attributes = new HashMap<>();
        String id = element.getAttribute(AbstractBeanDefinitionParser.ID_ATTRIBUTE);
        attributes.put("name", id);
        String serviceInterface = element.getAttribute("service-interface");
        attributes.put("serviceInterface", serviceInterface);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(GatewayProxyFactoryBean.class);
        builder.addConstructorArgValue(serviceInterface);
        BeanDefinitionHolder holder = new BeanDefinitionHolder(builder.getBeanDefinition(), id);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, parserContext.getRegistry());
        return null;
    }
}
