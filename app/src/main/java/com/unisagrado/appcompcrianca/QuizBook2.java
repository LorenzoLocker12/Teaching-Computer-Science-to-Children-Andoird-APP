package com.unisagrado.appcompcrianca;

public class QuizBook2 {
    public static String[] questions = new String [] {
            "O cérebro do computador se chama CPU.",
            "A memória do computador é onde ele guarda todas as suas fotos e vídeos.",
            "Os computadores NÃO precisam de eletricidade para funcionar",
            "O teclado é a parte do computador onde você vê as imagens.",
            "Os computadores têm uma parte chamada \"placa-mãe\" que conecta todas as outras partes",
            "O mouse do computador é usado para mover o cursor na tela.",

    };

    public static int[] images = new int [] {
            R.drawable.cpu, R.drawable.memoria, R.drawable.computer, R.drawable.hard1,
            R.drawable.motherboard, R.drawable.hard2,
    };

    public static boolean[] answers = new boolean[]{
            true, true, false, false, true, true,
    };
}
