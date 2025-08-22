package com.fintech.encryptor;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!UserStore.validateUser(username, password)) {
            System.out.println("Invalid username/password!");
            return;
        }

        System.out.println("Welcome " + username + "!");

        System.out.print("Select action (enc/dec): ");
        String action = scanner.nextLine().trim().toLowerCase();

        Set<String> algorithmsSet = CryptoUtils.getAvailablePBEAlgorithms();
        List<String> algorithms = algorithmsSet.stream().sorted().collect(Collectors.toList());

        System.out.println("Available algorithms:");
        for (int i = 0; i < algorithms.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, algorithms.get(i));
        }

        System.out.print("Choose algorithm number: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > algorithms.size()) {
            System.out.println("Invalid algorithm choice!");
            return;
        }
        String algorithm = algorithms.get(choice - 1);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        try {
            if ("enc".equals(action)) {
                String encrypted = CryptoUtils.encrypt(text, password, algorithm);
                System.out.println("Encrypted text: " + encrypted);

                String encryptedText = CryptoUtils.encrypt(text, password, algorithm);
                ClipboardUtils.copyToClipboard(encryptedText);
                System.out.println("Encrypted text copied to clipboard: " + encryptedText);

            } else if ("dec".equals(action)) {
                String decrypted = CryptoUtils.decrypt(text, password, algorithm);
                System.out.println("Decrypted text: " + decrypted);

                String encryptedText = CryptoUtils.encrypt(text, password, algorithm);
                ClipboardUtils.copyToClipboard(encryptedText);
                System.out.println("Decrypted text copied to clipboard: " + encryptedText);

            } else {
                System.out.println("Unknown action: " + action);
            }
        } catch (Exception e) {
            System.out.println("Error during " + action.toUpperCase() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}