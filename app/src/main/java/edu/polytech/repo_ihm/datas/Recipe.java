package edu.polytech.repo_ihm.datas;

public class Recipe {

    String image;
    String link;
    String title;

    public Recipe() {}

    public Recipe(String image, String link, String title) {
        this.image = image;
        this.link = link;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }
}
