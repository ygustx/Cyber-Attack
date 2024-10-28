package javaapplication1;

        import javaapplication1.Database;
        import javaapplication1.Product;
        import javaapplication1.User;
import java.io.*;   
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private final String productFile = "products.txt"; // Arquivo para armazenar produtos

    public Database() {
        loadProducts(); // Carrega produtos do arquivo ao iniciar
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(String id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts(); // Salva produtos no arquivo ao adicionar
    }

 public boolean removeProduct(String name) {
    Product productToRemove = products.stream()
        .filter(product -> product.getName().equals(name) && product.getQuantity() > 0)
        .findFirst()
        .orElse(null);

    if (productToRemove != null) {
        products.remove(productToRemove);
        return true; // Remoção bem-sucedida
    } else {
        return false; // Remoção falhou
    }
}
    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

  public void updateStock(String name, int quantity) {
    Product product = products.stream()
        .filter(p -> p.getName().equals(name) && p.getQuantity() > 0)
        .findFirst()
        .orElse(null);

    if (product != null) {
        product.setQuantity(quantity);
    }
}

    public boolean userExists(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(productFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String type = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    products.add(new Product(name, type, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(productFile))) {
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getType() + "," + product.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }
}