package edu.polytech.repo_ihm.datas;

public enum Ingredients {

    PASTA(20420,"Pâtes", 1.0),
    CHEESE(1041009,"Fromage", 0.5),
    CHOCOLATE(19081,"Chocolat", 0.25),
    TOMATOES(11529,"Tomates", 0.20),
    BEEF(23572,"Boeuf", 0.45),
    GARLIC(11215,"Ail", 0.10),
    APPLES(1079003,"Pommes", 0.20)
    ;


    private int id;
    private String french;
    private double coef;
    Ingredients(int id, String french, double coef) {
        this.id = id;
        this.french = french;
        this.coef = coef;
    }

    public int id() {return id;}
    public String french() {return french;}
    public double coef() {return coef;}
}
