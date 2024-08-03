package com.unisagrado.appcompcrianca;

public class QuizBook {
    public static String[] questions = new String [] {
            "A imagem abaixo (teclado) é um hardware ou software?",
            "A imagem abaixo (mouse) é um hardware ou software?",
            "A imagem abaixo (notebook) é um hardware ou software?",
            "A imagem abaixo (celular) é um hardware ou software?",
            "A imagem abaixo (arduino) é um hardware ou software?",
            "A imagem abaixo (google) é um hardware ou software?",
            "A imagem abaixo (spotify) é um hardware ou software?",
            "A imagem abaixo (instagram) é um hardware ou software?",
            "A imagem abaixo (duolingo) é um hardware ou software?",
    };

    public static int[] images = new int [] {
            R.drawable.hard1, R.drawable.hard2, R.drawable.hard3, R.drawable.hard4,
            R.drawable.hard5, R.drawable.soft1, R.drawable.soft5, R.drawable.soft3,
            R.drawable.soft4
    };

    public static boolean[] answers = new boolean[]{
            true, true, true, true, true, false, false, false, false
    };
}
