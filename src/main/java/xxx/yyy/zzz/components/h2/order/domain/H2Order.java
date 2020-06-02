package xxx.yyy.zzz.components.h2.order.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "h2_order")
public class H2Order extends OrderEntity {

    public H2Order() {
        orderNo = UUID.randomUUID().toString();
        title = "H2 order with ID " + orderNo;
        type = Type.H2;
        createdAt = new Date();
    }
}
