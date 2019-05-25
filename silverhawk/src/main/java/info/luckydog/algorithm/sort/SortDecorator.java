package info.luckydog.algorithm.sort;

/**
 * 排序算法装饰器
 *
 * @author eric
 * @since 2019/5/25
 */
public class SortDecorator extends AbstractSort {

    private AbstractSort sort;

    public SortDecorator(AbstractSort sort) {
        this.sort = sort;
    }

    @Override
    public void sort(Comparable[] a) {

        System.out.println(sort.getClass().getSimpleName() + "...");

        sort.sort(a);

        System.out.println("exchangeCount : " + sort.getExchangeCount());
        System.out.println("compareCount : " + sort.getCompareCount());
        sort.show(a);
        System.out.println("Is sorted? " + sort.isSorted(a));
        System.out.println("================================");
    }
}
