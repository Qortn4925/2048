package com.example.game2048;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game2048 game = new Game2048();
            GameWindow window = new GameWindow(game);
            window.setVisible(true);
        });
    }
}
