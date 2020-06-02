package vn.foo.core.spring.components.derby.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DerbyOrderRepo extends JpaRepository<DerbyOrder, String> {
}