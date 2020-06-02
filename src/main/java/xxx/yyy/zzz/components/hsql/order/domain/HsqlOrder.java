package xxx.yyy.zzz.components.hsql.order.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "hsql_order")
public class HsqlOrder extends OrderEntity {

    public HsqlOrder() {
        orderNo = UUID.randomUUID().toString();
        title = "HSQL order with ID " + orderNo;
        type = Type.HYPERSQL;
        createdAt = new Date();
    }
}
