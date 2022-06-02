package com.hdvuv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static Map<String, String> accountMap;

    public static void main(String[] args) throws Exception {
        accountMap = loadData();

        login();
    }

    /**
     * Load account and password for login system
     * Be called when run application
     *
     * @return Map contains all accounts and passwords
     * @throws Exception exception
     */
    public static Map<String, String> loadData() throws Exception {
        Map<String, String> accountMap = new HashMap<>();
        String accountManageUrl = new File("").getAbsolutePath() + "\\src\\com\\hdvuv\\manage\\account-manage.txt";
        File file = new File(accountManageUrl);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String userName = EMPTY;
        String password = EMPTY;

        while (line != null) {
            if (line.indexOf("Username") > 0) {
                userName = line.substring(line.indexOf("Username") + 8).trim();
            }
            if (line.indexOf("Password") > 0) {
                password = line.substring(line.indexOf("Password") + 8).trim();
            }
            if (!userName.isEmpty() && !password.isEmpty()) {
                accountMap.put(userName, password);
            }
            line = reader.readLine();
        }
        return accountMap;
    }

    /**
     * Handle logic login
     */
    public static void login() {
        Scanner sc = new Scanner(System.in);
        int numberLogin = NUMBER_LOGIN;

        while (numberLogin > 1) {
            System.out.println("---------- ĐĂNG NHẬP HỆ THỐNG ----------");
            System.out.println("Hãy nhập Tài khoản và Mật khẩu của bạn (còn" + numberLogin + " lần nhập)");
            System.out.println("Nhập Tài khoản:");
            String account = sc.nextLine();
            System.out.println("Nhập Mật khẩu:");
            String password = sc.nextLine();

            if (!checkLogin(account, password)) {
                numberLogin--;
            }
        }
        if (numberLogin == 0) {
            System.out.println("Đã nhập tối đa 5 lần. Đăng nhập thất bại");
            sc.close();
        }
    }

    /**
     * Compare account login
     *
     * @param account  account
     * @param password password
     * @return true if valid account, otherwise return false
     */
    public static boolean checkLogin(String account, String password) {
        if (accountMap.containsKey(account)) {
            return password.equals(accountMap.get(account));
        }
        return false;
    }

    /**
     * Constant
     */
    public static String EMPTY = "";
    public static int NUMBER_LOGIN = 5;
}
