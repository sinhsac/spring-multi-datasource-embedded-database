package xxx.yyy.zzz.components.derby.order.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "derby_order")
public class DerbyOrder extends OrderEntity {

    public DerbyOrder() {
        super();
        title = "derby order with ID " + orderNo;
        type = Type.DERBY;
    }
}
