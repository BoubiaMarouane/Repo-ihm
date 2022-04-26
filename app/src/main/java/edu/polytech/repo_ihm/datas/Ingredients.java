package edu.polytech.repo_ihm.datas;

public enum Ingredients {

    PASTA(20420,"Pâtes"),
    CHEESE(1041009,"Fromage"),
    CHOCOLATE(19081,"Chocolat"),
    TOMATOES(11529,"Tomates"),
    BEEF(23572,"Boeuf"),
    GARLIC(11215,"Ail"),
    APPLES(1079003,"Pommes")
    ;


    private int id;
    private String french;
    Ingredients(int id, String french) {
        this.id = id;
        this.french = french;
    }

    public int id() {return id;}
    public String french() {return french;}
}
