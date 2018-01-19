package com.company.problem2BookShop;

public class Book {
    private String author;
    private String title;
    private double price;

    public Book(String author, String title, double price) {
        this.setAuthor(author);
        this.setTitle(title);
        this.setPrice(price);
    }

    private void setTitle(String title) {
        if (title.length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }
        this.title = title;
    }

    private void setAuthor(String author) {
        if (validateAuthorName(author)) {
            throw new IllegalArgumentException("Author not valid!");
        }
        this.author = author;
    }

    protected void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price not valid!");
        }
        this.price = price;
    }

    private boolean validateAuthorName(String author) {
        String[] fullName = author.split("\\s+");
        String secondName = fullName[1];
        String firstLetterOnSecondName = String.valueOf(secondName.charAt(0));

        try {
            Integer.parseInt(firstLetterOnSecondName);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format("%s from %s Price: %.2f", this.title, this.author, this.price);
    }
}
