package edu.polytech.repo_ihm.datas;

public class Recipe {

    String img_link;
    String recipe_link;
    String title;

    public Recipe() {}

    public Recipe(String img_link, String recipe_link, String title) {
        this.img_link = img_link;
        this.recipe_link = recipe_link;
        this.title = title;
    }

    public String getImg_link() {
        return img_link;
    }

    public String getRecipe_link() {
        return recipe_link;

    }

    public String getTitle() {
        return title;
    }
}
