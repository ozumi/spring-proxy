package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{
    private Component component;
    public MessageDecorator(Component component) {
        this.component = component;
    }
    @Override
    public String operation() {
        log.info("MessageDecorator execute");
        String result = component.operation();
        log.info("MessageDecorator deco 전 {}", result);
        String decoResult = "***" + result + "***";
        log.info("MessageDecorator deco 후 {}", decoResult);

        return decoResult;
    }
}
