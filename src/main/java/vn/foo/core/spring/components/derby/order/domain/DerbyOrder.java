package vn.foo.core.spring.components.derby.order.domain;

import vn.foo.core.spring.components.common.OrderEntity;

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
