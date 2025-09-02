package com.fintech.encryptor;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

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

        System.out.print("1)Encrypt\n2)Decrypt\nSelect action :  ");
        String actionChoice = scanner.nextLine().trim();

        boolean encryptAction = "1".equals(actionChoice);

        PBEAlgorithm[] algorithms = PBEAlgorithm.values();
        System.out.println("Available PBE Algorithms:");
        for (int i = 0; i < algorithms.length; i++) {
            System.out.printf("%d. %s%n", i + 1, algorithms[i]);
        }

        System.out.print("Choose algorithm number: ");
        int algoIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (algoIndex < 0 || algoIndex >= algorithms.length) {
            System.out.println("Invalid algorithm selection!");
            return;
        }

        String selectedAlgorithm = algorithms[algoIndex].getAlgorithm();

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        try {
            String result;
            if (encryptAction) {
                result = CryptoUtils.encrypt(text, password, selectedAlgorithm);
                System.out.println("Encrypted text: " + result);
            } else {
                result = CryptoUtils.decrypt(text, password, selectedAlgorithm);
                System.out.println("Decrypted text: " + result);
            }

            StringSelection selection = new StringSelection(result);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            System.out.println("Result copied to clipboard!");

        } catch (Exception e) {
            System.out.println("Operation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}