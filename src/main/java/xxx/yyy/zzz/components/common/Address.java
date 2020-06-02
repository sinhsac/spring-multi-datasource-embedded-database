package xxx.yyy.zzz.components.common;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class Address {
    public String line;
    public String ward;
    public String district;
    public String province;

    public Address() {
        line = "line " + UUID.randomUUID();
        ward = "ward " + UUID.randomUUID();
        district = "district " + UUID.randomUUID();
        province = "province " + UUID.randomUUID();
    }
}
