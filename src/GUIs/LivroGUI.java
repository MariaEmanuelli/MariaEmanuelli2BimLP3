package GUIs;

import DAOs.DAOLivro;
import Entidades.Livro;
import tools.LivroGUIListagem;
import tools.MinhaJOptionPane;
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
import java.io.File;
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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import tools.JanelaPesquisar;
import tools.ManipulaArquivo;
import java.util.List;
import java.util.ArrayList;

public class LivroGUI extends JFrame {

    JButton btnCreate;
    JButton btnRetrieve;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSave;
    JButton btnCancel;
    JButton btnList;
    JLabel labelIdLivro = new JLabel("IdLivro");
    JTextField textFieldIdLivro = new JTextField();
    JLabel labelnomeLivro = new JLabel("NomeLivro");
    JTextField textFieldNomeLivro = new JTextField();
    JLabel labelEdicao = new JLabel("Edicao");
    JTextField textFieldEdicao = new JTextField();
    JLabel labelEditora = new JLabel("Editora");
    JTextField textFieldEditora = new JTextField();
    JLabel labelAnoPublicacao = new JLabel("AnoPublicacao");
    SimpleDateFormat sdfdataPublicacao = new SimpleDateFormat("dd/MM/yyyy");
    JTextField textFieldAnoPublicacao = new JTextField();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOLivro daoLivro = new DAOLivro();
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

    private void habilitarAtributos(boolean idLivro, boolean nomeLivro, boolean dataPublicacao) {

        if (idLivro) {
            textFieldIdLivro.requestFocus();
            textFieldIdLivro.selectAll();
        }
        textFieldIdLivro.setEnabled(idLivro);

        textFieldIdLivro.setEditable(idLivro);
        textFieldNomeLivro.setEditable(nomeLivro);
        textFieldAnoPublicacao.setEditable(dataPublicacao);
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
        habilitarAtributos(true, false, false);
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
        centro.setLayout(new GridLayout(3, 2));
        centro.add(labelIdLivro);
        centro.add(textFieldIdLivro);
        centro.add(labelEdicao);
        centro.add(textFieldNomeLivro);
        centro.add(labelAnoPublicacao);
        centro.add(textFieldAnoPublicacao);
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
                DAOLivro daoLivro = new DAOLivro();
                if (textFieldIdLivro.getText().equals("")) {
                    // DAOLivro daoLivro = new DAOLivro();
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
                        daoLivro = new DAOLivro();
//                         = daoLivro.obter(livro);

                        if (livro != null) { //se encontrou na lista                            
                            textFieldNomeLivro.setText(String.valueOf(livro.getNome()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false);
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
                habilitarAtributos(false, true, true);
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
                habilitarAtributos(false, true, true);
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
                    habilitarAtributos(true, false, false);
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
                habilitarAtributos(true, false, false);
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

        setVisible(true);//faz a janela ficar visível
        textFieldIdLivro.requestFocus();

    } //fim do construtor 

    public static void main(String[] args) {
        new StatusGUI(new Point(880, 250), new Dimension(800, 600));
    
    }
}//fim
