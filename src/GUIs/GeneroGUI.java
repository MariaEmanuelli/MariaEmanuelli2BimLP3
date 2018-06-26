package GUIs;

import DAOs.DAOGenero;
import Entidades.Genero;
import tools.GeneroGUIListagem;
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

public class GeneroGUI extends JFrame {

    JButton btnCreate;
    JButton btnRetrieve;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSave;
    JButton btnCancel;
    JButton btnList;
    JLabel labelIdGenero = new JLabel("IdGenero");
    JTextField textFieldIdGenero = new JTextField();
    JLabel labelNomeGenero = new JLabel("NomeGenero");
    JTextField textFieldNomeGenero = new JTextField();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update 

    DAOGenero daoGenero = new DAOGenero();
    Genero genero = new Genero();
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

    private void habilitarAtributos(boolean idGenero, boolean nomeGenero) {

        if (idGenero) {
            textFieldIdGenero.requestFocus();
            textFieldIdGenero.selectAll();
        }
        textFieldIdGenero.setEnabled(idGenero);

        textFieldIdGenero.setEditable(idGenero);
        textFieldNomeGenero.setEditable(nomeGenero);
    }

    public void zerarAtributos() {
        textFieldNomeGenero.setText("");
    }

    public GeneroGUI(Point posicao, Dimension dimensao) {
        btnCreate = new JButton("Criar");
        btnRetrieve = new JButton("Buscar");
        btnUpdate = new JButton("Atualizar");
        btnDelete = new JButton("Remover");
        btnSave = new JButton("Salvar");
        btnCancel = new JButton("Cancelar");
        btnList = new JButton("Listar");

//OBSERVAR O CONSTRUTOR DA ENTIDADE
        setTitle("Cadastro de Genero");
        setSize(800, 600);//tamanho da janela
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false);
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
        centro.setLayout(new GridLayout(2, 2));
        centro.add(labelIdGenero);
        centro.add(textFieldIdGenero);
        centro.add(labelNomeGenero);
        centro.add(textFieldNomeGenero);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldIdGenero.requestFocus();
        textFieldIdGenero.selectAll();
        textFieldIdGenero.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");

        // Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    textFieldIdGenero.setText(textFieldIdGenero.getText().trim());//caso tenham sido digitados espaços
                    if (textFieldIdGenero.getText().equals("")) {
                        MinhaJOptionPane myJOptionPane
                                = new MinhaJOptionPane(
                                        new Point(getBounds().x + (int) (getBounds().getWidth() / 2),
                                                getBounds().y + (int) (getBounds().getHeight() / 2)),
                                        "Deve ser informado um valor para esse campo");
                        if (myJOptionPane.getValorRetornado()) {
                            textFieldIdGenero.requestFocus();
                            textFieldIdGenero.selectAll();
                        }
                    } else {
                        genero = new Genero();
                        genero.setIdGenero(Integer.valueOf(textFieldIdGenero.getText()));
                        genero = daoGenero.obter(genero.getIdGenero());
                        if (genero != null) { //se encontrou na lista

                            textFieldNomeGenero.setText(String.valueOf(genero.getNomeGenero()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra placa [Pesquisar]");
                        }
                    }
                } catch (Exception err) {
                    System.out.println(err);
                    labelAviso.setText("Erro nos dados");
                    labelAviso.setOpaque(true);
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true);
                textFieldNomeGenero.requestFocus();
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
                habilitarAtributos(false, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + genero.getIdGenero() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoGenero.remover(genero);
                    zerarAtributos();
                    textFieldIdGenero.requestFocus();
                    textFieldIdGenero.selectAll();
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Genero generoNovo = new Genero();
                    generoNovo.setIdGenero(Integer.valueOf(textFieldIdGenero.getText()));
                    generoNovo.setNomeGenero(String.valueOf(textFieldNomeGenero.getText()));
                    if (acao.equals("insert")) {
                        daoGenero.inserir(generoNovo);
                        labelAviso.setText("Registro inserido...");

                    } else {  //acao = update
                        daoGenero.atualizar(generoNovo);
                        labelAviso.setText("Registro atualizado...");
                    }
                    habilitarAtributos(true, false);
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
                habilitarAtributos(true, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GeneroGUIListagem listagem = new GeneroGUIListagem(daoGenero.list(), getBounds().x, getBounds().y);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco

        textFieldIdGenero.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdGenero.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdGenero.setBackground(Color.white);
            }
        });

        textFieldNomeGenero.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeGenero.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeGenero.setBackground(Color.white);
            }
        });

        setVisible(true);//faz a janela ficar visível
        textFieldIdGenero.requestFocus();

    } //fim do construtor 

    public static void main(String[] args) {
        new StatusGUI(new Point(880, 250), new Dimension(800, 600));
    
    }
}//fim
