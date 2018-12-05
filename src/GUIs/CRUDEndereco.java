package GUIs;

import DAOs.DAOEditora;
import DAOs.DAOEndereco;
import Entidades.Editora;
import Entidades.Endereco;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;

public class CRUDEndereco extends JDialog {

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
    JLabel labelNome = new JLabel("Rua/Avenida");
    JTextField textFieldNome = new JTextField(40);
    JLabel labelCep = new JLabel("Cep");
    JTextField textFieldCep = new JTextField(0);
    JLabel labelNumero = new JLabel("Número");
    JTextField textFieldNumero = new JTextField(0);
    JLabel labelEditora = new JLabel("Id da editora");
    JTextField textFieldEditora = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOEndereco cl = new DAOEndereco();
    Endereco endereco;
    Endereco enderecoOriginal;

    //adicionado por Radames
    DAOEditora daoEditora = new DAOEditora();

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

    private void habilitarAtributos(boolean id, boolean nome, boolean cep, boolean numero, boolean editora) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldNome.setEditable(nome);
        textFieldCep.setEditable(cep);
        textFieldNumero.setEditable(numero);
        textFieldEditora.setEditable(editora);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldNome.setText("");
        textFieldCep.setText("");
        textFieldNumero.setText("");
        textFieldEditora.setText("");
    }

    public CRUDEndereco() {
        setTitle("Cadastrar um endereço");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);
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
        centro.setLayout(new GridLayout(5, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelNome);
        centro.add(textFieldNome);
        centro.add(labelCep);
        centro.add(textFieldCep);
        centro.add(labelNumero);
        centro.add(textFieldNumero);
        centro.add(labelEditora);
        centro.add(textFieldEditora);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldId.requestFocus();
        textFieldId.selectAll();
        textFieldId.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        setLocationRelativeTo(null);

// Listeners
        textFieldEditora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoEditora.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldEditora.getLocationOnScreen();
                    lc.x = lc.x + textFieldEditora.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldEditora.setText(selectedItem);
                    }
                }
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                endereco = new Endereco();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços
                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    endereco.setIdEndereco(Integer.parseInt(textFieldId.getText()));
                    endereco = cl.obter(Integer.valueOf(textFieldId.getText()));
                    if (endereco != null) { //se encontrou na lista
                        textFieldNome.setText(endereco.getNomeEndereco());
                        textFieldCep.setText(endereco.getCepEndereco());
                        textFieldNumero.setText(Integer.toString(endereco.getNumeroEndereco()));

                        Editora tu = daoEditora.obter(endereco.getEditoraIdEditora().getIdEditora());
                        textFieldEditora.setText(tu.getIdEditora() + "-" + tu.getNomeEditora());

                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        enderecoOriginal = endereco;
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
                habilitarAtributos(false, true, true, true, true);
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
                    endereco = new Endereco();
                    endereco.setIdEndereco(Integer.valueOf(textFieldId.getText()));
                    endereco.setNomeEndereco(textFieldNome.getText());
                    endereco.setCepEndereco(textFieldCep.getText());
                    endereco.setNumeroEndereco(Integer.parseInt(textFieldNumero.getText()));

                    String[] auxE = textFieldEditora.getText().split("-");
                    Editora editora = new DAOEditora().obter(Integer.valueOf(auxE[0]));
                    endereco.setEditoraIdEditora(editora);

                    cl.inserir(endereco);
                    habilitarAtributos(true, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    endereco = new Endereco();
                    endereco.setIdEndereco(Integer.valueOf(textFieldId.getText()));
                    endereco.setNomeEndereco(textFieldNome.getText());
                    endereco.setCepEndereco(textFieldCep.getText());
                    endereco.setNumeroEndereco(Integer.parseInt(textFieldNumero.getText()));

                    String[] auxE = textFieldEditora.getText().split("-");
                    Editora editora = new DAOEditora().obter(Integer.valueOf(auxE[0]));
                    endereco.setEditoraIdEditora(editora);
                    cl.atualizar(endereco);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false);
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
                habilitarAtributos(true, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemEndereco guiListagem = new ListagemEndereco(cl.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true);
            }
        });
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + endereco.getIdEndereco() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(endereco);
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
        textFieldCep.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldCep.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldCep.setBackground(Color.white);
            }
        });
        textFieldNumero.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldNumero.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldNumero.setBackground(Color.white);
            }
        });
        textFieldEditora.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldEditora.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldEditora.setBackground(Color.white);
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
        new CRUDEndereco();
    }
}
