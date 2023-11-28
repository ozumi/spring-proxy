package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            try {
                throw new IllegalAccessException("exception!");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
