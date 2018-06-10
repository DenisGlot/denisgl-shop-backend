package com.denisgl.filter;

import com.denisgl.dto.ICategory;

public class ProductFilter extends PagingFilter {

    private ICategory category;
    private Boolean active;


    public ICategory getCategory() {
        return category;
    }

    public void setCategory(ICategory category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
