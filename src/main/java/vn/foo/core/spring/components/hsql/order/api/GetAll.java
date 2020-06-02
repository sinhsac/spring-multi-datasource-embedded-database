package vn.foo.core.spring.components.hsql.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.foo.core.spring.components.hsql.order.domain.HsqlOrder;
import vn.foo.core.spring.components.hsql.order.domain.HsqlOrderRepo;

import java.util.ArrayList;
import java.util.List;

@RestController("hsqlGetAll")
@RequestMapping("/hsql")
public class GetAll {

    @Autowired
    HsqlOrderRepo orderRepo;

    @GetMapping("/orders")
    public List<HsqlOrder> getAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/dummy")
    public List<HsqlOrder> dummy() {
        List<HsqlOrder> orders = new ArrayList<>();
        for(int i=0; i<50; i++) {
            orders.add(new HsqlOrder());
        }

        return orderRepo.saveAll(orders);
    }
}