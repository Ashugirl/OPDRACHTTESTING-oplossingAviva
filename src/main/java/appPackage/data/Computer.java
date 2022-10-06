package appPackage.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Computer {

    @Id
    private String serialNumber;
    private Brand brand;
    private String info;
    private boolean needsReparation;

    public Computer() {
    }

    public Computer(String serialNumber, Brand brand, String info) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.info = info;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isNeedsReparation() {
        return needsReparation;
    }

    public void setNeedsReparation(boolean needsReparation) {
        this.needsReparation = needsReparation;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "serialNumber='" + serialNumber + '\'' +
                ", brand=" + brand +
                ", info='" + info + '\'' +
                ", needsReparation=" + needsReparation +
                '}';
    }
}
