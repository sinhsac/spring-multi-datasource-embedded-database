package vn.foo.core.spring.components.h2.order.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.foo.core.spring.components.h2.order.domain.H2Order;
import vn.foo.core.spring.components.h2.order.domain.H2OrderRepo;

import java.util.ArrayList;
import java.util.List;

@RestController("h2GetAll")
@RequestMapping("/h2")
public class GetAll {

    @Autowired
    H2OrderRepo orderRepo;

    @GetMapping("/orders")
    public List<H2Order> getAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/dummy")
    public List<H2Order> dummy() {
        List<H2Order> orders = new ArrayList<>();
        for(int i=0; i<50; i++) {
            orders.add(new H2Order());
        }

        return orderRepo.saveAll(orders);
    }
}