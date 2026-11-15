package com.example.game2048;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/** 화면을 그리기만 합니다. 이동 규칙은 여기서 구현하지 마세요. */
public class BoardPanel extends JPanel {
    private static final int BOARD_SIZE = 420;
    private static final int PADDING = 12;
    private static final int GAP = 10;
    private static final int GRID_SIZE = 4;

    private final Game2048 game;

    public BoardPanel(Game2048 game) {
        this.game = game;
        setOpaque(false);
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int size = Math.min(getWidth(), getHeight());
            int originX = (getWidth() - size) / 2;
            int originY = (getHeight() - size) / 2;
            int tileSize = (size - 2 * PADDING - (GRID_SIZE - 1) * GAP) / GRID_SIZE;

            g2.setColor(new Color(0xBBADA0));
            g2.fillRoundRect(originX, originY, size, size, 12, 12);

            int[][] board = game.boardSnapshot();
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    int value = board[row][col];
                    int x = originX + PADDING + col * (tileSize + GAP);
                    int y = originY + PADDING + row * (tileSize + GAP);

                    g2.setColor(tileColor(value));
                    g2.fillRoundRect(x, y, tileSize, tileSize, 10, 10);

                    if (value != 0) {
                        g2.setColor(value <= 4 ? new Color(0x776E65) : Color.WHITE);
                        int fontSize = value >= 1024 ? 29 : value >= 128 ? 34 : 40;
                        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
                        FontMetrics metrics = g2.getFontMetrics();
                        String number = String.valueOf(value);
                        int tx = x + (tileSize - metrics.stringWidth(number)) / 2;
                        int ty = y + (tileSize - metrics.getHeight()) / 2 + metrics.getAscent();
                        g2.drawString(number, tx, ty);
                    }
                }
            }
        } finally {
            g2.dispose();
        }
    }

    private Color tileColor(int value) {
        return switch (value) {
            case 0 -> new Color(0xCDC1B4);
            case 2 -> new Color(0xEEE4DA);
            case 4 -> new Color(0xEDE0C8);
            case 8 -> new Color(0xF2B179);
            case 16 -> new Color(0xF59563);
            case 32 -> new Color(0xF67C5F);
            case 64 -> new Color(0xF65E3B);
            case 128 -> new Color(0xEDCF72);
            case 256 -> new Color(0xEDCC61);
            case 512 -> new Color(0xEDC850);
            case 1024 -> new Color(0xEDC53F);
            default -> new Color(0xEDC22E);
        };
    }
}
