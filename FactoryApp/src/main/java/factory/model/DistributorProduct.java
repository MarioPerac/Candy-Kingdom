package factory.model;


public class DistributorProduct extends Product {

    private int selectedQuantity = 0;

    public DistributorProduct() {
        super();
    }

    public DistributorProduct(String name, Double price, int quantity) {
        super(name, price, quantity);
    }

    public int getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public static DistributorProduct fromProduct(Product product) {
        DistributorProduct distributorProduct = new DistributorProduct();
        distributorProduct.setName(product.getName());
        distributorProduct.setPrice(product.getPrice());
        distributorProduct.setQuantity(product.getQuantity());
        return distributorProduct;
    }
}
