package factory.distributorapp.rmi;

import factory.distributorapp.model.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DistributorInterface extends Remote {

    public List<Product> getProducts() throws RemoteException;
//    public boolean buyProduct(String name) throws RemoteException;
}
