package factory.distributorapp.model;

public class Distributor {

    private String name;

    private static Distributor instance;

    private Distributor(){

    }

    public static Distributor getInstance(){
        if(instance != null)
            return instance;
        else
            return new Distributor();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
