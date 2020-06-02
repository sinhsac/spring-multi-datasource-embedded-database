package vn.foo.core.spring.components.common;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class OrderEntity {
    @Id
    @Column(name = "order_no", length = 50)
    public String orderNo;

    @Column(name = "description", length = 150)
    public String title;

    @Column(name = "type", length = 40)
    @Enumerated(EnumType.STRING)
    public Type type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "line", column = @Column(name = "ship_line", length = 150)),
            @AttributeOverride(name = "ward", column = @Column(name = "ship_ward", length = 100)),
            @AttributeOverride(name = "district", column = @Column(name = "ship_district", length = 100)),
            @AttributeOverride(name = "province", column = @Column(name = "ship_province", length = 100))
    })
    public Address shipAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "line", column = @Column(name = "order_line", length = 150)),
            @AttributeOverride(name = "ward", column = @Column(name = "order_ward", length = 100)),
            @AttributeOverride(name = "district", column = @Column(name = "order_district", length = 100)),
            @AttributeOverride(name = "province", column = @Column(name = "order_province", length = 100))
    })
    public Address orderAddress;

    public enum Type {
        DERBY, H2, HYPERSQL, SQLITE
    }

    @Column(name = "created_at")
    public Date createdAt;

    public OrderEntity() {
        orderNo = UUID.randomUUID().toString();
        shipAddress = new Address();
        orderAddress = new Address();
        createdAt = new Date();
    }
}
