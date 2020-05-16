package ru.otveska.yclients.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 *  Настройка уведомлении о событиях
 *  https://yclients.docs.apiary.io/#reference/webhooks/0
 *
 * {
 *   "url": "https://dev.tools.test.com/test/Hooks",
 *   "active": 0,
 *   "salon": 1,
 *   "service_category": 0,
 *   "service": 1,
 *   "good": 1,
 *   "master": 1,
 *   "client": 1,
 *   "record": 1,
 *   "goods_operations_sale": 1,
 *   "goods_operations_receipt": 1,
 *   "goods_operations_consumable": 1,
 *   "goods_operations_stolen": 1,
 *   "goods_operations_move": 1,
 *   "finances_operation": 1
 * }
 */
@Data
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WebHookEventsSettings {

    String url;

    Integer active;

    Integer salon;

    @JsonAlias("service_category")
    Integer serviceCategory;

    Integer good;

    Integer master;

    Integer client;

    Integer record;

    @JsonAlias("goods_operations_sale")
    Integer goodsOperationsSale;

    @JsonAlias("goods_operations_receipt")
    Integer goodsOperationsReceipt;

    @JsonAlias("goods_operations_consumable")
    Integer goodsOperationsConsumable;

    @JsonAlias("goods_operations_stolen")
    Integer goodsOperationsStolen;

    @JsonAlias("goods_operations_move")
    Integer goodsOperationsMove;

    @JsonAlias("finances_operation")
    Integer financesOperation;

}
