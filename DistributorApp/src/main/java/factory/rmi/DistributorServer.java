package factory.rmi;

import factory.DistributorApplication;
import factory.model.Distributor;
import factory.model.Product;
import factory.model.Repository;
import javafx.util.Pair;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistributorServer extends Thread implements DistributorInterface {


    private static DistributorServer instance;
    private Registry registry;
    @SuppressWarnings("removal")
    private DistributorServer() throws RemoteException{
        System.setProperty("java.security.policy", String.valueOf(DistributorApplication.class.getResource("policy/server_policyfile.txt")));
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

    public static DistributorServer getInstance() throws RemoteException {
        if(instance == null)
            instance = new DistributorServer();
        return instance;
    }

    @Override
    public void run(){

        try {
            DistributorServer server = instance;
            DistributorInterface stub = (DistributorInterface) UnicastRemoteObject.exportObject(server, 0);

            try {
                registry = LocateRegistry.getRegistry(1098);
                registry.rebind(Distributor.getInstance().getName(), stub);
            } catch (RemoteException e) {
                registry = null;
            }

            if (registry == null) {
                try {
                    registry = LocateRegistry.createRegistry(1098);
                    registry.rebind(Distributor.getInstance().getName(), stub);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return;
                }
            }

            System.out.println("Server started.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getProducts() throws RemoteException {

        return new ArrayList<>( Repository.getInstance().getProducts());
    }

    @Override
    public void buyProduct(HashMap<String, Integer> boughtProducts) throws RemoteException {

        Repository repository = Repository.getInstance();
        for(String key: boughtProducts.keySet()){

            repository.decreaseProductQuantity(key, boughtProducts.get(key));
        }
    }


    public void stopServer() {
        try {
            registry.unbind(Distributor.getInstance().getName());

        UnicastRemoteObject.unexportObject(this, true);

        UnicastRemoteObject.unexportObject(registry, true);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
