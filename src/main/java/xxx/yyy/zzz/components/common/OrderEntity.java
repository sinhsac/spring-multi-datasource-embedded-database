package xxx.yyy.zzz.components.common;

import javax.persistence.*;
import java.util.Date;

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

    public enum Type {
        DERBY, H2, HYPERSQL, SQLITE
    }

    @Column(name = "created_at")
    public Date createdAt;
}
