package emazon.stock.ports.application.utils;

public class ArticleValidationConstants {

    public static final String CATEGORY_NOT_FOUND = "Category not found.";
    public static final String BRAND_NOT_FOUND = "Brand not found.";
    public static final String DUPLICATE_CATEGORY = "Duplicate categories are not allowed.";

    public static final int ARTICLE_CATEGORY_MIN_SIZE = 1;
    public static final int ARTICLE_CATEGORY_MAX_SIZE = 3;
    public static final int ARTICLE_QUANTITY_MIN_VALUE = 0;
    public static final int ARTICLE_QUANTITY_MAX_VALUE = 1000;
    //public static final String ARTICLE_ALREADY_EXISTS = "Article already exists.";
    public static final String ARTICLE_NAME_REQUIRED = "Article name is required.";
    public static final String ARTICLE_DESCRIPTION_REQUIRED = "Article description is required.";
    public static final String ARTICLE_QUANTITY_REQUIRED = "Article quantity is required.";
    public static final String ARTICLE_QUANTITY_MIN = "The article minimum quantity must be " + ARTICLE_QUANTITY_MIN_VALUE;
    public static final String ARTICLE_QUANTITY_MAX = "The article maximum quantity must be less than or equal to " + ARTICLE_QUANTITY_MAX_VALUE;
    public static final String ARTICLE_PRICE_REQUIRED = "Article price is required.";
    public static final String ARTICLE_PRICE_POSITIVE = "Article price must be greater than zero.";
    public static final String ARTICLE_BRAND_REQUIRED = "Every product needs a brand";
    public static final String ARTICLE_BRAND_ID_POSITIVE= "Brand id must be greater than 0";
    public static final String ARTICLE_CATEGORY_REQUIRED= "Every article needs at least one category";
    public static final String ARTICLE_CATEGORY_SIZE = "A article must have between " + ARTICLE_CATEGORY_MIN_SIZE + " and " + ARTICLE_CATEGORY_MAX_SIZE + " categories";

    public ArticleValidationConstants() {
    }
}
