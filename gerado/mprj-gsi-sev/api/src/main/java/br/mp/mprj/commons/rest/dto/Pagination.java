/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package  br.mp.mprj.commons.rest.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Wraps pageable requests Parameters for creation of an Spring Pageable parameters
 * TODO: Staging class!
 */
public class Pagination {

    private static final long serialVersionUID = -4541509938956089562L;

    private int page = 1;  // default values
    private int size = 20; // default values
    private String sort;

    public Pagination(){
    }

    public Pagination(int page, int size){
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }

    public Pageable getPageable() {
        // Page is -1 becouse Spring pagination deal with Pagination as an array representation (0..size-1)
        // And presentation pagination starts 'with page 1'
        if (sort == null || "".equals(sort.trim())){
            return new PageRequest(((page < 1 ? 1 : page) - 1), size);
        }
        return new PageRequest(((page < 1 ? 1 : page) - 1), size, new Sort(sort));
    }


}
