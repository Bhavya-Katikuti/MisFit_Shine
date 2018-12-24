package com.example.mechsrit.misfit_shine.modelclass;

public class Model {
    private String id;
    private String brand;
    private String name;
    private String price;
    private String imageLink;
    private String productLink;
    private String websiteLink;
    private String description;
    private String rating;
    private String category;
    private String productType;

  public Model(String  id, String brand, String name, String price, String imageLink,
              String productLink, String websiteLink, String description, String  rating,
               String category, String productType) {
      this.id = id;
      this.brand = brand;
       this.name = name;
       this.price = price;
       this.imageLink = imageLink;
      this.productLink = productLink;
      this.websiteLink = websiteLink;
        this.description = description;
       this.rating = rating;
        this.category = category;
        this.productType = productType;
   }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}



