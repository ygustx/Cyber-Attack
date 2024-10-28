
package testes;


import javaapplication1.Database;

import javaapplication1.Product;

import javaapplication1.User;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;


public class databasetest {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
        // Adicionando usuários e produtos de teste
        database.addUser(new User("Alice", "1", "alice@example.com", "123456789"));
        database.addUser(new User("Bob", "2", "bob@example.com", "987654321"));
        database.addProduct(new Product("Produto1", "Tipo1", 10));
        database.addProduct(new Product("Produto2", "Tipo2", 0)); // Produto sem estoque
    }

    @Test
    public void testAddUser() {
        User user = new User("Charlie", "3", "charlie@example.com", "555555555");
        database.addUser(user);
        assertTrue(database.userExists("charlie@example.com"));
    }

    @Test
    public void testRemoveUser() {
        database.removeUser("1");
        assertFalse(database.userExists("alice@example.com"));
    }

    @Test
    public void testRemoveProduct() {
        assertTrue(database.removeProduct("Produto1")); // Deve remover com sucesso
        assertFalse(database.removeProduct("Produto1")); // Não deve encontrar o produto
    }

    @Test
    public void testRemoveProductNotFound() {
        assertFalse(database.removeProduct("ProdutoInexistente")); // Produto não encontrado
    }

    @Test
    public void testUpdateStock() {
        database.updateStock("Produto1", 15);
        assertEquals(15, database.getProducts().stream()
            .filter(p -> p.getName().equals("Produto1"))
            .findFirst()
            .get().getQuantity());
    }

    @Test
    public void testUpdateStockProductNotFound() {
        database.updateStock("ProdutoInexistente", 10);
        // Verifica que a quantidade do produto não mudou
        assertEquals(10, database.getProducts().stream()
            .filter(p -> p.getName().equals("Produto1"))
            .findFirst()
            .get().getQuantity());
    }

    @Test
    public void testRemoveProductWithZeroStock() {
        assertFalse(database.removeProduct("Produto2")); // Produto sem estoque não deve ser removido
    }
}