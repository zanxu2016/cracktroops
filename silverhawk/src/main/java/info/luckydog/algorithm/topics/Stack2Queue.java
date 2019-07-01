package info.luckydog.algorithm.topics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Stack2Queue
 * 使用栈实现队列
 * 队列FIFO，栈是FILO，
 * 使用两个栈，可以实现队列的功能：
 * 一个栈用于存放入队元素，另一个用于存放出队元素，
 * 当push时，将元素压入入队栈；
 * 当pop时，检查出队栈是否为空，
 * 若为空，则检查入栈队中有无元素。若无，则抛异常；若有，则将入队栈中的所有元素依次压入出队栈，再将出队栈的栈顶元素弹出；
 * 若不为空，则将出栈队的栈顶元素弹出。
 *
 * @author eric
 * @since 2019/7/1
 */
public class Stack2Queue {

    @Test
    public void stack2Queue() {

        Queue<Integer> queue = new Queue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);

        System.out.println(queue.pop(5));


        queue.push(7);
        queue.push(8);
        queue.push(9);

        System.out.println(queue.pop(5));

    }

    class Queue<E> {
        private Stack<E> stackPush = new Stack<>();
        private Stack<E> stackPop = new Stack<>();

        public E push(E e) {
            return stackPush.push(e);
        }

        public E pop() {
            if (stackPop.empty()) {
                if (stackPush.isEmpty()) {
                    System.out.println("stackPop is empty. So is stackPush.");
                    throw new EmptyStackException();
                }
                System.out.println("stackPop is empty. Push stackPush into stackPop...");
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public List<E> pop(int n) {
            List<E> eleList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                try {
                    eleList.add(pop());
                } catch (EmptyStackException e) {
                    System.err.println("\nEmptyStackException occurs at pop(" + n + ")");
                    break;
                }

            }
            return eleList;
        }
    }
}
