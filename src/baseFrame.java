import javax.swing.*;
import java.awt.*;

/**
 * Modelo da ‘interface’ Gráfica do projeto,
 * esta oferece algumas indicações basicas sobre como deve parecer uma janela deste ‘software’
 */
public class baseFrame extends JFrame {

    public baseFrame() throws HeadlessException {

        this.setTitle("Sistema de Gestão de Tráfego Ferroviário");//Define o Titulo da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Define que o programa acaba quando o user decide fechar a janela
        this.setResizable(false);//Não permite ao user mudar o tamanho da janela
        this.setSize(800,600);//Comprimento e Largura da Janela
        this.setVisible(true);//A janela é visivel para o utilizador



        ImageIcon logo = new ImageIcon("src\\img\\logo.png");//Imagem do logo
        this.setIconImage(logo.getImage());//Define o logo como o icon da janela
        this.getContentPane().setBackground(new Color(36,37,38));//Define a Cor de fundo
    }
}
