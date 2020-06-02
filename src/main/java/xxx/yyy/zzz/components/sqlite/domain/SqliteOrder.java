package xxx.yyy.zzz.components.sqlite.domain;

import xxx.yyy.zzz.components.common.OrderEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "xsqlite_order")
public class SqliteOrder extends OrderEntity {

    public SqliteOrder() {
        super();
        title = "SQLite order with ID " + orderNo;
        type = Type.SQLITE;
    }
}