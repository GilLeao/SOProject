import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class login {
    public login(){
        baseFrame frame = new baseFrame();
        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        frame.setIconImage(logo.getImage());//Define o logo como o icon da janela
        frame.getContentPane().setBackground(new Color(166, 237, 128));//Define a Cor de fundo

        JPanel panel = new JPanel();//NOVO PAINEL
        panel.setLayout(null);
        panel.setBackground(new Color(64,64,64));
        panel.setBounds(70,90,250,300);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);


        frame.add(panel);

        JLabel fazLoginTexto = new JLabel("FAÇA LOGIN!");
        fazLoginTexto.setBounds(70,60, 200, 25);
        fazLoginTexto.setForeground(Color.WHITE);
        fazLoginTexto.setFont(new Font("Verdana", Font.BOLD, 15));

        panel.add(fazLoginTexto);

        JTextField user = new JFormattedTextField();
        user.setBounds(25,100, 200, 25);
        user.setVisible(true);
        user.setText("USER");
        panel.add(user);


        JPasswordField pw = new JPasswordField();
        pw.setBounds(25,140, 200, 25);
        pw.setVisible(true);
        pw.setText("PASSWORD");
        panel.add(pw);


        JButton login = new JButton();
        login.setBounds(70,200, 100, 25);
        login.addActionListener(e ->{
            User userLog = new User();
            String userString = user.getText();
            String pwString = new String(pw.getPassword());
            try {
                if(userLog.checkCredentials(userString, pwString)){
                    Main.mainFrames();
                    frame.dispose();
                }else{
                    frame.repaint();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        login.setText("LOGIN");
        panel.add(login);

        panel.repaint();



    }



}
