public class Computer {
    //calculate the next move
    public int nextMove(Field field) {
        if (field.wouldWin('o') != -1) {
            return field.wouldWin('o');
        }
        if (field.wouldWin('x') != -1) {
            return field.wouldWin('x');
        }
        if (!(field.isNotFree(4))) {
            return 4;
        }
        int[] corner = {0,2,6,8};
        for (int i = 0; i < corner.length; i++) {
            if (!(field.isNotFree(corner[i]))) {
                return corner[i];
            }
        }
        int index = (2 * field.getField()[0].length);
        for (int i = 0; i < index; i++) {
            return index;
        }

        return -1;
    }
}
