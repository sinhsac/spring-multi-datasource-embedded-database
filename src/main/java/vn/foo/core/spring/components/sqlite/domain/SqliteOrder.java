package vn.foo.core.spring.components.sqlite.domain;

import vn.foo.core.spring.components.common.OrderEntity;

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