package ru.geekbrains;

public class Page {

    String root;
    String ruLocale;

    public Page(String root, String ruLocale) {
        this.root = root;
        this.ruLocale = ruLocale;
    }

    public String getRoot() {
        return root;
    }

    public String getRuLocale() {
        return ruLocale;
    }


}
