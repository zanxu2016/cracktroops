package info.luckydog.javacore.exception;

import org.junit.Test;

public class ExceptionDemo {

    @Test
    public void tryThrowCatch() throws Exception {
        try {
            throw new Exception("try throw");
        } catch (Exception e) {
            System.out.println("exception caught, e: " + e.getMessage());
            throw e;
        }
    }
}
