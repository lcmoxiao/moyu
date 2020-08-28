package com.jlp.utils.devidePage;

import java.util.Comparator;
import java.util.List;

public interface PageInfo<V> {

    void sortContent(Comparator<V> comparator);

    List<V> nowPage();

    List<V> nextPage();

    List<V> lastPage();

    void updateContent(List<V> content);

}
