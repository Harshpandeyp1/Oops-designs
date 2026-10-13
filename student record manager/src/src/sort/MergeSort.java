package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <T> List<T> sort(List<T> list, Comparator<T> cmp) {
        if (list.size() <= 1) {
            return new ArrayList<>(list);          // base case
        }
        int mid = list.size() / 2;
        List<T> left = sort(list.subList(0, mid), cmp);
        List<T> right = sort(list.subList(mid, list.size()), cmp);
        return merge(left, right, cmp);
    }

    private static <T> List<T> merge(List<T> left, List<T> right, Comparator<T> cmp) {
        List<T> result = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (cmp.compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size())  result.add(left.get(i++));   // leftovers
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }
}