package org.chongjing.it.gateway.config.xml;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * Created by Administrator on 2017/6/15/015.
 */
public class GatewayProxyFactoryBean implements FactoryBean<Object>, MethodInterceptor, BeanClassLoaderAware, InitializingBean {


    private volatile Class<?> serviceInterface;

    private volatile Object serviceProxy;

    private volatile ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    public GatewayProxyFactoryBean() {
    }

    public GatewayProxyFactoryBean(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        //TODO 对接口方法类拦截处理
        return "Echo: " + arguments[0];
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public Object getObject() throws Exception {
        if (this.serviceProxy == null) {
            this.afterPropertiesSet();
            Assert.notNull(this.serviceProxy, "failed to initialize proxy");
        }
        return this.serviceProxy;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.serviceInterface != null ? this.serviceInterface : null);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.serviceProxy = new ProxyFactory(this.serviceInterface, this).getProxy(this.beanClassLoader);
    }
}
