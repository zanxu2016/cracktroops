package info.luckydog.algorithm.topics;

import org.junit.Test;

/**
 * LinkedCycle
 * 1、判断链表是否有环？
 * 快慢指针，追及相遇
 * 2、若有环，环的长度是多少？
 * 快慢指针，首次相遇和第二次相遇之间的距离
 * 3、环的入口是哪个位置？
 * 快慢指针，首次相遇，再将慢指针退回头节点，快指针转为慢指针，这样往前走，首次相遇的节点。
 * 画图理思路
 * 假设：Head为头节点，Join为入环节点，Pos为首次相遇点，Head到Join的距离为LenA，Join到Pos的距离为x，环长为R，则
 * 已知：慢指针走的时间和快指针走的时间相同，快指针的速度是慢指针的2倍，
 * 则首次相遇时，快指针走过的距离等于慢指针走过距离的2倍，关系如下：
 * 2*(LenA + x) = LenA + x + n*R
 * => LenA + x = n*R
 * => LenA = n*R - x = (n-1)*R + (R - x)
 * 由上面的推导公式可得：
 * 将两个慢指针A、B分别放在Head和Pos点，同时往前走，A走到Join的时候，B肯定也会走到Join。
 *
 * @author eric
 * @since 2019/7/1
 */
public class LinkCycle {

    @Test
    public void linkCycleTest() {
        // 初始化测试数据
        Link<Integer> link = new Link<>();
        link.add(2);
        link.add(5);
        link.add(3);
        link.add(6);
        link.add(7);
        link.add(4);
        link.linkTo(2);

        System.out.println("link size: " + link.size);

        link.foreach();

        boolean hasCycle = hasCycle(link);

        System.out.println("has cycle? " + hasCycle);

        if (hasCycle) {
            System.out.println("link cycle length: " + linkCycleLength(link));
            System.out.println("link cycle entrance index: " + linkCycleEntranceIndex(link));
        }

    }

    /**
     * 判断链表是否有环
     *
     * @param link
     * @return
     */
    public boolean hasCycle(Link link) {
        boolean hasCycle = false;
        if (link.first == null) {
            return false;
        }
        Link.Node p1 = link.first;// 慢指针
        Link.Node p2 = link.first;// 快指针
        // 快指针在前，若无环，则快指针先到达尾节点；若有环，则快指针先进入环内，追及问题，快指针肯定能与慢指针相遇。
        while (p2 != null || p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {// 快慢指针相遇，说明有环
                hasCycle = true;
                break;
            }
        }

        return hasCycle;
    }

    /**
     * 有环链表的环长
     *
     * @param link
     * @return
     */
    public int linkCycleLength(Link link) {

        int cycleLength = 0;
        if (link.first == null) {
            return cycleLength;
        }
        Link.Node p1 = link.first.next, p2 = link.first.next.next;

        // 通过快慢指针，找到首次相遇点，作为标记
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        // 快慢指针继续往前走，直到下一次相遇，走过的步数就是环长
        p1 = p1.next;
        p2 = p2.next.next;
        cycleLength++;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next.next;
            cycleLength++;
        }

        return cycleLength;
    }

    /**
     * 有环链表入环下标
     *
     * @param link
     * @return
     */
    public int linkCycleEntranceIndex(Link link) {

        Link.Node p1 = link.first, p2 = link.first;
        p1 = p1.next;// 慢指针
        p2 = p2.next.next;// 快指针
        // 通过快慢指针，找到首次相遇点，作为标记
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        // 将慢指针退回到头节点，快指针定位在首次相遇点，快指针变为慢指针，两个慢指针再次相遇的点，即为入环节点
        p1 = link.first;
        int index = 0;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            index++;
        }
        System.out.println("entrance element: " + link.node(index).data);
        return index;
    }

    /**
     * 单链表
     */
    class Link<E> {
        private int size;// 链表长度
        private Node<E> first;// 链表头节点
        private Node<E> last;// 链表尾节点

        public Link() {
        }

        /**
         * 获取下标对应的元素
         *
         * @param index
         * @return
         */
        public E get(int index) {
            checkRange(index);
            if (index == 0) {
                return first.data;
            }
            if (index == size - 1) {
                return last.data;
            }
            return node(index).data;
        }

        /**
         * 遍历链表元素
         */
        public void foreach() {
            if (this.first == null) {
                System.out.println("null");
            }
            int index = 0;
            for (Node<E> x = first; x != null; x = x.next) {
                System.out.print(x.data + " ");
                index++;
                if (index >= size) {
                    System.out.println();
                    break;
                }
            }
        }

        /**
         * 添加元素（默认添加至链表尾部）
         *
         * @param e
         * @return
         */
        public boolean add(E e) {
            Node<E> newNode = new Node<>(e, null);
            Node<E> l = last;
            if (l == null) {// 空链表
                first = newNode;
                last = newNode;
                size++;
                return true;
            }

            l.next = newNode;
            last = newNode;
            size++;
            return true;
        }

        /**
         * 将尾节点链接到指定下标的元素
         *
         * @param index
         * @return
         */
        public boolean linkTo(int index) {
            checkRange(index);
            if (index == size - 1) {
                return false;
            }

            if (last == null) {
                return false;
            }
            last.next = node(index);
            return true;
        }

        /**
         * 获取指定下标的节点
         *
         * @param index
         * @return
         */
        private Node<E> node(int index) {
            checkRange(index);

            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }

            return x;
        }

        /**
         * 检查下标
         *
         * @param index
         */
        private void checkRange(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException(outOfRangeMsg(index));
            }
        }

        /**
         * 下标越界提示信息
         *
         * @param index
         * @return
         */
        private String outOfRangeMsg(int index) {
            return "Index: " + index + "; Size: " + size;
        }


        /**
         * 节点类
         *
         * @param <E>
         */
        private class Node<E> {
            private E data;// 元素
            private Node<E> next;// 下一节点

            public Node(E data, Node<E> next) {
                this.data = data;
                this.next = next;
            }

            public E getData() {
                return data;
            }

            public void setData(E data) {
                this.data = data;
            }

            public Node<E> getNext() {
                return next;
            }

            public void setNext(Node<E> next) {
                this.next = next;
            }
        }
    }
}
