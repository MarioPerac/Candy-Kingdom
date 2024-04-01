package factory.model;

import org.glassfish.jersey.servlet.internal.PersistenceUnitBinder;

public class StatusRequest {

    private String status;

    public StatusRequest() {
    }

    public StatusRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
