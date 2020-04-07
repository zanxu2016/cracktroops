package info.luckydog.javacore.concurrent;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 多线程下获取accessToken
 */
public class WeChatAccessTokenDemo {

    private AtomicBoolean locked = new AtomicBoolean(false);

    private volatile String accessToken;

    public static void main(String[] args) {

        WeChatAccessTokenDemo demo = new WeChatAccessTokenDemo();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    long start = System.currentTimeMillis();
                    String accessToken = demo.getAccessToken();
                    long end = System.currentTimeMillis();
                    System.err.println(String.format("thread %s get access_token cost %s ms", Thread.currentThread().getName(), (end - start)));

                } catch (Exception e) {
                    System.out.println(String.format("thread %s get access_token error, cause: %s", Thread.currentThread().getName(), e.getMessage()));
                }
            }, "t" + i).start();
        }
    }

    public String getAccessToken() throws Exception {
        System.out.println(String.format("thread %s try to get access_token...", Thread.currentThread().getName()));
        String tokenInCache = this.getTokenInCache();
        if (StringUtils.isNotBlank(tokenInCache)) {
            return tokenInCache;
        }

        long expire = 5000;

        if (this.tryLock(expire)) {
            String token = this.getTokenAfterLock();
            if (StringUtils.isBlank(token)) {
                throw new Exception("系统异常：未获取到access_token，请稍后重试！");
            }
            return token;
        }

        String accessTokenOnRetry = this.getTokenOnRetry();
        if (StringUtils.isBlank(accessTokenOnRetry)) {
            throw new Exception("系统异常：未获取到access_token，请稍后重试！");
        }
        return accessTokenOnRetry;
    }

    public String getTokenInCache() {
        // 模拟redis获取token----耗时10ms
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String accessToken = this.accessToken;
        System.out.println(String.format("thread %s get token in cache: %s", Thread.currentThread().getName(), accessToken));
        return accessToken;
    }

    public boolean tryLock(long expire) {
        boolean lock = this.locked.get();
        if (lock) {
            System.out.println(String.format("thread %s lock failed.", Thread.currentThread().getName()));
            return false;
        }
        boolean locked = this.locked.compareAndSet(false, true);
        if (locked) {
            System.err.println(String.format("thread %s lock successful.", Thread.currentThread().getName()));
            // 给lock字段设置过期时间
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(expire);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("thread %s unlock result = %s", Thread.currentThread().getName(), this.locked.compareAndSet(true, false)));
            }, "Ex");

        }
        return locked;
    }

    public String getTokenAfterLock() {
        // 先拿cache中token
        String accessToken = this.getTokenInCache();
        if (StringUtils.isNotBlank(accessToken)) {
            return accessToken;
        }

        String accessTokenFromApi = null;
        try {
            long timeout = 3000;
            accessTokenFromApi = this.getTokenFromApi(timeout);
            if (StringUtils.isNotBlank(accessTokenFromApi)) {
                System.out.println(String.format("thread %s set token %s in cache", Thread.currentThread().getName(), accessTokenFromApi));
                this.accessToken = accessTokenFromApi;
            }
        } catch (Exception e) {
            System.out.println(String.format("thread %s get token from api error, cause: %s", Thread.currentThread().getName(), e.getMessage()));
        } finally {
            // 拿到token，解锁lock
            System.out.println(String.format("thread %s unlock result = %s", Thread.currentThread().getName(), this.locked.compareAndSet(true, false)));
        }

        return accessTokenFromApi;
    }

    private String getTokenOnRetry() {
        int interval = 1000;
        int intervalDelta = 200;
        for (int i = 1; i <= 3; i++) {
            System.out.println(String.format("thread %s get token on retry %s time.", Thread.currentThread().getName(), i));
            try {
                TimeUnit.MILLISECONDS.sleep(interval * i + intervalDelta);
            } catch (InterruptedException e) {
                System.out.println(String.format("thread %s get token on retry interrupted, cause: %s", Thread.currentThread().getName(), e.getMessage()));
            }
            String tokenInCache = this.getTokenInCache();
            if (StringUtils.isNotBlank(tokenInCache)) {
                return tokenInCache;
            }
            long expire = 5000;
            if (this.tryLock(expire)) {
                return this.getTokenAfterLock();
            }
        }
        return null;
    }

    private String getTokenFromApi(long timeout) throws Exception {
        String accessTokenFromApi;
        try {
            accessTokenFromApi = "1234567890";// api获取的token，可设置成功和失败两种，此处均设置成功

            // 模拟通过微信api获取accessToken----耗时3秒内
            long delta = 2000;
            long costMillis = new Random().nextInt((int) (timeout + delta));
            if (costMillis > timeout) {
                accessTokenFromApi = null;
                costMillis = timeout;
            }
            TimeUnit.MILLISECONDS.sleep(costMillis);
            System.err.println(String.format("thread %s get token from api, cost %s ms", Thread.currentThread().getName(), costMillis));
        } catch (Exception e) {
            throw new Exception("thread" + Thread.currentThread().getName() + " get token from api error, cause: " + e.getMessage());
        }
        return accessTokenFromApi;
    }
}
