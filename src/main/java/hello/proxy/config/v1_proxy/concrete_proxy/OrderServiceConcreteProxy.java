package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null); //부모 기능을 사용하지 않고 proxy로만 사용할거라서 null 넘겨줌
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderService.request()");
            target.orderItem(itemId);
            logTrace.end(traceStatus);
        }catch(Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }

}
