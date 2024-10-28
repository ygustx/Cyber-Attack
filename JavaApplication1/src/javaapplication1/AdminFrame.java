        package javaapplication1;


        import javaapplication1.Database;
        import javaapplication1.Product;
        import javaapplication1.User;
        import javax.swing.*;
        import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

        public class AdminFrame extends JFrame {
            private Database database;

            public AdminFrame() {
                database = new Database(); // Simulando o banco de dados
                setTitle("Admin Panel");
                setSize(400, 300);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                JPanel panel = new JPanel();
                add(panel);
                placeComponents(panel);
                setVisible(true);
            }

            private void placeComponents(JPanel panel) {
                panel.setLayout(null);

                JButton addUserButton = new JButton("Cadastrar Usuário");
                addUserButton.setBounds(10, 20, 150, 25);
                panel.add(addUserButton);

                JButton removeUserButton = new JButton("Remover Usuário");
                removeUserButton.setBounds(10, 60, 150, 25);
                panel.add(removeUserButton);

                JButton addProductButton = new JButton("Cadastrar Produto");
                addProductButton.setBounds(10, 100, 150, 25);
                panel.add(addProductButton);

                JButton removeProductButton = new JButton("Remover Produto");
                removeProductButton.setBounds(10, 140, 150, 25);
                panel.add(removeProductButton);

                JButton updateStockButton = new JButton("Atualizar Estoque");
                updateStockButton.setBounds(10, 180, 150, 25);
                panel.add(updateStockButton);

                addUserButton.addActionListener(e -> {
                    String name = JOptionPane.showInputDialog("Nome Completo:");
                    String id = JOptionPane.showInputDialog("ID:");
                    String email = JOptionPane.showInputDialog("E-mail:");
                    String phone = JOptionPane.showInputDialog("Telefone:");
                    if (name != null && id != null && email != null && phone != null) {
                        if (!database.userExists(email)) {
                            database.addUser(new User(name, id, email, phone));
                            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "E-mail já cadastrado!");
                        }
                    }
                });

                removeUserButton.addActionListener(e -> {
                    String id = JOptionPane.showInputDialog("ID do Usuário a Remover:");
                    if (id != null) {
                        database.removeUser(id);
                        JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    }
                });

                addProductButton.addActionListener(e -> {
                    String name = JOptionPane.showInputDialog("Nome do Produto:");
                    String type = JOptionPane.showInputDialog("Tipo do Produto:");
                    String quantityStr = JOptionPane.showInputDialog("Quantidade:");
                    if (name != null && type != null && quantityStr != null) {
                        try {
                            int quantity = Integer.parseInt(quantityStr);
                            database.addProduct(new Product(name, type, quantity));
                            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Quantidade inválida!");
                        }
                    }
                });

   removeProductButton.addActionListener(e -> {
    String name = JOptionPane.showInputDialog("Nome do Produto a Remover:");
    if (name != null) {
        boolean removed = database.removeProduct(name);
        if (removed) {
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado ou estoque insuficiente!");
        }
    }
});

             updateStockButton.addActionListener(e -> {
    String name = JOptionPane.showInputDialog("Nome do Produto:");
    String quantityStr = JOptionPane.showInputDialog("Nova Quantidade:");
    if (name != null && quantityStr != null) {
        try {
            int quantity = Integer.parseInt(quantityStr);
            Product product = database.getProducts().stream()
                .filter(p -> p.getName().equals(name) && p.getQuantity() > 0)
                .findFirst()
                .orElse(null);

            if (product != null) {
                database.updateStock(name, quantity);
                JOptionPane.showMessageDialog(null, "Estoque atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto não encontrado ou estoque insuficiente!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida!");
        }
    }
});
            }
        }
