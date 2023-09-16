package swehacker.cqrs.customer.core.vo;

import swehacker.demo.cqrs.vo.BaseId;

public class CustomerNo extends BaseId<String> {
    protected CustomerNo(String value) {
        super(value);
    }
}
