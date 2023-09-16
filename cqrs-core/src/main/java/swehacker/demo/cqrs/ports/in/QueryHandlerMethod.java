package swehacker.demo.cqrs.ports.in;

import swehacker.demo.cqrs.entities.BaseEntity;
import swehacker.demo.cqrs.queries.BaseQuery;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
