package ru.otveska.yclients.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.otveska.yclients.model.Resource;
import ru.otveska.yclients.service.handler.ResourceHandler;
import ru.otveska.yclients.service.handler.StubResourceHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResourceHandlerFactory implements BeanFactoryAware, InitializingBean {

    private BeanFactory beanFactory;

    private static final ResourceHandler defaultHandler = new StubResourceHandler();

    private Map<String, ResourceHandler> handlerMap = new HashMap<>();

    public ResourceHandler getResourceHandler(Resource resource) {
        return handlerMap.getOrDefault(resource.name().toLowerCase(), defaultHandler);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (Resource resource : Resource.values()) {
            if (beanFactory.containsBean(resource.name().toLowerCase())) {
                handlerMap.put(resource.name().toLowerCase(), (ResourceHandler) beanFactory.getBean(resource.name().toLowerCase()));
            } else if (beanFactory.containsBean(resource.name().toUpperCase())) {
                handlerMap.put(resource.name().toLowerCase(), (ResourceHandler) beanFactory.getBean(resource.name().toUpperCase()));
            }
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
