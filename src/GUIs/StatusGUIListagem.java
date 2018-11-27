package GUIs;

import DAOs.DAOAutor;
import Entidades.Autor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.ScrollPane;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class StatusGUIListagem extends JDialog {

    List<Autor> texto;
    JPanel painelTa = new JPanel();
    ScrollPane scroll = new ScrollPane();
    JTextArea ta = new JTextArea();
    int posX;
    int posY;

    public StatusGUIListagem(List<Autor> texto, int posX, int posY) {
        this.texto = texto;
        this.posX = posX;
        this.posY = posY;
        inicializar();
    }

    public StatusGUIListagem(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        inicializar();
    }

    private void inicializar() {
        List<Autor> texto = new DAOAutor().list();
        setTitle("Listagem de Autor");
        setSize(500, 180);//tamanho da janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        setModal(true);
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        JToolBar toolBar = new JToolBar();

        ta.setText("");
        for (int i = 0; i < texto.size(); i++) {
            ta.append(texto.get(i).toString() + System.lineSeparator());
        }

        scroll.add(ta);
        painelTa.add(scroll);

        cp.add(toolBar, BorderLayout.NORTH);
        cp.add(scroll, BorderLayout.CENTER);

        setLocation(posX + 20, posY + 20);
        setVisible(true);//faz a janela ficar visível        
    }
}
