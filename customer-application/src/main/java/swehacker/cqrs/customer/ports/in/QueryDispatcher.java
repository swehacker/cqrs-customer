package swehacker.cqrs.customer.ports.in;

import swehacker.demo.cqrs.entities.BaseEntity;
import swehacker.demo.cqrs.queries.BaseQuery;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
