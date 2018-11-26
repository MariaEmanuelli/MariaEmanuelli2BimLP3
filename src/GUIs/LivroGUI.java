package GUIs;

import DAOs.DAOAutor;
import DAOs.DAOEditora;
import DAOs.DAOGenero;
import DAOs.DAOLivro1;
import Entidades.Livro;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.text.SimpleDateFormat;
import tools.ManipulaArquivo;
import java.util.List;
import javax.swing.JDialog;

public class LivroGUI extends JDialog {

    JButton btnCreate;
    JButton btnRetrieve;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSave;
    JButton btnCancel;
    JButton btnList;
    JLabel labelIdLivro = new JLabel("IdLivro");
    JTextField textFieldIdLivro = new JTextField();
    JLabel labelNomeLivro = new JLabel("NomeLivro");
    JTextField textFieldNomeLivro = new JTextField();
    JLabel labelEdicao = new JLabel("Edicao");
    JTextField textFieldEdicao = new JTextField();
    JLabel labelEditora = new JLabel("Editora");
    JTextField textFieldEditora = new JTextField();
    JLabel labelAnoPublicacao = new JLabel("AnoPublicacao");
    SimpleDateFormat sdfdataPublicacao = new SimpleDateFormat("dd/MM/yyyy");
    JTextField textFieldAnoPublicacao = new JTextField();
    JLabel labelQntEstoque = new JLabel("quantidadeEstoque");
    JTextField textFieldQntEstoque = new JTextField();
    JLabel labelAutor = new JLabel("Autor");
    JTextField textFieldAutor = new JTextField();
    JLabel labelGenero = new JLabel("Genero");
    JTextField textFieldGenero = new JTextField();
    JLabel labelStatus = new JLabel("Status");
    JTextField textFieldStatus = new JTextField();
    
            
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOLivro1 daoLivro = new DAOLivro1();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Livro livro = new Livro();
    ManipulaArquivo file = new ManipulaArquivo();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idLivro, boolean nomeLivro, boolean dataPublicacao, boolean edicao,
            boolean editora, boolean autor, boolean qntEstoque, boolean genero, boolean status) {

        if (idLivro) {
            textFieldIdLivro.requestFocus();
            textFieldIdLivro.selectAll();
        }
        textFieldIdLivro.setEnabled(idLivro);

        textFieldIdLivro.setEditable(idLivro);
        textFieldNomeLivro.setEditable(nomeLivro);
        textFieldAnoPublicacao.setEditable(dataPublicacao);
        textFieldEdicao.setEditable(edicao);
        textFieldEditora.setEditable(editora);
        textFieldAutor.setEditable(autor);
        textFieldGenero.setEditable(genero);
        textFieldStatus.setEditable(status);
        textFieldQntEstoque.setEditable(qntEstoque);
    }

    public void zerarAtributos() {
        textFieldNomeLivro.setText("");
        textFieldAnoPublicacao.setText("");
    }

    public LivroGUI(Point posicao, Dimension dimensao) {
        btnCreate = new JButton("Criar");
        btnRetrieve = new JButton("Buscar");
        btnUpdate = new JButton("Atualizar");
        btnDelete = new JButton("Remover");
        btnSave = new JButton("Salvar");
        btnCancel = new JButton("Cancelar");
        btnList = new JButton("Listar");

        sdfdataPublicacao.setLenient(false);
//OBSERVAR O CONSTRUTOR DA ENTIDADE
        setTitle("Cadastro de Livro");
        setSize(800, 600);//tamanho da janela
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(9, 2));
        centro.add(labelIdLivro);
        centro.add(textFieldIdLivro);
        centro.add(labelNomeLivro);
        centro.add(textFieldNomeLivro);
        centro.add(labelAnoPublicacao);
        centro.add(textFieldAnoPublicacao);
        centro.add(labelAutor);
        centro.add(textFieldAutor);
        centro.add(labelEdicao);
        centro.add(textFieldEdicao);
        centro.add(labelEditora);
        centro.add(textFieldEditora);
        centro.add(labelGenero);
        centro.add(textFieldGenero);
        centro.add(labelQntEstoque);
        centro.add(textFieldQntEstoque);
        centro.add(labelStatus);
        centro.add(textFieldStatus);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldIdLivro.requestFocus();
        textFieldIdLivro.selectAll();
        textFieldIdLivro.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");

        // Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Livro livro = new Livro();
                textFieldIdLivro.setText(textFieldIdLivro.getText().trim());//caso tenham sido digitados espaços
                DAOLivro1 daoLivro = new DAOLivro1();
                if (textFieldIdLivro.getText().equals("")) {
                    // DAOLivro1 daoLivro = new DAOLivro1();
                    List<String> listaAuxiliar = daoLivro.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new myUtil.JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdLivro.setText(aux[0]);
                            textFieldAnoPublicacao.setText(aux[2]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdLivro.requestFocus();
                            textFieldIdLivro.selectAll();
                        }
                    }

                    textFieldIdLivro.requestFocus();
                    textFieldIdLivro.selectAll();
                } else {
                    try {
                        livro = new Livro();
                        livro.setId(Integer.valueOf(textFieldIdLivro.getText()));
                        livro.setAnoPublicacao(sdf.parse(textFieldAnoPublicacao.getText()));
                        daoLivro = new DAOLivro1();
//                         = daoLivro.obter(livro);

                        if (livro != null) { //se encontrou na lista                            
                            textFieldNomeLivro.setText(String.valueOf(livro.getNome()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {  //não achou na lista
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdLivro.setBackground(Color.green);
                        textFieldAnoPublicacao.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdLivro.setOpaque(true);
                        textFieldIdLivro.selectAll();
                        textFieldIdLivro.requestFocus();
                        textFieldIdLivro.setBackground(Color.red);
                        textFieldAnoPublicacao.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });
//        btnRetrieve.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                try {
//                    textFieldIdLivro.setText(textFieldIdLivro.getText().trim());//caso tenham sido digitados espaços
//                    if (textFieldIdLivro.getText().equals("")) {
//                        MinhaJOptionPane myJOptionPane
//                                = new MinhaJOptionPane(
//                                        new Point(getBounds().x + (int) (getBounds().getWidth() / 2),
//                                                getBounds().y + (int) (getBounds().getHeight() / 2)),
//                                        "Deve ser informado um valor para esse campo");
//                        if (myJOptionPane.getValorRetornado()) {
//                            textFieldIdLivro.requestFocus();
//                            textFieldIdLivro.selectAll();
//                        }
//                    } else {
//                        livro = new Livro();
//                        livro.setId(Integer.valueOf(textFieldIdLivro.getText()));
//                        livro = daoLivro.obter(livro.getId());
//                        if (livro != null) { //se encontrou na lista
//
//                            textFieldNomeLivro.setText(String.valueOf(livro.getNome()));
//                            textFieldAnoPublicacao.setText(sdfdataPublicacao.format(livro.getAnoPublicacao()));
//                            atvBotoes(false, true, true, true);
//                            habilitarAtributos(true, false, false);
//                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
//                            acao = "encontrou";
//                        } else {
//                            atvBotoes(true, true, false, false);
//                            zerarAtributos();
//                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra placa [Pesquisar]");
//                        }
//                    }
//                } catch (Exception err) {
//                    System.out.println(err);
//                    labelAviso.setText("Erro nos dados");
//                    labelAviso.setOpaque(true);
//                    labelAviso.setBackground(Color.red);
//                }
//            }
//        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true, true, true, true, true, true);
                textFieldNomeLivro.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + livro.getId() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoLivro.remover(livro);
                    zerarAtributos();
                    textFieldIdLivro.requestFocus();
                    textFieldIdLivro.selectAll();
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Livro livroNovo = new Livro();
                    livroNovo.setId(Integer.valueOf(textFieldIdLivro.getText()));
                    livroNovo.setNome(String.valueOf(textFieldNomeLivro.getText()));
                    livroNovo.setAnoPublicacao(sdfdataPublicacao.parse(textFieldAnoPublicacao.getText()));
                    if (acao.equals("insert")) {
                        daoLivro.inserir(livroNovo);
                        labelAviso.setText("Registro inserido...");

                    } else {  //acao = update
                        daoLivro.atualizar(livroNovo);
                        labelAviso.setText("Registro atualizado...");
                    }
                    habilitarAtributos(true, false, false, false, false, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                } catch (Exception err) {
                    System.out.println(err);
                    labelAviso.setText("Erro nos dados");
                    labelAviso.setOpaque(true);
                    labelAviso.setBackground(Color.red);
                }
            }

        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false, false, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                LivroGUIListagem listagem = new LivroGUIListagem(daoLivro.list(), getBounds().x, getBounds().y);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        textFieldEditora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = new DAOEditora().listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new myUtil.JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldEditora.setText(aux[0]);
                        } 
                    }
            }
        });
        
        textFieldAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = new DAOAutor().listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new myUtil.JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldAutor.setText(aux[0]);
                        } 
                    }
                }
        });
        
        textFieldGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                  List<String> listaAuxiliar = new DAOGenero().listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new myUtil.JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldGenero.setText(aux[0]);
                        } 
                    } 
            }
        });
        
        
        textFieldIdLivro.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdLivro.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdLivro.setBackground(Color.white);
            }
        });

        textFieldNomeLivro.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeLivro.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeLivro.setBackground(Color.white);
            }
        });

        textFieldAnoPublicacao.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldAnoPublicacao.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldAnoPublicacao.setBackground(Color.white);
            }
        });
        
        textFieldStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
            }
        });
        
        
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                dispose();
            }
        });
        setModal(true);
        setVisible(true);//faz a janela ficar visível
        textFieldIdLivro.requestFocus();

    } //fim do construtor 

    public static void main(String[] args) {
        new LivroGUI(new Point(880, 250), new Dimension(800, 600));

    }
}//fim
