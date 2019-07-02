package info.luckydog.algorithm.topics;

import org.junit.Test;

import java.util.Stack;

/**
 * LeastStack
 * 实现一个最小栈
 * 要求获取栈中最小值的时间复杂度为O(1)
 * 使用两个栈实现最小栈：
 * 1、一个栈A用来存放栈元素，另一个栈B用来存放栈的最小值；
 * 2、每次push时，栈A push；若栈B为空，则栈B push，若栈B不为空，则比较peek到的元素和push的元素，若push的元素小，则栈B push；
 * 3、每次pop时，若栈A为空，则返回null，若栈A不为空，则栈A pop；比较栈B peek到的元素与pop的元素，若相等，则栈B pop；
 * 4、栈A的最小值，即为栈B peek到的值。若栈B为空，则无最小值（此时栈A也为空）。
 *
 * @author eric
 * @since 2019/7/2
 */
public class LeastStack {

    @Test
    public void leastStackTest() {
        LStack<Integer> lStack = new LStack<>();
        lStack.push(5);
        lStack.push(6);
        lStack.push(3);
        lStack.push(2);
        lStack.push(8);
        lStack.push(7);
        lStack.push(4);
        lStack.push(1);

        System.out.println("最小值为：" + lStack.getLeast());
        lStack.pop();
        System.out.println("最小值为：" + lStack.getLeast());
        lStack.pop();
        System.out.println("最小值为：" + lStack.getLeast());
        lStack.pop();
        System.out.println("最小值为：" + lStack.getLeast());
        lStack.pop();
    }

}

class LStack<E extends Comparable> {
    private Stack<E> pushStack = new Stack<>();
    private Stack<E> minStack = new Stack<>();

    public E push(E e) {
        pushStack.push(e);
        if (minStack.isEmpty()) {
            minStack.push(e);
        } else {
            if (e.compareTo(minStack.peek()) < 0) {
                minStack.push(e);
            }
        }
        return e;
    }

    public E pop() {
        if (pushStack.empty()) {
            return null;
        }

        E e = pushStack.pop();

        if (e.compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }

        return e;
    }

    public E getLeast() {
        if (minStack.empty()) {
            return null;
        }

        return minStack.peek();
    }
}
