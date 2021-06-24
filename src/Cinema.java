package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of rows:\n> ");
        int numRows = sc.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        int numSeats = sc.nextInt();

        String[][] cinema = new String[numRows + 1][numSeats + 1];

        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[i].length; j++) {
                cinema[i][j] = "S";
            }
        }

        System.out.print("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
        int choice = sc.nextInt();

        displayMenu(choice, cinema, numRows, numSeats);

    }

    // Methods
    private static void displayCinema(String[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(j + " ");
                    }
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(cinema[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void buyTicket(String[][] cinema, int numRows, int numSeats) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a row number:\n> ");
        int userRow = sc.nextInt();
        System.out.print("Enter a seat number in that row:\n> ");
        int userSeat = sc.nextInt();

        if (userRow <= 0 || userRow > cinema.length - 1 || cinema[userRow][userSeat].equals("B")) {
            while (true) {
                if (userRow <= 0 || userRow > cinema.length - 1 || userSeat <= 0 || userSeat > numSeats) {
                    System.out.println("Wrong input!");
                } else if (cinema[userRow][userSeat].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    break;
                }
                System.out.print("Enter a row number:\n> ");
                userRow = sc.nextInt();
                System.out.print("Enter a seat number in that row:\n> ");
                userSeat = sc.nextInt();
            }
        }

        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[i].length; j++) {
                cinema[userRow][userSeat] = "B";
            }
        }

        int ticketPrice;

        if (numSeats * numRows <= 60) {
            ticketPrice = 10;
        } else {
            if (numRows / 2 >= userRow) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        System.out.printf("Ticket price: $%d\n", ticketPrice);
    }

    private static void displayMenu(int input, String[][] cinema, int numRows, int numSeats) {

        Scanner sc = new Scanner(System.in);

        while (input != 0) {
            switch (input) {
                case 1:
                    System.out.println("Cinema:");
                    displayCinema(cinema);
                    break;
                case 2:
                    buyTicket(cinema, numRows, numSeats);
                    break;
                case 3:
                    stats(cinema, numRows, numSeats);
                    break;
                default:
                    break;
            }
            System.out.print("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
            input = sc.nextInt();
        }
        return;
    }

    private static void stats(String[][] cinema, int numRows, int numSeats) {
        int totalIncome = 0;

        if (numSeats * numRows <= 60) {
            totalIncome += (numSeats * numRows) * 10;
        } else {
            if (numRows % 2 == 0){
                totalIncome += ((numSeats * numRows) / 2) * 10 + ((numSeats * numRows) / 2) * 8;
            } else {
                totalIncome += (((numRows / 2)) * numSeats) * 10 + (((numRows / 2) + 1) * numSeats) * 8;
            }
        }

        int purchasedTickets = 0;
        int currentIncome = 0;

        for (int i = 1; i < cinema.length; i++) {
            for (int j = 1; j < cinema[i].length; j++) {
                if (cinema[i][j].equals("B")) {
                    purchasedTickets++;
                    if (numSeats * numRows <= 60) {
                        currentIncome += 10;
                    } else {
                        if (i <= numRows / 2) {
                            currentIncome += 10;
                        } else {
                            currentIncome += 8;
                        }
                    }
                }
            }
        }

        float percentageTickets = ((float) purchasedTickets / (numSeats * numRows)) * 100;

        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentageTickets);
        System.out.printf("Current Income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }
}
