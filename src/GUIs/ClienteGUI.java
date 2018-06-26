package GUIs;

import DAOs.DAOCliente;
import Entidades.Cliente;
import tools.ClienteGUIListagem;
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

public class ClienteGUI extends JFrame {

    JButton btnCreate;
    JButton btnRetrieve;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSave;
    JButton btnCancel;
    JButton btnList;
    JLabel labelId = new JLabel("Id");
    JTextField textFieldId = new JTextField();
    JLabel labelNome = new JLabel("Nome");
    JTextField textFieldNome = new JTextField();
    JLabel labelEnderecoCliente = new JLabel("EnderecoCliente");
    JTextField textFieldEnderecoCliente = new JTextField();
    JLabel labelCpfCliente = new JLabel("CpfCliente");
    JTextField textFieldCpfCliente = new JTextField();
    JLabel labelTelefoneCliente = new JLabel("TelefoneCliente");
    JTextField textFieldTelefoneCliente = new JTextField();
    JLabel labelCelularCliente = new JLabel("CelularCliente");
    JTextField textFieldCelularCliente = new JTextField();
    JLabel labelEmailCliente = new JLabel("EmailCliente");
    JTextField textFieldEmailCliente = new JTextField();
    JLabel labelSenhaCliente = new JLabel("SenhaCliente");
    JTextField textFieldSenhaCliente = new JTextField();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update 

    DAOCliente daoCliente = new DAOCliente();
    Cliente cliente = new Cliente();
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

    private void habilitarAtributos(boolean idCliente, boolean nomeCliente, boolean enderecoCliente, boolean cpfCliente, boolean telefoneCliente, boolean celularCliente, boolean emailCliente, boolean senhaCliente) {

        if (idCliente) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(idCliente);

        textFieldId.setEditable(idCliente);
        textFieldNome.setEditable(nomeCliente);
        textFieldEnderecoCliente.setEditable(enderecoCliente);
        textFieldCpfCliente.setEditable(cpfCliente);
        textFieldTelefoneCliente.setEditable(telefoneCliente);
        textFieldCelularCliente.setEditable(celularCliente);
        textFieldEmailCliente.setEditable(emailCliente);
        textFieldSenhaCliente.setEditable(senhaCliente);
    }

    public void zerarAtributos() {
        textFieldNome.setText("");
        textFieldEnderecoCliente.setText("");
        textFieldCpfCliente.setText("");
        textFieldTelefoneCliente.setText("");
        textFieldCelularCliente.setText("");
        textFieldEmailCliente.setText("");
        textFieldSenhaCliente.setText("");
    }

    public ClienteGUI(Point posicao, Dimension dimensao) {
        btnCreate = new JButton("Criar");
        btnRetrieve = new JButton("Buscar");
        btnUpdate = new JButton("Atualizar");
        btnDelete = new JButton("Remover");
        btnSave = new JButton("Salvar");
        btnCancel = new JButton("Cancelar");
        btnList = new JButton("Listar");

//OBSERVAR O CONSTRUTOR DA ENTIDADE
        setTitle("Cadastro de Cliente");
        setSize(800, 600);//tamanho da janela
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false, false, false, false);
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
        centro.setLayout(new GridLayout(8, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelNome);
        centro.add(textFieldNome);
        centro.add(labelEnderecoCliente);
        centro.add(textFieldEnderecoCliente);
        centro.add(labelCpfCliente);
        centro.add(textFieldCpfCliente);
        centro.add(labelTelefoneCliente);
        centro.add(textFieldTelefoneCliente);
        centro.add(labelCelularCliente);
        centro.add(textFieldCelularCliente);
        centro.add(labelEmailCliente);
        centro.add(textFieldEmailCliente);
        centro.add(labelSenhaCliente);
        centro.add(textFieldSenhaCliente);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldId.requestFocus();
        textFieldId.selectAll();
        textFieldId.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");

        // Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    textFieldId.setText(textFieldId.getText().trim());//caso tenham sidClienteo digitados espaços
                    if (textFieldId.getText().equals("")) {
                        MinhaJOptionPane myJOptionPane
                                = new MinhaJOptionPane(
                                        new Point(getBounds().x + (int) (getBounds().getWidth() / 2),
                                                getBounds().y + (int) (getBounds().getHeight() / 2)),
                                        "Deve ser informado um valor para esse campo");
                        if (myJOptionPane.getValorRetornado()) {
                            textFieldId.requestFocus();
                            textFieldId.selectAll();
                        }
                    } else {
                        cliente = new Cliente();
                        cliente.setId(Integer.valueOf(textFieldId.getText()));
                        cliente = daoCliente.obter(cliente.getId());
                        if (cliente != null) { //se encontrou na lista

                            textFieldNome.setText(String.valueOf(cliente.getNome()));
                            textFieldEnderecoCliente.setText(String.valueOf(cliente.getEndereco()));
                            textFieldCpfCliente.setText(String.valueOf(cliente.getCpf()));
                            textFieldTelefoneCliente.setText(String.valueOf(cliente.getTelefone()));
                            textFieldCelularCliente.setText(String.valueOf(cliente.getCelular()));
                            textFieldEmailCliente.setText(String.valueOf(cliente.getEmail()));
                            textFieldSenhaCliente.setText(String.valueOf(cliente.getSenha()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false, false, false, false);
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
                habilitarAtributos(false, true, true, true, true, true, true, true);
                textFieldNome.requestFocus();
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
                habilitarAtributos(false, true, true, true, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + cliente.getId() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoCliente.remover(cliente);
                    zerarAtributos();
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Cliente clienteNovo = new Cliente();
                    clienteNovo.setId(Integer.valueOf(textFieldId.getText()));
                    clienteNovo.setNome(String.valueOf(textFieldNome.getText()));
                    clienteNovo.setEndereco(String.valueOf(textFieldEnderecoCliente.getText()));
                    clienteNovo.setCpf(String.valueOf(textFieldCpfCliente.getText()));
                    clienteNovo.setTelefone(String.valueOf(textFieldTelefoneCliente.getText()));
                    clienteNovo.setCelular(String.valueOf(textFieldCelularCliente.getText()));
                    clienteNovo.setEmail(String.valueOf(textFieldEmailCliente.getText()));
                    clienteNovo.setSenha(String.valueOf(textFieldSenhaCliente.getText()));
                    if (acao.equals("insert")) {
                        daoCliente.inserir(clienteNovo);
                        labelAviso.setText("Registro inseridClienteo...");

                    } else {  //acao = update
                        daoCliente.atualizar(clienteNovo);
                        labelAviso.setText("Registro atualizado...");
                    }
                    habilitarAtributos(true, false, false, false, false, false, false, false);
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
                habilitarAtributos(true, false, false, false, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ClienteGUIListagem listagem = new ClienteGUIListagem(daoCliente.list(), getBounds().x, getBounds().y);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco

        textFieldId.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldId.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldId.setBackground(Color.white);
            }
        });

        textFieldNome.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNome.setBackground(Color.white);
            }
        });

        textFieldEnderecoCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEnderecoCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEnderecoCliente.setBackground(Color.white);
            }
        });

        textFieldCpfCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCpfCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldCpfCliente.setBackground(Color.white);
            }
        });

        textFieldTelefoneCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTelefoneCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTelefoneCliente.setBackground(Color.white);
            }
        });

        textFieldCelularCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCelularCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldCelularCliente.setBackground(Color.white);
            }
        });

        textFieldEmailCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEmailCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEmailCliente.setBackground(Color.white);
            }
        });

        textFieldSenhaCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldSenhaCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldSenhaCliente.setBackground(Color.white);
            }
        });

        setVisible(true);//faz a janela ficar visível
        textFieldId.requestFocus();

    } //fim do construtor 

    public static void main(String[] args) {
        new ClienteGUI(new Point(880, 250), new Dimension(800, 600));
    }
}//fim
