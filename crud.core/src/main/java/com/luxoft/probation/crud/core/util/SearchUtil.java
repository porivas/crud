package com.luxoft.probation.crud.core.util;

import com.luxoft.probation.crud.core.matcher.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for finding element from collection
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
public final class SearchUtil {

    private SearchUtil() {
    }

    public static <T> List<T> search(List<T> lstBeans, Criteria<T> criteria) {
        List<T> lstMatch = new ArrayList<>(lstBeans.size());
        for (T bean : lstBeans) {
            if (criteria.matches(bean)) {
                lstMatch.add(bean);
            }
        }

        return lstMatch;
    }
}
