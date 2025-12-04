import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {
    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private static final String NAME = "Tic Tac Toe";
    private static TTT ttt = new TTT();
    private static boolean playerMove = true;
    
    private JFrame frame;
    private JLabel resultLabel;
    private JPanel choicesPanel;

    public GUI() {
        createFrame();
        initializeComponents();
        displayFrame();
        runGame();
    }

    public JFrame getFrame() {
        return frame;
    }
    private void runGame() {
        boolean gameWin = false;
        while (!gameWin) {
            if (playerMove) {
                resultLabel.setText("Your Turn!");
            } else {
                resultLabel.setText("AI's Turn!");
                int[] com = ttt.bestMove(ttt.board, false);
                ttt.makeMove(com[0], com[1], false);
                for (int i = 0; i<=2; i++) {
                    System.out.println(ttt.getSquare(i, 0) + " " + ttt.getSquare(i, 1) + " " + ttt.getSquare(i, 2) + " ");
                }
                playerMove=true;
            }
            int r = ttt.solve(ttt.board);
            if ((r == 1 || r==-1) || (r==0 && !ttt.isMovesLeft())) {
                gameWin=true;
                updateChoiceButtons();
                if (r == 1) {
                    resultLabel.setText("You Won!");
                } else if (r == -1) {
                    resultLabel.setText("You Lost!");
                } if (r == 0) {
                    resultLabel.setText("Draw!");
                }
            } else {
                updateChoiceButtons();
            }
            
        }
    }
    private void createFrame() {
        frame = new JFrame(NAME);
        frame.setLocation(X_LOC, Y_LOC);
        frame.setSize(WIDTH, HEIGHT);
        //frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        initializeCurrentOrderLabel();
        updateChoiceButtons();
    }

    private void displayFrame() {
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setVisible(true);
    }

    private void initializeCurrentOrderLabel() {
        resultLabel = new JLabel();
        resultLabel.setText("Player: X  AI: O");
        resultLabel.setSize(250,500);
        resultLabel.setName("resultLabel");
        resultLabel.setBackground(Color.gray);
        resultLabel.setOpaque(true);
        resultLabel.setForeground(Color.WHITE);
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(Color.GRAY);
        resultPanel.add(resultLabel);

        frame.add(resultPanel, BorderLayout.NORTH);
    }

    private void updateChoiceButtons() {
        JButton button00 = new JButton(ttt.getSquare(0,0));
        JButton button01 = new JButton(ttt.getSquare(0,1));
        JButton button02 = new JButton(ttt.getSquare(0,2));
        JButton button10 = new JButton(ttt.getSquare(1,0));
        JButton button11 = new JButton(ttt.getSquare(1,1));
        JButton button12 = new JButton(ttt.getSquare(1,2));
        JButton button20 = new JButton(ttt.getSquare(2,0));
        JButton button21 = new JButton(ttt.getSquare(2,1));
        JButton button22 = new JButton(ttt.getSquare(2,2));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        button00.setToolTipText(ttt.getSquare(0, 0));
        button01.setToolTipText(ttt.getSquare(0, 1));
        button02.setToolTipText(ttt.getSquare(0, 2));
        button10.setToolTipText(ttt.getSquare(1, 0));
        button11.setToolTipText(ttt.getSquare(1, 1));
        button12.setToolTipText(ttt.getSquare(1, 2));
        button20.setToolTipText(ttt.getSquare(2, 0));
        button21.setToolTipText(ttt.getSquare(2, 1));
        button22.setToolTipText(ttt.getSquare(2, 2));
        buttonPanel.add(button00);
        buttonPanel.add(button01);
        buttonPanel.add(button02);
        buttonPanel.add(button10);
        buttonPanel.add(button11);
        buttonPanel.add(button12);
        buttonPanel.add(button20);
        buttonPanel.add(button21);
        buttonPanel.add(button22);

        frame.add(buttonPanel, BorderLayout.CENTER);

        button00.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button00.setText("X");
                ttt.makeMove(0, 0, true);
                playerMove=ttt.state;
                button00.revalidate();
                button00.repaint();
            }
        });
        button01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button01.setText("X");
                ttt.makeMove(0, 1, true);
                playerMove=ttt.state;
                button01.revalidate();
                button01.repaint();
            }
        });
        button02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button02.setText("X");
                ttt.makeMove(0, 2, true);
                playerMove=ttt.state;
                button02.revalidate();
                button02.repaint();
            }
        });
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button10.setText("X");
                ttt.makeMove(1, 0, true);
                playerMove=ttt.state;
                button10.revalidate();
                button10.repaint();
            }
        });
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button11.setText("X");
                ttt.makeMove(1, 1, true);
                playerMove=ttt.state;
                button11.revalidate();
                button11.repaint();
            }
        });
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button12.setText("X");
                ttt.makeMove(1, 2, true);
                playerMove=ttt.state;
                button12.revalidate();
                button12.repaint();
            }
        });
        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button20.setText("X");
                ttt.makeMove(2, 0, true);
                playerMove=ttt.state;
                button20.revalidate();
                button20.repaint();
            }
        });
        button21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button21.setText("X");
                ttt.makeMove(2, 1, true);
                playerMove=ttt.state;
                button21.revalidate();
                button21.repaint();
            }
        });
        button22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button22.setText("X");
                ttt.makeMove(2, 2, true);
                playerMove=ttt.state;
                button22.revalidate();
                button22.repaint();
            }
        });
        buttonPanel.revalidate();
        buttonPanel.repaint();
    
    }
    public static void main(String[] args) {
        
        GUI g = new GUI();
    }
}