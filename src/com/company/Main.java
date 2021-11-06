package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static String[] allowedToppings = {"pepperoni", "mushroom", "cheese", "sausage", "onion", "olives", "green pepper", "garlic", "pineapple", "chicken", "ham"};

    static void toppingsList() {
        for (int i = 0; i < allowedToppings.length; i++) {
            System.out.println((i + 1) + "-" + allowedToppings[i]);
        }
    }

    static int parseInt(String topping) {
        return Integer.parseInt(topping);
    }

    static double bill(int[] toppingNumb, double pizzaSize) {
        double toppingAmount = 0.00;
        for (int i = 0; i < toppingNumb.length; i++) {
            toppingAmount += toppingNumb[i];
        }
        return ((toppingAmount - 2) * 1.25) + pizzaSize;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] toppings = new int[allowedToppings.length];
        double size;
        String topping;
        System.out.println("What size pizza would you want: small, medium or large?");
        String sizeType = input.next();
        if ((sizeType.equalsIgnoreCase("small")) || (sizeType.contains("s"))) {
            size = 7.99;
        } else if ((sizeType.equalsIgnoreCase("medium")) || (sizeType.contains("m"))) {
            size = 10.99;
        } else {
            size = 13.99; //if they type something incorrect they will order the largest pizza, so we make more money
        }
        toppingsList();
        breakpoint:
        {
            while (true) {
                try {
                    System.out.println("Choose all of the toppings that you want from the list (Type the number you want).");
                    System.out.println("Type 'end' if you do not want anymore toppings:");
                    topping = input.next();
                    if (topping.equalsIgnoreCase("end")) {
                        break breakpoint;
                    }
                    toppings[parseInt(topping) - 1] += 1;
                    System.out.println(Arrays.toString(toppings));
                } catch (InputMismatchException e) {
                    toppingsList();
                    System.out.println("You have not typed a number. " + e);
                    input.next();
                } catch (ArrayIndexOutOfBoundsException e) {
                    toppingsList();
                    System.out.println("The number you have typed isn't on the list.");
                } catch (NumberFormatException e) {
                    toppingsList();
                    System.out.println("Something you have typed is incorrect. " + e);
                }
            }
        }
        System.out.println("Your order has been completed.");
        System.out.println("You have ordered a " + sizeType + " pizza with the toppings: ");
        for (int i = 0; i < allowedToppings.length; i++) {
            System.out.println(toppings[i] + "x " + allowedToppings[i]);
        }
        System.out.println("Your bill sums to: £" + bill(toppings, size));
    }
}