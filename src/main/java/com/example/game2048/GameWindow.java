package com.example.game2048;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

/** 창, 점수 표시, 키보드 입력 연결을 담당합니다. */
public class GameWindow extends JFrame {
    private final Game2048 game;
    private final BoardPanel boardPanel;
    private final JLabel scoreLabel;
    private final JLabel statusLabel;

    public GameWindow(Game2048 game) {
        super("2048 - Java Swing Practice");
        this.game = game;
        this.boardPanel = new BoardPanel(game);
        this.scoreLabel = new JLabel();
        this.statusLabel = new JLabel("방향키 / WASD 이동 | R 재시작");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        JPanel content = new JPanel(new BorderLayout(0, 14));
        content.setBackground(new Color(0xFAF8EF));
        content.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        setContentPane(content);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("2048");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 46));
        title.setForeground(new Color(0x776E65));
        scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        scoreLabel.setForeground(new Color(0x776E65));
        header.add(title, BorderLayout.WEST);
        header.add(scoreLabel, BorderLayout.EAST);

        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        statusLabel.setForeground(new Color(0x776E65));
        content.add(header, BorderLayout.NORTH);
        content.add(boardPanel, BorderLayout.CENTER);
        content.add(statusLabel, BorderLayout.SOUTH);

        bindKeys();
        updateView();
        pack();
        setLocationRelativeTo(null);
    }


    private void bindKeys() {
        InputMap keys = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = getRootPane().getActionMap();

        bindDirection(keys, actions, "UP", Direction.UP);
        bindDirection(keys, actions, "W", Direction.UP);
        bindDirection(keys, actions, "DOWN", Direction.DOWN);
        bindDirection(keys, actions, "S", Direction.DOWN);
        bindDirection(keys, actions, "LEFT", Direction.LEFT);
        bindDirection(keys, actions, "A", Direction.LEFT);
        bindDirection(keys, actions, "RIGHT", Direction.RIGHT);
        bindDirection(keys, actions, "D", Direction.RIGHT);

        keys.put(KeyStroke.getKeyStroke("pressed R"), "reset");
        actions.put("reset", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                game.reset();
                statusLabel.setText("초기화됨 (현재는 화면 확인용 고정 타일)");
                updateView();
            }
        });
    }

    private void bindDirection(InputMap keys, ActionMap actions, String key, Direction direction) {
        String name = "move-" + key;
        keys.put(KeyStroke.getKeyStroke("pressed " + key), name);
        actions.put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                game.move(direction);
                statusLabel.setText(direction + " 입력됨 | 이동 로직은 Game2048.move()에서 구현");
                updateView();
            }
        });
    }

    private void updateView() {
        scoreLabel.setText("SCORE  " + game.score());
        boardPanel.repaint();
        if(game.isGameOver()){
            statusLabel.setText("GAME OVER! R키를 눌러 재시작");
        }
    }
}
