/*
 * File: Builder.java
 * Path: ./etc/
 *
 * Date: 2026-01-21
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Builder template
 */

package etc;

class Car {
    String idBrand = "";
    String brand = "";
    String brandCyrillic = "";
    int fame = 0;
    String country = "";
    int startYearOfBrand = -1;
    int endYearOfBrand = -1;
    String idModel = "";
    String model = "";
    String modelCyrillic = "";
    String modelClass = "";
    int startYearOfModel = -1;
    int endYearOfModel = -1;
}

abstract class  CarBuilderConcept {
    protected Car targetCar;

    public Car getCar() {
        return targetCar;
    }

    public void createNew() {
        targetCar = new Car();
    }

    public abstract void setBrand();
    public abstract void setFame();
    public abstract void setCountry();
    public abstract void setModel();
    public abstract void setModelClass();
}

class BuildCarAcuraCDX extends CarBuilderConcept {
//"ACURA,Acura,Акура,0,Япония,1986,2026,ACURA_CDX,CDX,сдх,D,2016,2021"
    @Override
    public void setBrand() {
        targetCar.idBrand = "ACURA";
        targetCar.brand = "Acura";
        targetCar.brandCyrillic = "Акура";
        targetCar.startYearOfBrand = 1986;
        targetCar.endYearOfModel = 2026;
    }

    @Override
    public void setFame() {
        targetCar.fame = 0;
    }

    @Override
    public void setCountry() {
        targetCar.country = "Япония";
    }

    @Override
    public void setModel() {
        targetCar.idModel = "ACURA_CDX";
        targetCar.model = "CDX";
        targetCar.modelCyrillic = "сдх";
        targetCar.startYearOfModel = 2016;
        targetCar.endYearOfModel = 2021;
    }

    @Override
    public void setModelClass() {
        targetCar.modelClass = "D";
    }
}

class BuildCarAbarth124Spider extends CarBuilderConcept {
// ABARTH,Abarth,Абарт,0,Италия,2008,2026,ABARTH_124_SPIDER,124 Spider,124 спайдер,S,2016,2020

    @Override
    public void setBrand() {
        targetCar.idModel = "ABARTH";
        targetCar.model = "Abarth";
        targetCar.modelCyrillic = "Абарт";
        targetCar.startYearOfBrand = 2008;
        targetCar.endYearOfBrand = 2026;
    }

    @Override
    public void setFame() {
        targetCar.fame = 0;
    }

    @Override
    public void setCountry() {
        targetCar.country = "Италия";
    }

    @Override
    public void setModel() {
        targetCar.idModel = "ABARTH_124_SPIDER";
        targetCar.model = "124 Spider";
        targetCar.modelCyrillic = "124 спайдер";
        targetCar.startYearOfModel = 2016;
        targetCar.endYearOfModel = 2020;
    }

    @Override
    public void setModelClass() {
        targetCar.modelClass = "S";
    }
}

class BuildCarAudiA4 extends CarBuilderConcept {
// AUDI,Audi,Ауди,1,Германия,1927,2026,AUDI_A4,A4,А4,D,1994,2026

    @Override
    public void setBrand() {
        targetCar.idBrand = "AUDI";
        targetCar.brand = "Audi";
        targetCar.brandCyrillic = "Ауди";
        targetCar.startYearOfBrand = 1917;
        targetCar.endYearOfBrand = 2026;
    }

    @Override
    public void setFame() {
        targetCar.fame = 1;
    }

    @Override
    public void setCountry() {
        targetCar.country = "Германия";
    }

    @Override
    public void setModel() {
        targetCar.idModel = "AUDI_A4";
        targetCar.model = "A4";
        targetCar.modelCyrillic = "А4";
        targetCar.startYearOfModel = 1994;
        targetCar.endYearOfModel = 2026;
    }

    @Override
    public void setModelClass() {
        targetCar.modelClass = "D";
    }
}

class BuildCarToyota4Runner extends CarBuilderConcept {
// TOYOTA,Toyota,Тойота,1,Япония,1960,2026,TOYOTA_4RUNNER,4Runner,Фораннер,J,1984,2026

    @Override
    public void setBrand() {
        targetCar.idBrand = "TOYOTA";
        targetCar.brand = "Toyota";
        targetCar.brandCyrillic = "Тойота";
        targetCar.startYearOfBrand = 1960;
        targetCar.endYearOfBrand = 2026;
    }

    @Override
    public void setFame() {
        targetCar.fame = 1;
    }

    @Override
    public void setCountry() {
        targetCar.country = "Япония";
    }

    @Override
    public void setModel() {
        targetCar.idModel = "TOYOTA_4RUNNER";
        targetCar.model = "4Runner";
        targetCar.modelCyrillic = "Фораннер";
        targetCar.startYearOfModel = 1984;
        targetCar.endYearOfModel = 2026;
    }

    @Override
    public void setModelClass() {
        targetCar.modelClass = "J";
    }
}

class BuildDirector {
    private CarBuilderConcept selectedBuilder;

    public void setCarBuilder(CarBuilderConcept choice) {
        selectedBuilder = choice;
    }

    public Car getCar() {
        return selectedBuilder.getCar();
    }

    public void constructionCar() {
        selectedBuilder.createNew();
        selectedBuilder.setBrand();
        selectedBuilder.setFame();
        selectedBuilder.setCountry();
        selectedBuilder.setModel();
        selectedBuilder.setModelClass();
    }
}

public class Builder {
    private final static String MODULE_INFO =
        "This module not for production use. Only for studying purpose.";

    public static void main(String[] args) {
        BuildDirector buildDirector = new BuildDirector();
        CarBuilderConcept toyota4Runner = new BuildCarToyota4Runner();

        buildDirector.setCarBuilder(toyota4Runner);
        buildDirector.constructionCar();

        Car newToyotaCar = buildDirector.getCar();

        System.out.println(formatingStringForCar(newToyotaCar));
    }

    private static String formatingStringForCar(Car car) {
        String fameString = (car.fame == 0) ? "False" : "True";
        return  "ID Brand:\t\t\t" + car.idBrand + "\n" +
                "Brand:\t\t\t\t" + car.brand + "\n" +
                "Brand (Cyrillic):\t" + car.brandCyrillic + "\n" +
                "Fame:\t\t\t\t" + fameString + "\n" +
                "Country:\t\t\t" + car.country + "\n" +
                "Brand from year:\t" + car.startYearOfBrand + "\n" +
                "Brand to year:\t\t" + car.endYearOfBrand + "\n" +
                "ID Model:\t\t\t" + car.idModel + "\n" +
                "Model:\t\t\t\t" + car.model + "\n" +
                "Model (Cyrillic):\t" + car.modelCyrillic + "\n" +
                "Model class:\t\t" + car.modelClass + "\n" +
                "Model from year:\t" + car.startYearOfModel + "\n" +
                "Model end year:\t\t" + car.endYearOfModel + "\n";
    }
}
