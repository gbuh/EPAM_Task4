package domain;

/**
 * This class describes the Request entity
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public class Request extends Entity {
    private Long driverId;
    private String description;
    private Status status;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}