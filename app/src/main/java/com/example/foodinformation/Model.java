package com.example.foodinformation;

import java.net.URL;
import java.util.ArrayList;

public class Model {
    ArrayList<categories> categories;

    public ArrayList<categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<categories> categories) {
        this.categories = categories;
    }

    public class categories {
        String idCategory;
        String strCategory;
        String strCategoryThumb;
        String strCategoryDescription;

        public categories(String idCategory, String strCategory, String strCategoryThumb, String strCategoryDescription) {
            this.idCategory = idCategory;
            this.strCategory = strCategory;
            this.strCategoryThumb = strCategoryThumb;
            this.strCategoryDescription = strCategoryDescription;
        }

        public String getIdCategory() {
            return idCategory;
        }

        public void setIdCategory(String idCategory) {
            this.idCategory = idCategory;
        }

        public String getStrCategory() {
            return strCategory;
        }

        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }

        public String getStrCategoryThumb() {
            return strCategoryThumb;
        }

        public void setStrCategoryThumb(String strCategoryThumb) {
            this.strCategoryThumb = strCategoryThumb;
        }

        public String getStrCategoryDescription() {
            return strCategoryDescription;
        }

        public void setStrCategoryDescription(String strCategoryDescription) {
            this.strCategoryDescription = strCategoryDescription;
        }
    }
}
