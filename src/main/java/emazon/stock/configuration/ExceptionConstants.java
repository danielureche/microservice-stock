package emazon.stock.configuration;

public class ExceptionConstants {
    public static final String CATEGORY_NAME_REQUIRED = "Category name is required.";
    public static final String CATEGORY_NAME_LENGTH = "Category name must be less than or equal to 50 characters.";
    public static final String CATEGORY_DESCRIPTION_REQUIRED = "Category description is required.";
    public static final String CATEGORY_DESCRIPTION_LENGTH = "Category description must be less than or equal to 90 characters.";
    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists.";

    public static final String BRAND_NAME_REQUIRED = "Brand name is required.";
    public static final String BRAND_NAME_LENGTH = "Brand name must be less than or equal to 50 characters.";
    public static final String BRAND_DESCRIPTION_REQUIRED = "Brand description is required.";
    public static final String BRAND_DESCRIPTION_LENGTH = "Brand description must be less than or equal to 120 characters.";
    public static final String BRAND_ALREADY_EXISTS = "Brand already exists.";

    public static final String CATEGORY_NOT_FOUND = "Category not found.";
    public static final String BRAND_NOT_FOUND = "Brand not found.";
    public static final String DUPLICATE_CATEGORY = "Duplicate categories are not allowed.";

    public static final String ARTICLE_NAME_REQUIRED = "Article name is required.";
    public static final String ARTICLE_DESCRIPTION_REQUIRED = "Article description is required.";
    public static final String ARTICLE_QUANTITY_REQUIRED = "Article quantity is required.";
    public static final String ARTICLE_ALREADY_EXISTS = "Article already exists.";


    public ExceptionConstants() {
    }
}
