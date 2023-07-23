package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;
    @Column(nullable = false)
    private String carMake;

    @Column(nullable = false)
    private String carModel;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false, unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private int kilometers;

    @Column(nullable = false)
    private double engine;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getYear() {
        return year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getKilometers() {
        return kilometers;
    }

    public double getEngine() {
        return engine;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }




}
