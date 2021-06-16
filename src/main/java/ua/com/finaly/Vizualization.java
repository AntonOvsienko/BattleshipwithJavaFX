package ua.com.finaly;

public class Vizualization {
    private static String ship="⛵";
    private static String empty="\u2B1C";
    private static String shipEmpty="\u2BBD";
    private static String shipHit="\u2B1B";
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";

    public static void Aura(int[] massivfinal, Anketa player) {
        int[][] aura = player.getField();
        for (int k = 0; k < massivfinal.length/2; k++) {
            for (int i = massivfinal[0 + k * 2] - 1; i < massivfinal[0 + k * 2] + 2; i++) {
                for (int j = massivfinal[1 + k * 2] - 1; j < massivfinal[1 + k * 2] + 2; j++) {
                    if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                        if (aura[i][j] != 1 && aura[i][j] != 3) {
                            aura[i][j] = 4;
                        }
                    }
                }
            }
        }
        player.setField(aura);
    }

}

