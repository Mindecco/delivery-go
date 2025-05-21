package org.deliverygo.delivery.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deliverygo.delivery.service.DeliveryService;
import org.deliverygo.global.dto.OrderCreateEvent;
import org.deliverygo.global.helper.RedisService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreateConsumer {

    private final String TOPIC = "order-topic";
    private final RedisService redisService;
    private final DeliveryService deliveryService;


    // 1. order create 컨슘해서
    // 1. 이미 처리 된 eventId 인지 redis 에서 조회
    // 2. 이미 처리됐으면 무시

    @KafkaListener(topics = TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public void listenOrderCreate(OrderCreateEvent event) {
        log.debug("[module-consumer] order-create 이벤트 수신 성공: topic = {}, event = {}", TOPIC, event);

        boolean acquired = redisService.setIfAbsent("order:create:" + event.getOrderId(), "1", Duration.ofMinutes(5));

        if (!acquired) {
            log.debug("이미 처리 중인 orderId: {}", event.getOrderId());
            return;
        }
        deliveryService.saveDelivery(event);
    }
}
