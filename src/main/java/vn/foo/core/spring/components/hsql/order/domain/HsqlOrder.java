package vn.foo.core.spring.components.hsql.order.domain;

import vn.foo.core.spring.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hsql_order")
public class HsqlOrder extends OrderEntity {

    public HsqlOrder() {
        super();
        title = "HSQL order with ID " + orderNo;
        type = Type.HYPERSQL;
    }
}
