package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{
    private Subject target;
    private String cacheValue;

    //realSubject 주입
    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("call proxy");
        if (cacheValue == null) {
            log.info("save cache");
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}
