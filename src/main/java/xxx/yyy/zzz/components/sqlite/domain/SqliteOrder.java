package xxx.yyy.zzz.components.sqlite.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "xsqlite_order")
public class SqliteOrder extends OrderEntity {

    public SqliteOrder() {
        orderNo = UUID.randomUUID().toString();
        title = "SQLite order with ID " + orderNo;
        type = Type.SQLITE;
        createdAt = new Date();
    }
}