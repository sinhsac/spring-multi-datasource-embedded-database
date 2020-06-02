package xxx.yyy.zzz.components.derby.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "order_no", length = 20)
    public String orderNo;

    @Column(name = "description", length = 150)
    public String title;

    @Column(name = "created_at")
    public Date createdAt;
}
