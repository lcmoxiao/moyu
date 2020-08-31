package com.jlp.utils.devidePage;

import java.util.Comparator;
import java.util.List;

public class PageInfoImpl<V> implements PageInfo<V> {

    private List<V> content;
    private int now;
    private int size;

    public PageInfoImpl(List<V> content, int now, int size) {
        this.content = content;
        this.now = now;
        this.size = size;
    }

    @Override
    public void sortContent(Comparator<V> comparator) {
        content.sort(comparator);
    }

    @Override
    public List<V> nowPage() {
        return preventGetNullContentOfTail(now, now + size);
    }

    @Override
    public List<V> nextPage() {
        now += size;
        List<V> res = preventGetNullContentOfTail(now, now + size);
        if (res == null) now -= size;
        return res;
    }

    @Override
    public List<V> lastPage() {
        now -= size;
        List<V> res = preventGetNullContentOfTail(now, now + size);
        if (res == null) now += size;
        return res;
    }

    private List<V> preventGetNullContentOfTail(int start, int end) {
        //当前页值小于最大页值
        if (start >= end) return null;
        if (start < content.size())
            //当前内容最大值小于最大页值
            if (end < content.size())
                return content.subList(start, end);
            else if (start < 0)
                return null;
            else
                return content.subList(start, content.size());
        else return null;
    }

    @Override
    public void updateContent(List<V> content) {
        this.content = content;
    }

}
