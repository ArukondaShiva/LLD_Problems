package org.example._04_vendingmachine.repository;

import org.example._04_vendingmachine.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {


    /*
    we have
        private Map<Product,Integer> inventory;
        in vending Machine class. which means each machine can have it's own inventory.
        so it would be better to maintain a map of ==> private Map<Integer, VendingMachine> machines = new HashMap<>();

        if we want to store the all global products from all machines we can use ==> Map<Integer, Product> products


        == Mistake ==
        this class storing all global products in a list and
        has method called findByMachine(machineId)


        == Definition ==
        = Entity (VendingMachine) = manages its own local inventory map.
        = Repository (VendingMachineRepository) = manages all machines (global view).


     “NOTE = Entities store the state of a single object, while the repository layer stores and manages
      collections of entities — acting like the database of the application.”

      */

    private Map<Integer, Product> products;
    private int nextProductId;



    public ProductRepository(){
        this.products = new HashMap<>();
        this.nextProductId = 1;
        System.out.println("ProductRepository initialized");
    }


    public Product findById(int productId){

        Product product = products.get(productId);

        if(product==null){
            System.out.println("Repository: Product not found with ID: " + productId);
        }else{
            System.out.println("Repository: Found product with ID: " + productId);
        }

        return product;
    }



    public List<Product> findByMachine(int machineId){
        // For simplicity, return all products since we don't have machine-specific products
        List<Product> machineProducts = new ArrayList<>(products.values());
        System.out.println("Repository: Found " + machineProducts.size() + " products for machine " + machineId);
        return machineProducts;
    }


    public Product save(Product product){

        if(product.getId()==0){
            Product newProduct = new Product(nextProductId++,product.getName(),product.getPrice(),product.getCategory());
            products.put(newProduct.getId(), newProduct);
            System.out.println("Repository: Saved new product with ID: " + newProduct.getId());
            return newProduct;
        }else{
            products.put(product.getId(),product);
            System.out.println("Repository: Updated product with ID: " + product.getId());
            return product;
        }
    }


    public void delete(int productId){
        Product removed = products.remove(productId);
        if(removed!=null){
            System.out.println("Repository: Deleted product with ID: " + productId);
        }else{
            System.out.println("Repository: Product not found for deletion: " + productId);
        }
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public int getTotalProducts() {
        return products.size();
    }

}
