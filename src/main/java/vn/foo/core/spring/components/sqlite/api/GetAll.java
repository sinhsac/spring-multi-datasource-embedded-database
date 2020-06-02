package vn.foo.core.spring.components.sqlite.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.foo.core.spring.components.sqlite.domain.SqliteOrder;
import vn.foo.core.spring.components.sqlite.domain.SqliteOrderRepo;

import java.util.ArrayList;
import java.util.List;

@RestController("sqliteGetAll")
@RequestMapping("/sqlite")
public class GetAll {
    @Autowired
    SqliteOrderRepo orderRepo;

    @GetMapping("/orders")
    public List<SqliteOrder> getAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/dummy")
    public List<SqliteOrder> dummy() {
        List<SqliteOrder> orders = new ArrayList<>();
        for(int i=0; i<50; i++) {
            orders.add(new SqliteOrder());
        }

        return orderRepo.saveAll(orders);
    }
}
