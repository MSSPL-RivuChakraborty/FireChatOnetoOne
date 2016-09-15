package com.massoftind.rnd.firechatonetoone.interfaces;

/**
 * Created by Developer on 4/27/2015.
 */
public interface InterfaceFeedCallback {
    void doPagination(int page_no);
    void delete(int position);
    void reportInappropriate(int position);
}
