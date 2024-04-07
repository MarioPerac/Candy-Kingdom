package factory.rmi;

import factory.model.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface DistributorInterface extends Remote {

    public List<Product> getProducts() throws RemoteException;
    public void buyProduct(HashMap<String, Integer> boughtProducts) throws RemoteException;
}
