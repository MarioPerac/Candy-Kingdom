package factory.model;

public class Distributor {

    private String name;

    private static Distributor instance;

    private Distributor(){

    }

    public static Distributor getInstance(){
        if (instance == null) {
            instance = new Distributor();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
