package vn.foo.core.spring.components.h2.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface H2OrderRepo extends JpaRepository<H2Order, String> {
}
