package factory.rmi;

import factory.model.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DistributorInterface extends Remote {

    public List<Product> getProducts() throws RemoteException;
}
