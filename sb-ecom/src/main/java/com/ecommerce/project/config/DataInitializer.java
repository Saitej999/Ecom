package com.ecommerce.project.config;

import com.ecommerce.project.model.*;
import com.ecommerce.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@DependsOn("webSecurityConfig")
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeCategories();
        // Skip suppliers for now to avoid role conflicts
        // initializeSuppliers();
        initializeProducts();
    }

    private void initializeCategories() {
        String[] categories = {
            "Beverages",
            "Energy Drinks", 
            "Dairy Products",
            "Snacks & Confectionery",
            "Canned & Packaged Foods",
            "Fresh Fruits",
            "Fresh Vegetables",
            "Bakery Items",
            "Frozen Foods",
            "Health & Wellness"
        };

        for (String categoryName : categories) {
            if (categoryRepository.findByCategoryName(categoryName) == null) {
                Category category = new Category();
                category.setCategoryName(categoryName);
                categoryRepository.save(category);
                System.out.println("Created category: " + categoryName);
            }
        }
    }

    private void initializeSuppliers() {
        // Create supplier roles and users
        Role sellerRole = roleRepository.findByRoleName(AppRole.ROLE_SELLER).orElse(null);
        if (sellerRole == null) {
            System.out.println("Seller role not found, skipping supplier creation");
            return;
        }

        String[][] suppliers = {
            {"coca_cola_supplier", "supplier@cocacola.com", "Coca-Cola Company Supplier"},
            {"pepsi_supplier", "supplier@pepsi.com", "PepsiCo Distributor"},
            {"nestle_supplier", "supplier@nestle.com", "Nestle Foods Supplier"},
            {"redbull_supplier", "supplier@redbull.com", "Red Bull Distribution"},
            {"local_dairy", "supplier@localdairy.com", "Fresh Local Dairy Farm"},
            {"organic_farm", "supplier@organicfresh.com", "Organic Fresh Farm"}
        };

        for (String[] supplier : suppliers) {
            if (!userRepository.existsByUserName(supplier[0])) {
                User supplierUser = new User();
                supplierUser.setUserName(supplier[0]);
                supplierUser.setEmail(supplier[1]);
                supplierUser.setPassword(passwordEncoder.encode("supplier123"));
                // Create a new set instead of using existing role directly
                Set<Role> roles = Set.of(sellerRole);
                supplierUser.setRoles(roles);
                userRepository.save(supplierUser);
                System.out.println("Created supplier: " + supplier[2]);
            }
        }
    }

    private void initializeProducts() {
        // Get categories
        Category beverages = categoryRepository.findByCategoryName("Beverages");
        Category energyDrinks = categoryRepository.findByCategoryName("Energy Drinks");
        Category dairy = categoryRepository.findByCategoryName("Dairy Products");
        Category snacks = categoryRepository.findByCategoryName("Snacks & Confectionery");
        Category canned = categoryRepository.findByCategoryName("Canned & Packaged Foods");
        Category fruits = categoryRepository.findByCategoryName("Fresh Fruits");
        Category vegetables = categoryRepository.findByCategoryName("Fresh Vegetables");
        Category bakery = categoryRepository.findByCategoryName("Bakery Items");
        Category frozen = categoryRepository.findByCategoryName("Frozen Foods");
        Category health = categoryRepository.findByCategoryName("Health & Wellness");

        // Get existing users (created by WebSecurityConfig)
        User seller1 = userRepository.findByUserName("seller1").orElse(null);
        User admin = userRepository.findByUserName("admin").orElse(null);
        
        // Use these users as suppliers for now
        User cocoaColaSupplier = seller1;
        User pepsiSupplier = seller1;
        User nestleSupplier = admin;
        User redbullSupplier = seller1;
        User localDairy = admin;
        User organicFarm = seller1;

        // Energy Drinks
        createProduct("Red Bull Energy Drink", "Classic Red Bull energy drink with caffeine, taurine and B-vitamins. Perfect for boosting energy and focus.", 250, 3.99, 5.0, energyDrinks, redbullSupplier, "redbull.jpg");
        createProduct("Monster Energy", "Monster Energy drink with a powerful blend of caffeine and natural flavors. Original green flavor.", 355, 2.99, 10.0, energyDrinks, pepsiSupplier, "monster.jpg");
        createProduct("Rockstar Energy", "Rockstar Energy drink with herbal blend and caffeine. Great taste and energy boost.", 473, 2.49, 8.0, energyDrinks, pepsiSupplier, "rockstar.jpg");
        createProduct("Bang Energy", "Bang Energy drink with zero sugar and creatine. Perfect for pre-workout energy.", 473, 2.79, 12.0, energyDrinks, pepsiSupplier, "bang.jpg");
        createProduct("5-Hour Energy", "5-Hour Energy shot with B-vitamins and caffeine. Compact energy boost without crash.", 57, 3.49, 0.0, energyDrinks, pepsiSupplier, "5hour.jpg");

        // Beverages
        createProduct("Coca-Cola Classic", "Original Coca-Cola with the classic taste loved worldwide. Refreshing carbonated soft drink.", 355, 1.99, 15.0, beverages, cocoaColaSupplier, "coca_cola.jpg");
        createProduct("Pepsi Cola", "Pepsi Cola with bold and refreshing taste. Perfect for any occasion.", 355, 1.89, 12.0, beverages, pepsiSupplier, "pepsi.jpg");
        createProduct("Sprite", "Lemon-lime flavored carbonated soft drink. Crisp and refreshing taste.", 355, 1.79, 10.0, beverages, cocoaColaSupplier, "sprite.jpg");
        createProduct("Orange Juice", "100% pure orange juice made from fresh oranges. Rich in Vitamin C.", 946, 4.99, 5.0, beverages, organicFarm, "orange_juice.jpg");
        createProduct("Apple Juice", "100% pure apple juice from fresh apples. No added sugars or preservatives.", 946, 4.49, 8.0, beverages, organicFarm, "apple_juice.jpg");

        // Dairy Products
        createProduct("Whole Milk", "Fresh whole milk from local dairy farms. Rich in calcium and protein.", 946, 3.99, 0.0, dairy, localDairy, "whole_milk.jpg");
        createProduct("Greek Yogurt", "Creamy Greek yogurt with live cultures. High in protein and probiotics.", 170, 1.99, 20.0, dairy, localDairy, "greek_yogurt.jpg");
        createProduct("Cheddar Cheese", "Aged cheddar cheese with rich and sharp flavor. Perfect for sandwiches.", 227, 5.99, 15.0, dairy, localDairy, "cheddar_cheese.jpg");
        createProduct("Butter", "Fresh creamy butter made from pure cream. Perfect for cooking and baking.", 454, 4.49, 0.0, dairy, localDairy, "butter.jpg");
        createProduct("Cream Cheese", "Smooth and creamy cheese spread. Great for bagels and baking.", 227, 2.99, 25.0, dairy, localDairy, "cream_cheese.jpg");

        // Snacks & Confectionery
        createProduct("Lay's Classic Chips", "Classic potato chips with the perfect amount of salt. Crispy and delicious.", 40, 1.49, 20.0, snacks, pepsiSupplier, "lays_chips.jpg");
        createProduct("Kit Kat Chocolate", "Crispy wafer bars covered in milk chocolate. Have a break, have a Kit Kat.", 42, 1.29, 15.0, snacks, nestleSupplier, "kitkat.jpg");
        createProduct("Snickers Bar", "Chocolate bar with peanuts, caramel and nougat. Satisfies your hunger.", 52, 1.99, 10.0, snacks, nestleSupplier, "snickers.jpg");
        createProduct("Pringles Original", "Original flavored potato crisps in iconic canister. Once you pop, you can't stop.", 158, 2.99, 18.0, snacks, pepsiSupplier, "pringles.jpg");
        createProduct("Oreo Cookies", "Chocolate sandwich cookies with cream filling. Milk's favorite cookie.", 432, 3.99, 22.0, snacks, nestleSupplier, "oreo.jpg");

        // Fresh Fruits
        createProduct("Bananas", "Fresh yellow bananas rich in potassium and vitamins. Perfect healthy snack.", 1000, 1.99, 0.0, fruits, organicFarm, "bananas.jpg");
        createProduct("Red Apples", "Crispy and sweet red apples. Great source of fiber and vitamins.", 1000, 3.49, 0.0, fruits, organicFarm, "red_apples.jpg");
        createProduct("Orange", "Fresh juicy oranges packed with Vitamin C. Perfect for juice or eating.", 1000, 2.99, 0.0, fruits, organicFarm, "oranges.jpg");
        createProduct("Strawberries", "Fresh sweet strawberries. Rich in antioxidants and vitamin C.", 454, 4.99, 0.0, fruits, organicFarm, "strawberries.jpg");
        createProduct("Grapes", "Sweet seedless grapes. Perfect for snacking or making juice.", 454, 3.99, 0.0, fruits, organicFarm, "grapes.jpg");

        // Fresh Vegetables
        createProduct("Tomatoes", "Fresh red tomatoes perfect for cooking and salads. Rich in lycopene.", 454, 2.99, 0.0, vegetables, organicFarm, "tomatoes.jpg");
        createProduct("Carrots", "Fresh organic carrots rich in beta-carotene. Great for cooking and snacking.", 454, 1.99, 0.0, vegetables, organicFarm, "carrots.jpg");
        createProduct("Broccoli", "Fresh green broccoli packed with vitamins and minerals. Healthy and nutritious.", 454, 2.49, 0.0, vegetables, organicFarm, "broccoli.jpg");
        createProduct("Spinach", "Fresh baby spinach leaves. Rich in iron and perfect for salads.", 142, 2.99, 0.0, vegetables, organicFarm, "spinach.jpg");
        createProduct("Bell Peppers", "Colorful bell peppers rich in vitamin C. Perfect for cooking and salads.", 454, 3.49, 0.0, vegetables, organicFarm, "bell_peppers.jpg");

        // Bakery Items
        createProduct("White Bread", "Fresh white bread loaf perfect for sandwiches and toast.", 680, 2.49, 0.0, bakery, localDairy, "white_bread.jpg");
        createProduct("Croissants", "Buttery and flaky croissants. Perfect for breakfast or snack.", 340, 4.99, 15.0, bakery, localDairy, "croissants.jpg");
        createProduct("Bagels", "Fresh bagels with sesame seeds. Great for breakfast with cream cheese.", 454, 3.99, 0.0, bakery, localDairy, "bagels.jpg");
        createProduct("Chocolate Muffins", "Delicious chocolate chip muffins. Perfect with coffee or tea.", 340, 5.99, 20.0, bakery, localDairy, "muffins.jpg");

        // Canned & Packaged Foods
        createProduct("Tomato Soup", "Campbell's tomato soup in convenient can. Just heat and serve.", 305, 1.99, 0.0, canned, nestleSupplier, "tomato_soup.jpg");
        createProduct("Pasta", "Premium durum wheat pasta. Perfect for Italian dishes.", 454, 1.49, 0.0, canned, nestleSupplier, "pasta.jpg");
        createProduct("Rice", "Long grain white rice. Perfect side dish for any meal.", 907, 3.99, 0.0, canned, nestleSupplier, "rice.jpg");
        createProduct("Beans", "Canned black beans ready to eat. High in protein and fiber.", 425, 1.29, 0.0, canned, nestleSupplier, "beans.jpg");

        // Frozen Foods
        createProduct("Frozen Pizza", "Delicious pepperoni pizza ready to bake. Quick and easy meal.", 378, 4.99, 25.0, frozen, nestleSupplier, "frozen_pizza.jpg");
        createProduct("Ice Cream", "Vanilla ice cream made with real cream. Perfect dessert.", 946, 5.99, 30.0, frozen, localDairy, "ice_cream.jpg");
        createProduct("Frozen Vegetables", "Mixed frozen vegetables. Convenient and nutritious.", 454, 2.99, 0.0, frozen, organicFarm, "frozen_vegetables.jpg");

        // Health & Wellness
        createProduct("Protein Powder", "Whey protein powder for muscle building. Vanilla flavor.", 907, 29.99, 15.0, health, nestleSupplier, "protein_powder.jpg");
        createProduct("Multivitamins", "Daily multivitamin supplements with essential vitamins and minerals.", 100, 19.99, 20.0, health, nestleSupplier, "multivitamins.jpg");
        createProduct("Green Tea", "Organic green tea bags rich in antioxidants. Healthy and refreshing.", 40, 7.99, 10.0, health, organicFarm, "green_tea.jpg");
    }

    private void createProduct(String name, String description, int quantity, double price, double discount, Category category, User supplier, String imageName) {
        // Check if product already exists
        if (productRepository.findAll().stream().anyMatch(p -> p.getProductName().equals(name))) {
            return;
        }

        Product product = new Product();
        product.setProductName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setSpecialPrice(price - (discount * 0.01 * price));
        product.setCategory(category);
        product.setUser(supplier);
        product.setImage(imageName);

        productRepository.save(product);
        System.out.println("Created product: " + name);
    }
}
