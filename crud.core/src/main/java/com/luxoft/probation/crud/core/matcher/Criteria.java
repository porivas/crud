package com.luxoft.probation.crud.core.matcher;

/**
 * Criteria interface for matching implementation
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
public interface Criteria<T> {
    boolean matches(T bean);
}
