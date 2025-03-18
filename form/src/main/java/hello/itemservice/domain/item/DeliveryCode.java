package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */

@Data // Getter, Setter, ToString 등
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
public class DeliveryCode {
    private String code;
    private String displayName;
}
