package ru.otveska.yclients.service.handler;

import org.springframework.http.HttpEntity;

/**
 * Обработчик по типу измений Resource.
 * Для реализации обработчика по типу Resource просто унаследуйтесь от ResourceHandler с именем компонента @Component("ResourceName").
 * Где "ResourceName"  - имя enum из Resource. Например, для Resource.COMPANY  @Component("COMPANY").
 */
public interface ResourceHandler {

    HandleResult handle(HttpEntity<String> httpEntity);

}
