package edu.polytech.repo_ihm.activities.ideeRecettes;

public class Ingredient {

    private String name;
    private String french;

    public Ingredient(String name, String french) {
        this.name = name;
        this.french = french;
    }

    public String getName() {
        return name;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public void setName(String name) {
        this.name = name;
    }
}
