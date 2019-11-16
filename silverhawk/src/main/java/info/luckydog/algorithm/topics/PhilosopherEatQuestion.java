package info.luckydog.algorithm.topics;

import java.util.Random;

/**
 * PhilosopherEatQuestion
 *
 * @author eric
 * @since 2019/11/16
 */
public class PhilosopherEatQuestion {
    public static void main(String[] args) {
        {
            /* 产生筷子类的实例 chopsticks */
            Chopsticks chopsticks = new Chopsticks();
            /* 用筷子类的实例作为参数， 产生五个哲学家线程并启动 */
            /* 五个哲学家线程的名称为 1~5 */
            new Philosopher("1", chopsticks).start();
            new Philosopher("2", chopsticks).start();
            new Philosopher("3", chopsticks).start();
            new Philosopher("4", chopsticks).start();
            new Philosopher("5", chopsticks).start();
        }
    }
}

class Chopsticks {
    // 用 used[1]至 used[5]五个数组元素分别代表编号 1 至 5 的五支筷子的状态
    // false 表示未被占用，true 表示已经被占用。 used[0]元素在本程序中未使用
    private boolean[] used = {true, false, false, false, false, false};

    // 拿起筷子的操作
    public synchronized void takeChopstick() {
        /* 取得该线程的名称并转化为整型,用此整数来判断该哲学家应该用哪两支筷子 */
        /* i 为左手边筷子编号，j 为右手边筷子编号 */
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);
        /*
         * 1~4 号哲学家使用的筷子编号是 i 和 i+1,5 号哲学家使用 的筷子编号是 5 和 1
         */
        int j = i == 5 ? 1 : i + 1;
        /* 将两边筷子的编号按奇偶顺序赋给 odd,even 两个变量 */
        int odd, even;
        if (i % 2 == 0) {
            even = i;
            odd = j;
        } else {
            odd = i;
            even = j;
        }
        /* 首先竞争奇数号筷子 */
        while (used[odd]) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        used[odd] = true;
        /* 然后竞争偶数号筷子 */
        while (used[even]) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        used[even] = true;
    }

    /* 放下筷子的操作 */
    public synchronized void putChopstick() {
        String name = Thread.currentThread().getName();
        int i = Integer.parseInt(name);
        int j = i == 5 ? 1 : i + 1;
        /*
         * 将相应筷子的标志置为 fasle 表示使用完毕， 并且通知其他等待线程来竞争
         */
        used[i] = false;
        used[j] = false;
        notifyAll();
    }
}

class Philosopher extends Thread {
    private Random rand = new Random();//用来进行随机延时
    private Chopsticks chopsticks;

    public Philosopher(String name, Chopsticks chopsticks) {
        /* 在构造实例时将 name 参数传给 Thread 的构造函数作为线程的名称 */
        super(name);
        /* 所有哲学家线程共享同一个筷子类的实例 */
        this.chopsticks = chopsticks;
    }

    public void run() {
        /* 交替地思考、拿起筷子、进餐、放下筷子 */
        while (true) {
            thinking();
            chopsticks.takeChopstick();
            eating();
            chopsticks.putChopstick();
        }
    }

    public void thinking() {
        /* 显示字符串输出正在思考的哲学家，用线程休眠若干秒钟来模拟思考时间 */
        System.out.println("Philosopher " + Thread.currentThread().getName() + " is thinking.");
        try {
            Thread.sleep(1000 * rand.nextInt(5));
        } catch (InterruptedException e) {
        }
    }

    public void eating() {
        /* 显示字符串输出正在进餐的哲学家，并用线程休眠 若干 秒钟来模拟进餐时间 */
        System.out.println("Philosopher " + Thread.currentThread().getName() + " is eating.");
        try {
            Thread.sleep(1000 * rand.nextInt(5));
        } catch (InterruptedException e) {
        }
    }
}

