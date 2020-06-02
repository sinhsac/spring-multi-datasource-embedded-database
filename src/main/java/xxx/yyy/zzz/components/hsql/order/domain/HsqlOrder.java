package xxx.yyy.zzz.components.hsql.order.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

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
