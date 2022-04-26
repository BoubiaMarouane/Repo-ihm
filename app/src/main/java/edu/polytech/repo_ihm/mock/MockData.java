package edu.polytech.repo_ihm.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Product;

public class MockData {

    public static List<String> mails = Arrays.asList("user@gmail.com");

    public static List<Product> products = Arrays.asList(
            new Product("riz", 1, "10/2/2020"),
            new Product("mayo", 1, "10/2/2020"),
            new Product("riz", 1, "10/2/2020"),
            new Product("mayo", 1, "10/2/2020")
    );

}