package GUIs;

import DAOs.DAOCliente;
import Entidades.Cliente;
import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class CRUDCliente extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelId = new JLabel("Id");
    JTextField textFieldId = new JTextField(0);
    JLabel labelNome = new JLabel("Nome");
    JTextField textFieldNome = new JTextField(0);
    JLabel labelEndereco = new JLabel("Endereço");
    JTextField textFieldEndereco = new JTextField(0);
    JLabel labelCpf = new JLabel("CPF");
    JTextField textFieldCpf = new JTextField(0);
    JLabel labelTelefone = new JLabel("Telefone");
    JTextField textFieldTelefone = new JTextField(0);
    JLabel labelCelular = new JLabel("Celular");
    JTextField textFieldCelular = new JTextField(0);
    JLabel labelEmail = new JLabel("Email");
    JTextField textFieldEmail = new JTextField(0);
    JLabel labelSenha = new JLabel("Senha");
    JTextField textFieldSenha = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOCliente cl = new DAOCliente();
    Cliente cliente;
    Cliente clienteOriginal;

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

    private void habilitarAtributos(boolean id, boolean nome, boolean endereco, boolean cpf, boolean telefone, boolean celular, boolean email, boolean senha) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldNome.setEditable(nome);
        textFieldEndereco.setEditable(endereco);
        textFieldCpf.setEditable(cpf);
        textFieldTelefone.setEditable(telefone);
        textFieldCelular.setEditable(celular);
        textFieldEmail.setEditable(email);
        textFieldSenha.setEditable(senha);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldNome.setText("");
        textFieldEndereco.setText("");
        textFieldCpf.setText("");
        textFieldTelefone.setText("");
        textFieldCelular.setText("");
        textFieldSenha.setText("");

    }

    public CRUDCliente() {
        setTitle("Cadastrar um cliente");
        setSize(600, 400);//tamanho da janela
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
        Toolbar1.add(labelId);
        Toolbar1.add(textFieldId);
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
        centro.add(labelEndereco);
        centro.add(textFieldEndereco);
        centro.add(labelCpf);
        centro.add(textFieldCpf);
        centro.add(labelTelefone);
        centro.add(textFieldTelefone);
        centro.add(labelCelular);
        centro.add(textFieldCelular);
        centro.add(labelEmail);
        centro.add(textFieldEmail);
        centro.add(labelSenha);
        centro.add(textFieldSenha);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldId.requestFocus();
        textFieldId.selectAll();
        textFieldId.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        setLocationRelativeTo(null); // posiciona no centro da tela principal

// Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cliente = new Cliente();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    cliente.setIdCliente(Integer.valueOf(textFieldId.getText()));
                    cliente = cl.obter(cliente.getIdCliente());
                    if (cliente != null) { //se encontrou na lista
                        textFieldNome.setText(cliente.getNomeCliente());
                        textFieldEndereco.setText(cliente.getEnderecoCliente());
                        textFieldCpf.setText(cliente.getCpfCliente());
                        textFieldTelefone.setText(cliente.getTelefoneCliente());
                        textFieldCelular.setText(cliente.getCelularCliente());
                        textFieldEmail.setText(cliente.getEmailCliente());
                        textFieldSenha.setText(cliente.getSenhaCliente());
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        clienteOriginal = cliente;
                    } else {
                        atvBotoes(true, true, false, false);
                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                habilitarAtributos(false, true, true, true, true, true, true, true);
                textFieldNome.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    cliente = new Cliente();
                    cliente.setIdCliente(Integer.valueOf(textFieldId.getText()));
                    cliente.setNomeCliente(textFieldNome.getText());
                    cliente.setEnderecoCliente(textFieldEndereco.getText());;
                    cliente.setCpfCliente(textFieldCpf.getText());
                    cliente.setTelefoneCliente(textFieldTelefone.getText());;
                    cliente.setCelularCliente(textFieldCelular.getText());
                    cliente.setEmailCliente(textFieldEmail.getText());
                    cliente.setSenhaCliente(textFieldSenha.getText());
                    cl.inserir(cliente);
                    habilitarAtributos(true, false, false, false, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    cliente.setIdCliente(Integer.valueOf(textFieldId.getText()));
                    cliente.setNomeCliente(textFieldNome.getText());
                    cliente.setEnderecoCliente(textFieldEndereco.getText());;
                    cliente.setCpfCliente(textFieldCpf.getText());
                    cliente.setTelefoneCliente(textFieldTelefone.getText());;
                    cliente.setCelularCliente(textFieldCelular.getText());
                    cliente.setEmailCliente(textFieldEmail.getText());
                    cliente.setSenhaCliente(textFieldSenha.getText());
                    cl.atualizar(cliente);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false, false, false, false);
                    atvBotoes(false, true, false, false);
                    zerarAtributos();
                    labelAviso.setText("Registro atualizado...");
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
                ListagemCliente guiListagem = new ListagemCliente(cl.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true, true, true, true);
                atvBotoes(false, true, false, false);
            }
        });
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + cliente.getIdCliente() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(cliente);
                    zerarAtributos();
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                    atvBotoes(false, true, false, false);
                }
            }
        });
        textFieldId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldId.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }
            @Override
            public void focusLost(FocusEvent fe) {
                textFieldId.setBackground(Color.white);
            }
        });
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
        textFieldEndereco.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldEndereco.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldEndereco.setBackground(Color.white);
            }
        });
        textFieldCpf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldCpf.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldCpf.setBackground(Color.white);
            }
        });
        textFieldTelefone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldTelefone.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldTelefone.setBackground(Color.white);
            }
        });
        textFieldCelular.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldCelular.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldCelular.setBackground(Color.white);
            }
        });
        textFieldEmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldEmail.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldEmail.setBackground(Color.white);
            }
        });
        textFieldSenha.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldSenha.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldSenha.setBackground(Color.white);
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
    }
    public static void main(String[] args) {
        new CRUDCliente();
    }
}