package info.luckydog.javacore.object;

public class FinalizeDemo {
    // 需要状态判断的对象
    public static FinalizeDemo Hook = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();// 只会执行一次
        System.out.println("执行了 finalize 方法");
        FinalizeDemo.Hook = this;
    }

    public static void main(String[] args) throws InterruptedException {
        Hook = new FinalizeDemo();
        // 卸载对象，第一次执行 finalize()
        Hook = null;
        System.gc();
        Thread.sleep(500); // 等待 finalize() 执行
        if (Hook != null) {
            System.out.println("存活状态");
        } else {
            System.out.println("死亡状态");
        }
        // 卸载对象，与上一次代码完全相同
        Hook = null;
        System.gc();
        Thread.sleep(500); // 等待 finalize() 执行
        if (Hook != null) {
            System.out.println("存活状态");
        } else {
            System.out.println("死亡状态");
        }
    }
}