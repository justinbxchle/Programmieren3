public class Field {
    //attribute
    private char[][] field;

    //constructors
    public Field() {
        this.field = setDefaultField();
    }
    public Field(Field field) {
        this.field = copyField(field);
    }

    //getter method for field attribute
    public char[][] getField() {
        return this.field;
    }

    //mandatory methods

    //checking if cell is occupied
    public boolean isNotFree(int index) {
        boolean free = false;
        int line = this.findLine(index);
        int column = this.findColumn(index);
        if (this.field[line][column] != '-') {
            free = true;
        }
        return free;
    }

    //checking if a given player has won
    public boolean hasWon(char player) {
        boolean won = true;
        boolean won1 = true;
        boolean won2 = true;
        boolean out = true;
        int length = this.field[0].length;

        //we check (2*n)+2 different possibilities
        int[] win = new int [(2 * length) + 2];

        //temp variable
        char temp_left_corner = this.field[0][0];
        char temp_right_corner = this.field[0][length - 1];

        //loop over the field
        for (int i = 0; i < length; i++) {
            char temp_left = this.field[i][0];
            char temp_up = this.field[0][i];

            //checking if horizontally won
            for (int j = 0; j < length; j++) {
                if (temp_left != this.field[i][j]) {
                    won = false;
                }
            }
            if (won && temp_left == player) {
                System.out.println("1");
                return true;
            }

            won = true;

            //checking if vertically won
            for (int j = 0; j < length; j++) {
                if (temp_up != this.field[j][i]) {
                    won = false;
                }
            }
            if (won && temp_up == player) {
                System.out.println("2");

                return true;
            }

            won = true;

            //checking if diagonally won
            if (temp_left_corner != this.field[i][i]) {
                won1 = false;
            }

            if (temp_right_corner != this.field[length - i - 1][i]) {
                won2 = false;
            }
        }

        //checking if diagonally won
        if (won1 && temp_left_corner == player) {
            return true;
        }

        if (won2 && temp_right_corner == player) {
            return true;
        }

        return false;
    }

    //checking if field is full
    public boolean isFull() {
        boolean full = true;
        int length = this.field[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.field[i][j] == '-') {
                    full = false;
                }
            }
        }
        return full;
    }

    //placing players sign on given index
    public void placeSign(int index, char character) {
        int line = this.findLine(index);
        int column = this.findColumn(index);

        this.field[line][column] = character;

    }

    //coverting field to string
    public String toString() {
        int length = this.field[0].length;
        String field_string = "";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                field_string = field_string + this.field[i][j];
            }
            field_string = field_string + '\n';
        }
        return field_string;
    }

    //checking if a player would win with one more sign
    public int wouldWin(char player) {
        int length = this.field[0].length;
        int index = (length * length) - 1;
        Field temp = new Field(this);

        for (int i = 0; i <= index; i++) {
            if (!(temp.isNotFree(i))) {
                temp.placeSign(i, player);

                if (temp.hasWon(player)) {
                    return i;
                }
                temp.placeSign(i, '-');
            }
        }
        index = -1;
        return index;
    }



    //own (help) methods

    //copying given field
    private static char[][] copyField(Field field) {
        int length = field.field[0].length;
        char[][] copy = new char[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                copy[i][j] = field.field[i][j];
            }
        }
        return copy;
    }

    //setting to empty field
    private static char[][] setDefaultField() {
        int length = 3;
        char[][] field = new char [length] [length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                field[i][j] = '-';
            }
        }
        return field;
    }

    //help method to find the right column to a given index
    private int findColumn(int index) {
        int length = this.field[0].length;
        int column = ((index + 1) % length) - 1;
        if (column == (-1)) {
            column = 2;
        }
        return column;
    }

    //help method to find the right line to a given index
    private int findLine(int index) {
        int length = this.field[0].length;
        int line = index / length;
        return line;
    }

}
