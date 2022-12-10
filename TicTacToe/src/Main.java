import java.util.Scanner;

public class Main {

    //help method to calculate if a given number is even
    static boolean isEven(int number) {
        boolean even = false;
        if ((number % 2) == 0) {
            even = true;
        }
        return even;
    }

    //Main Method
    public static void main(String[] args) {
        char[] player = {'x','o'};

        //Creating Objects (TicTacToe Field and a Computer)
        Field field1 = new Field();
        Computer computer = new Computer();

        //Printing the empty field
        System.out.print(field1);

        //variable for counting
        int move_count = 1;
        int index;

        //initialization and declaration of the scanner
        Scanner scanner = new Scanner(System.in);

        //actual game
        //loop till field is full
        while (field1.isFull() == false) {
            char character = player[0];
            if (Main.isEven(move_count)) {
                character = player[1];
            }

            System.out.println(move_count + ". Zug: " + character);

            if (character == 'x') {
                index = scanner.nextInt();
            } else {
                index = computer.nextMove(field1);
            }

            if (!(index > 8 || index < 0 || field1.isNotFree(index))) {
                field1.placeSign(index, character);
                System.out.print(field1);
                move_count++;

            }

            //if someone won loop will stop and winner is announced
            if (field1.hasWon(character)) {
                System.out.println("Sieger: " + character);
                break;
            }

            //printing out that nobody won
            if (field1.isFull()) {
                System.out.println("Kein Sieger");
            }
        }



        scanner.close();

    }
}