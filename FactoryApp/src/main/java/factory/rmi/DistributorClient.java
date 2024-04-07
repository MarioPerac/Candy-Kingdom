package factory.rmi;

import factory.FactoryApplication;
import factory.properties.ConfigProperties;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DistributorClient {

    private static DistributorClient instance;
    private ConfigProperties prop = new ConfigProperties();

    @SuppressWarnings("removal")
    private DistributorClient() {
        System.setProperty("java.security.policy", String.valueOf(FactoryApplication.class.getResource("policy/client_policyfile.txt")));
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }

    public static DistributorClient getInstance() {
        if (instance == null) {
            instance = new DistributorClient();
        }
        return instance;
    }


    public String[] getDistributors() {

        try {
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(prop.getRegistryPort()));
            return registry.list();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public DistributorInterface getDistributor(String name) {
        try {
            System.out.println(name);
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(prop.getRegistryPort()));
            return (DistributorInterface) registry.lookup(name);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
