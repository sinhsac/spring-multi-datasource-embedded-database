package vn.foo.core.spring.components.hsql.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HsqlOrderRepo extends JpaRepository<HsqlOrder, String> {
}
