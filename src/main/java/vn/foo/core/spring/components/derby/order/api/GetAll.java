package vn.foo.core.spring.components.derby.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.foo.core.spring.components.derby.order.domain.DerbyOrder;
import vn.foo.core.spring.components.derby.order.domain.DerbyOrderRepo;

import java.util.ArrayList;
import java.util.List;

@RestController("derbyGetAll")
@RequestMapping("/derby")
public class GetAll {

    @Autowired
    DerbyOrderRepo orderRepo;

    @GetMapping("/orders")
    public List<DerbyOrder> getAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/dummy")
    public List<DerbyOrder> dummy() {
        List<DerbyOrder> orders = new ArrayList<>();
        for(int i=0; i<50; i++) {
            orders.add(new DerbyOrder());
        }

        return orderRepo.saveAll(orders);
    }
}
