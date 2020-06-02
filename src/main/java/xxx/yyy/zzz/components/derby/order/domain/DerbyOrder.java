package xxx.yyy.zzz.components.derby.order.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "derby_order")
public class DerbyOrder extends OrderEntity {

    public DerbyOrder() {
        orderNo = UUID.randomUUID().toString();
        title = "derby order with ID " + orderNo;
        type = Type.DERBY;
        createdAt = new Date();
    }
}
