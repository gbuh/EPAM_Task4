package domain;

/**
 * This class describes the Driver entity
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public class Driver extends User{
    private Long carId;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

}