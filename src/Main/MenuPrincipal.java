package Main;

//import CrudTeste.TesteGUI;
//import CrudGenero.GeneroGUI;
import DAOs.DAOLivro1;
import GUIs.AutorGUI;
import GUIs.AutorGUIListagem;
import GUIs.ClienteGUI;
import GUIs.EditoraGUI;
import GUIs.EditoraGUIListagem;
import GUIs.GeneroGUI;
import GUIs.LivroGUI;
import GUIs.StatusGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MenuPrincipal extends JFrame {
    
    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JLabel lbTitulo = new JLabel("Biblioteca Municipal");
    
    Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    
    JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    
    JMenuBar menuBar = new JMenuBar();
    JMenu menuCadastros = new JMenu("Cadastros");
    JMenu menuListas = new JMenu("Listas");
    JMenuItem cadLivro = new JMenuItem("Livro");
    JMenuItem listLivro = new JMenuItem("Livro");
    JMenuItem cadEditora = new JMenuItem("Editora");
    JMenuItem listEditora = new JMenuItem("Editora");
    JMenuItem cadAutor = new JMenuItem("Autor");
    JMenuItem listAutor = new JMenuItem("Autor");
    JMenuItem cadGenero = new JMenuItem("Genero");
    JMenuItem listGenero = new JMenuItem("Genero");
    JMenuItem cadCliente = new JMenuItem("Cliente");
    JMenuItem listCliente = new JMenuItem("Cliente");
    JMenuItem cadEmprestimo = new JMenuItem("Emprestimo");
    JMenuItem listEmprestimo = new JMenuItem("Emprestimo");
    JMenuItem cadStatus = new JMenuItem("Status");
    JMenuItem listStatus = new JMenuItem("Status");
//    JMenu menuCompor = new JMenu("Composicoes");
//    JMenu menuDoc = new JMenu("Documentos");

//    JMenuItem cadItensVenda = new JMenuItem("Itens venda");
//    
//    JMenuItem cadPDFCliente = new JMenuItem("Lista de clientes");
//    JMenuItem cadPDFLivro = new JMenuItem("Lista de filmes");
    JMenu menuScreen = new JMenu("Posicionamento no monitor");
    JMenuItem posicaoNaJanela = new JMenuItem("Onde está...");
    Point p;
    
    public MenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle(lbTitulo.getText());
        
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);

        //para ajustar o tamanho de uma imagem
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/fotos/logo.png"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(674, 208, Image.SCALE_FAST));
            
            labelComImagemDeTamanhoDiferente = new JLabel();
            labelComImagemDeTamanhoDiferente.setIcon(icone);
        } catch (Exception e) {
            System.out.println("erro ao carregar a imagem");
        }

        cadLivro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LivroGUI livroGUI = new LivroGUI(p, dimensao);
            }
        });
        cadEditora.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EditoraGUI editoraGUI = new EditoraGUI(p, dimensao);
            }
        });
        listEditora.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EditoraGUIListagem editoraGUIListagem = new EditoraGUIListagem(100, 200);
            }
        });
        cadAutor.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                AutorGUI autorGUI = new AutorGUI(p, dimensao);
            }
        }
        );
        
        listAutor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AutorGUIListagem autorGUIListagem = new AutorGUIListagem(100,200);
            }
            
        });
        cadCliente.addActionListener(
                new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                ClienteGUI clienteGUI = new ClienteGUI(p, dimensao);
            }
        }
        );
        
        cadGenero.addActionListener(
                new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneroGUI generoGUI = new GeneroGUI();
            }
        }
        );
        
        cadStatus.addActionListener(
                new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusGUI statusGUI = new StatusGUI(p, dimensao);
            }
        }
        );

//        DAOCliente controleCliente = new DAOCliente();
//        cadPDFCliente.addActionListener(new ActionListener() {
//            @Override
//        public void actionPerformed(ActionEvent e) {
//                Document documentoPDF = new Document();
//
//                try {
//                    PdfWriter.getInstance(documentoPDF, new FileOutputStream("D:\\PDF_Cliente.pdf"));
//                    documentoPDF.open();
//                    documentoPDF.setPageSize(PageSize.A4);
//                    documentoPDF.add(new Paragraph("Lista de clientes"));
//                    for (int i = 0; i < controleCliente.listStrings().size(); i++) {
//                        documentoPDF.add(new Paragraph(controleCliente.listStrings().get(i)));
//                    }
//                } catch (DocumentException de) {
//                    de.printStackTrace();
//                } catch (IOException ioe) {
//                    ioe.printStackTrace();
//                } finally {
//                    documentoPDF.close();
//                }
//            }
//        });
//
//        DAOLivro1 controleLivro = new DAOLivro1();
//        cadPDFLivro.addActionListener(new ActionListener() {
//            @Override
//        public void actionPerformed(ActionEvent e) {
//                Document documentoPDF = new Document();
//
//                try {
//                    PdfWriter.getInstance(documentoPDF, new FileOutputStream("D:\\PDF_Livro.pdf"));
//                    documentoPDF.open();
//                    documentoPDF.setPageSize(PageSize.A4);
//                    documentoPDF.add(new Paragraph("Lista de filmes"));
//                    for (int i = 0; i < controleLivro.listStrings().size(); i++) {
//                        documentoPDF.add(new Paragraph(controleLivro.listStrings().get(i)));
//                    }
//                } catch (DocumentException de) {
//                    de.printStackTrace();
//                } catch (IOException ioe) {
//                    ioe.printStackTrace();
//                } finally {
//                    documentoPDF.close();
//                }
//            }
//        });
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);
        
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        
        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuBar.add(menuListas);
//        menuBar.add(menuDoc);

        menuCadastros.add(cadLivro);
        menuCadastros.add(cadGenero);
        menuCadastros.add(cadEditora);
        menuCadastros.add(cadCliente);
        menuCadastros.add(cadAutor);
        menuCadastros.add(cadStatus);
        menuCadastros.add(cadEmprestimo);
        
        menuListas.add(listAutor);
        menuListas.add(listCliente);
        menuListas.add(listEmprestimo);
        menuListas.add(listEditora);
        menuListas.add(listGenero);
        menuListas.add(listLivro);
        menuListas.add(listStatus);
        

//        menuDoc.add(cadPDFCliente);
//        menuDoc.add(cadPDFLivro);
//
//        menuBar.add(menuCompor);
//
//        menuCompor.add(cadItensVenda);
        menuScreen.setVisible(false); //mostrar se precisar ajustar a posicao na tela
        menuBar.add(menuScreen);
        
        menuScreen.add(posicaoNaJanela);
        
        posicaoNaJanela.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("X " + getBounds().x);
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                System.exit(0);
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
}
