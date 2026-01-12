/*
 * File: Builder.java
 * Path: ./etc/
 *
 * Date: 2026-01-12
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Builder template
 */

package etc;

import java.time.LocalDate;

class Car {
    private String idBrand;
    private String brand;
    private String brandCyrillic;
    private int fame;
    private String country;
    private int startYearOfBrand;
    private int endYearOfBrand;
    private String idModel;
    private String model;
    private String modelCyrillic;
    private String modelClass;
    private int startYearOfModel;
    private int endYearOfModel;

    public Car() {
        idBrand = "";
        brand = "";
        brandCyrillic = "";
        fame = 0;
        country = "";
        idModel = "";
        model = "";
        modelCyrillic = "";
        modelClass = "";
    }

    public Car(String brand, String model) throws NullPointerException {
        this();
        if (brand == null || model == null) throw new NullPointerException("class Car(" + brand + ", " + model +")");
        this.brand = brand;
        this.model = model;
    }

    public String getIdBrand() {
        return idBrand;
    }

    public String getBrand() {
        return brand;
    }

    public String getBrandCyrillic() {
        return brandCyrillic;
    }

    public int getFame() {
        return fame;
    }

    public String getCountry() {
        return country;
    }

    public int getStartYearOfBrand() {
        return startYearOfBrand;
    }

    public int getEndYearOfBrand() {
        return endYearOfBrand;
    }

    public String getIdModel() {
        return idModel;
    }

    public String getModel() {
        return model;
    }

    public String getModelCyrillic() {
        return modelCyrillic;
    }

    public String getModelClass() {
        return modelClass;
    }

    public int getStartYearOfModel() {
        return startYearOfModel;
    }

    public int getEndYearOfModel() {
        return endYearOfModel;
    }

    public Car setIdBrand(String idBrand) throws NullPointerException {
        if (idBrand == null) throw new NullPointerException("class Car: setIdMark(null)");
        this.idBrand = idBrand;
        return this;
    }

    public Car setBrand(String brand) throws NullPointerException {
        if (brand == null) throw new NullPointerException("class Car: setMark(null)");
        this.brand = brand;
        return this;
    }

    public Car setBrandCyrillic(String brandCyrillic) throws NullPointerException {
        if (brandCyrillic == null) throw new NullPointerException("class Car: setMarkCyrillic(null)");
        this.brandCyrillic = brandCyrillic;
        return this;
    }

    public Car setFame(int fame) {
        if (fame != 0 && fame != 1 ) return this;
        this.fame = fame;
        return this;
    }

    public Car setCountry(String country) throws  NullPointerException {
        if (country == null) throw new NullPointerException("class Car: setCountry(null)");
        this.country = country;
        return this;
    }

    public Car setStartYearOfBrand(int startYearOfBrand) {
        if (startYearOfBrand < 1799 || startYearOfBrand > LocalDate.now().getYear()) return this;
        this.startYearOfBrand = startYearOfBrand;
        return this;
    }

    public Car setEndYearOfBrand(int endYearOfBrand) {
        if (endYearOfBrand < 1799 || endYearOfBrand > LocalDate.now().getYear()) return this;
        this.endYearOfBrand = endYearOfBrand;
        return this;
    }

    public Car setIdModel(String idModel) throws NullPointerException {
        if (idModel == null) throw new NullPointerException("class Car: setIdModel(null)");
        this.idModel = idModel;
        return this;
    }

    public Car setModel(String model) throws NullPointerException {
        if (model == null) throw new NullPointerException("class Car: setModel(null)");
        this.model = model;
        return this;
    }

    public Car setModelCyrillic(String modelCyrillic) throws NullPointerException {
        if (modelCyrillic == null) throw new NullPointerException("class Car: setModelCyrillic(null)");
        this.modelCyrillic = modelCyrillic;
        return this;
    }

    public Car setModelClass(String modelClass) throws NullPointerException {
        if (modelClass == null) throw new NullPointerException("class Car: setModelClass(null)");
        this.modelClass = modelClass;
        return this;
    }

    public Car setStartYearOfModel(int startYearOfModel) {
        if (startYearOfModel < 1799 || startYearOfModel > LocalDate.now().getYear()) return this;
        this.startYearOfModel = startYearOfModel;
        return this;
    }

    public Car setEndYearOfModel(int endYearOfModel) {
        if (endYearOfModel < 1799 || endYearOfModel > LocalDate.now().getYear()) return this;
        this.endYearOfModel = endYearOfModel;
        return this;
    }

    @Override
    public String toString() {
        String fameString = (fame == 0) ? "False" : "True";
        return "ID Brand:\t\t\t" + idBrand + "\n" +
                "Brand:\t\t\t\t" + brand + "\n" +
                "Brand (Cyrillic):\t" + brandCyrillic + "\n" +
                "Fame:\t\t\t\t" + fameString + "\n" +
                "Country:\t\t\t" + country + "\n" +
                "Brand from year:\t" + startYearOfBrand + "\n" +
                "Brand to year:\t\t" + endYearOfBrand + "\n" +
                "ID Model:\t\t\t" + idModel + "\n" +
                "Model:\t\t\t\t" + model + "\n" +
                "Model (Cyrillic):\t" + modelCyrillic + "\n" +
                "Model class:\t\t" + modelClass + "\n" +
                "Model from year:\t" + startYearOfModel + "\n" +
                "Model end year:\t\t" + endYearOfModel + "\n";
    }
}

public class Builder {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    public static void main(String[] args) {
        String carRecord = "ACURA,Acura,Акура,0,Япония,1986,2025,ACURA_CDX,CDX,сдх,D,2016,2021";
        var carField = carRecord.split(",");

        Car yourCar;

        yourCar = new Car(carField[ 1 ], carField[ 8 ])
                .setIdBrand(carField[ 0 ])
                .setBrandCyrillic(carField[ 2 ])
                .setFame(Integer.parseInt(carField[ 3 ]))
                .setCountry(carField[ 4 ])
                .setStartYearOfBrand(Integer.parseInt(carField[ 5 ]))
                .setEndYearOfBrand(Integer.parseInt(carField[ 6 ]))
                .setIdModel(carField[ 7 ])
                .setModel(carField[ 8 ])
                .setModelCyrillic(carField[ 9 ])
                .setModelClass(carField[ 10 ])
                .setStartYearOfModel(Integer.parseInt(carField[ 11 ]))
                .setEndYearOfModel(Integer.parseInt(carField[ 12 ]));

        System.out.println(yourCar);
    }
}
