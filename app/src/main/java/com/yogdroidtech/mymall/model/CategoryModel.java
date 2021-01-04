package com.yogdroidtech.mymall.model;

public class CategoryModel {
    private  String iconUrl;
    private String categoryName;

    public CategoryModel(String iconUrl, String categoryName) {
        this.iconUrl = iconUrl;
        this.categoryName = categoryName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
