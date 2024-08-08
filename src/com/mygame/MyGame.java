package com.mygame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class MyGame extends JFrame implements ActionListener {
  JLabel heading,clockLabel;
  Font font = new Font("",Font.PLAIN,40);
  JPanel mainPanel;

  JButton[] btns = new JButton[9];


  //Game instance variable

    int gameChances[] = { 2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;

    int winning[][] = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
    int winner =2;
    boolean gameover = false;




    MyGame(){
        setTitle("Tic Tac Toe");
        setSize(600,700);
        ImageIcon icon = new ImageIcon("src/image/tic.jpg");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }


    private void createGUI(){

        this.getContentPane().setBackground(Color.black);
        this.setLayout(new BorderLayout());


        //North Heading
        heading = new JLabel("Tic Tca Toe");
        heading.setIcon(new ImageIcon("src/image/Taac.jpg"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);



        this.add(heading,BorderLayout.NORTH);



        //Creating object of JLabel for clock
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.white);

        this.add(clockLabel,BorderLayout.SOUTH);

        Thread t= new Thread() {
            public void run(){
                 try{
                     while(true) {
                         String datetime =new Date().toLocaleString();
                         clockLabel.setText(datetime);
                         Thread.sleep(1000);
                     }
                 }

                 catch (Exception e){
                     e.printStackTrace();
                 }
            }
        };
        t.start();



        //Panel Section
        mainPanel = new JPanel();

        mainPanel.setLayout((new GridLayout(3,3)));

        for(int i=1; i <= 9; i++){
            JButton btn = new JButton();
            //btn.setIcon(new ImageIcon("src/image/Zeero.jpg"));
            btn.setBackground(Color.CYAN);

            btn.setFont(font);
            mainPanel.add(btn);
            btns[i -1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i -1));
        }

        this.add(mainPanel,BorderLayout.CENTER);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton = (JButton) e.getSource();

        String nameStr = currentButton.getName();



        int name = Integer.parseInt(nameStr.trim());

        if(gameover)
        {
            JOptionPane.showMessageDialog(this, "Game Already Over");
            return;
        }



        if(gameChances[name] == 2)
        {
            if (activePlayer == 1)
            {
                currentButton.setIcon(new ImageIcon("src/image/On.jpg "));
                gameChances[name] = activePlayer;
                activePlayer =0;
            }
            else
            {
                currentButton.setIcon(new ImageIcon("src/image/Zeero.jpg"));
                gameChances[name] =activePlayer;
                activePlayer=1;
            }


            //Find The Winner.........
            for(int []temp:winning)
            {
                if((gameChances[temp[0]] == gameChances[temp[1]]) && (gameChances[temp[1]] == gameChances[temp[2]]) && (gameChances[temp[2]] != 2))
                {
                    winner = gameChances[temp[0]];
                    gameover = true;
                    JOptionPane.showMessageDialog(null, "Congratulations!!!   Player " + winner + " has won the game..");
                    int i = JOptionPane.showConfirmDialog(this,"Do you want to Play again??");
                    if(i == 0)
                    {
                        this.setVisible(false);
                        new MyGame();
                    }
                    else if (i == 1) {
                        System.exit(4456);
                    }
                    else{

                    }
                    System.out.println(i);
                    break;
                }
            }



            //Math tie or Draw Case
            int count = 0;

            for(int x:gameChances)
            {
                if(x ==2)
                {
                    count++;
                    break;
                }
            }

            if(count==0  && gameover==false)
            {
                JOptionPane.showMessageDialog(null,"Match is Draw...");
                int i  = JOptionPane.showConfirmDialog(this,"Want to Play more ?? ");

                if(i == 0)
                {
                    this.setVisible(false);

                    new MyGame();
                } else if (i ==1) {
                    System.exit(43132);

                }
                else{

                }

                gameover = true;
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this,"Position Already Occupied");
        }




    }
}
