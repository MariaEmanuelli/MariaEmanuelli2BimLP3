package Main;

import GUIs.CRUDAutor;
import GUIs.CRUDCliente;
import GUIs.CRUDEditora;
import GUIs.CRUDEmprestimo;
import GUIs.CRUDEndereco;
import GUIs.CRUDGenero;
import GUIs.CRUDItensEmprestimo;
import GUIs.CRUDLivro;
import GUIs.CRUDStatus;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
//import myUtil.CentroDoMonitorMaior;

public class MenuPrincipal extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("Biblioteca Municipal");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudCliente = new JMenuItem("Cliente");
    private JMenuItem crudStatus = new JMenuItem("Status");
    private JMenuItem crudGenero = new JMenuItem("Gênero");
    private JMenuItem crudEditora = new JMenuItem("Editora");
    private JMenuItem crudAutor = new JMenuItem("Autor");
    
    private JMenuItem crudEmprestimo = new JMenuItem("Empréstimo");
    private JMenuItem crudLivro = new JMenuItem("Livro");
    private JMenuItem crudEndereco = new JMenuItem("Endereço");
    
    private JMenuItem crudItensEmprestimo = new JMenuItem("Itens do Empréstimo");
    
    public MenuPrincipal() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Biblioteca Municipal");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudCliente);
        menuCadastros.add(crudStatus);
        menuCadastros.add(crudGenero);
        menuCadastros.add(crudEditora);
        menuCadastros.add(crudAutor);
        menuCadastros.add(crudEmprestimo);
        menuCadastros.add(crudLivro);
        menuCadastros.add(crudEndereco);
        menuCadastros.add(crudItensEmprestimo);
        
        crudCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDCliente crudCliente1 = new CRUDCliente();
            }
        });

        crudStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDStatus crudStatus1 = new CRUDStatus();
            }
        });
        
        crudGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDGenero crudGenero1 = new CRUDGenero();
            }
        });
        
        crudEditora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDEditora crudEditora = new CRUDEditora();
            }
        });
        
        crudAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDAutor crudAutor1 = new CRUDAutor();
            }
        });

        crudEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDEmprestimo crudEmprestimo1 = new CRUDEmprestimo();
            }
        });

        crudLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDLivro crudLivro1 = new CRUDLivro();
            }
        });
        
        crudEndereco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDEndereco crudEndereco1 = new CRUDEndereco();
            }
        });
        
        crudItensEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDItensEmprestimo crudItensEmprestimo1 = new CRUDItensEmprestimo();
            }
        });
        
        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
