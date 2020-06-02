package vn.foo.core.spring.components.sqlite.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SqliteOrderRepo extends JpaRepository<SqliteOrder, String> {
}
