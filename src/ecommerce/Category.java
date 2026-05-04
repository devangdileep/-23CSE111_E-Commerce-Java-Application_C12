package ecommerce;
import java.util.ArrayList;
import java.util.List;
public class Category {
    private Long categoryId;
    private String name;
    private Category parentCategory;
    private List<Category> subCategories;
    public Category(Long id, String name, Category parent) {
        this.categoryId = id;
        this.name = name;
        this.parentCategory = parent;
        this.subCategories = new ArrayList<>();
        if (parent != null) {
            parent.addSubCategory(this);
        }
    }
    public void addSubCategory(Category category) {
        subCategories.add(category);
    }
    public String getFullPath() {
        if (parentCategory == null) return name;
        return parentCategory.getFullPath() + " > " + name;
    }
    public boolean isRootCategory() {
        return parentCategory == null;
    }
    public String getName() {
        return name;
    }
}
