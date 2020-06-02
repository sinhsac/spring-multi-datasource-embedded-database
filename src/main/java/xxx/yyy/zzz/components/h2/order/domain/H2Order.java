package xxx.yyy.zzz.components.h2.order.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "h2_order")
public class H2Order extends OrderEntity {

    public H2Order() {
        super();
        title = "H2 order with ID " + orderNo;
        type = Type.H2;
    }
}
