package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;

public class OrderControllerConcreteProxy extends OrderControllerV2 {
  private final OrderControllerV2 target;
  private final LogTrace logTrace;

  public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace logTrace) {
    super(null);
    this.target = target;
    this.logTrace = logTrace;
  }

  @Override
  public String request(String itemId) {
    TraceStatus traceStatus = null;
    try {
      traceStatus = logTrace.begin("OrderController.request()");
      String result = target.request(itemId);
      logTrace.end(traceStatus);
      return result;
    } catch (Exception e) {
      logTrace.exception(traceStatus, e);
      throw e;
    }
  }

  @Override
  public String noLog() {
    return target.noLog();
  }
}
