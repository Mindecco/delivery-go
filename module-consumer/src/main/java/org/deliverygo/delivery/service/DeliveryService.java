package org.deliverygo.delivery.service;

import lombok.RequiredArgsConstructor;
import org.deliverygo.global.dto.OrderCreateEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final RiderRepository riderRepository;
    private final DeliveryRepository deliveryRepository;
    // 2. 대기중인 라이더 조회
    // 3. 라이더에게 배정( 배달테이블 insert)
    // 4. 몽고DB 에 최초 delivery Insert
    // 5. AFTER_COMMIT : kafka ack, event 처리 완료 redis insert, redislock 해제 <- 근데 여기서 ack 실패나, redis insert 실패 시 다시 시도되기 때문에 그에 따른 코드도 넣어야겠는데 맞아?
    // 6. AFTER_COMMIT : 클라이언트에게 몽고db delivery Id 와 배달중으로 상태변경 push( redis 에 push )
    // 7. schedule: 레디스에서 주기적으로 poll 해서 websocket 으로 alert

    @Transactional
    public void saveDelivery(OrderCreateEvent event) {

        riderRepository.findByWating()

        deliveryRepository.save();
    }
}
