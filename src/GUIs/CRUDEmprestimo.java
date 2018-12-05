package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOEmprestimo;
import Entidades.Cliente;
import Entidades.Emprestimo;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class CRUDEmprestimo extends JDialog {

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
    JLabel labelData = new JLabel("Data de retirada");
    JTextField textFieldData = new JTextField(40);
    JLabel labelCliente = new JLabel("Id oa cliente");
    JTextField textFieldCliente = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOEmprestimo cl = new DAOEmprestimo();
    Emprestimo emprestimo;
    Emprestimo emprestimoOriginal;

    DAOCliente daoCliente = new DAOCliente();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

    private void habilitarAtributos(boolean id, boolean data, boolean cliente) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldData.setEditable(data);
        textFieldCliente.setEditable(cliente);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldData.setText("");
        textFieldCliente.setText("");
    }

    public CRUDEmprestimo() {
        setTitle("Realizar um empréstimo");
        setSize(600, 400);//tamanho da janela
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
        centro.setLayout(new GridLayout(3, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelData);
        centro.add(textFieldData);
        centro.add(labelCliente);
        centro.add(textFieldCliente);
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
        textFieldCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldCliente.getLocationOnScreen();
                    lc.x = lc.x + textFieldCliente.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldCliente.setText(selectedItem);
                    }
                }
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                emprestimo = new Emprestimo();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços
                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    emprestimo.setIdEmprestimo(Integer.parseInt(textFieldId.getText()));
                    emprestimo = cl.obter(Integer.valueOf(textFieldId.getText()));
                    if (emprestimo != null) { //se encontrou na lista
                        textFieldData.setText(sdf.format(emprestimo.getDataEmprestimo()));

                        Cliente tu = daoCliente.obter(emprestimo.getClienteIdCliente().getIdCliente());
                        textFieldCliente.setText(tu.getIdCliente() + "-" + tu.getNomeCliente());

                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        emprestimoOriginal = emprestimo;
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
                habilitarAtributos(false, true, true);
                textFieldData.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    emprestimo = new Emprestimo();
                    emprestimo.setIdEmprestimo(Integer.valueOf(textFieldId.getText()));
                    try {
                        emprestimo.setDataEmprestimo(sdf.parse(textFieldData.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    String[] auxC = textFieldCliente.getText().split("-");
                    Cliente cliente = new DAOCliente().obter(Integer.valueOf(auxC[0]));
                    emprestimo.setClienteIdCliente(cliente);

                    cl.inserir(emprestimo);
                    habilitarAtributos(true, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    emprestimo = new Emprestimo();
                    emprestimo.setIdEmprestimo(Integer.valueOf(textFieldId.getText()));
                    try {
                        emprestimo.setDataEmprestimo(sdf.parse(textFieldData.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    String[] auxC = textFieldCliente.getText().split("-");
                    Cliente cliente = new DAOCliente().obter(Integer.valueOf(auxC[0]));
                    emprestimo.setClienteIdCliente(cliente);
                    cl.atualizar(emprestimo);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false);
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
                habilitarAtributos(true, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemEmprestimo guiListagem = new ListagemEmprestimo(cl.list());
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
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + emprestimo.getIdEmprestimo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(emprestimo);
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
        textFieldData.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldData.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldData.setBackground(Color.white);
            }
        });
        textFieldCliente.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldCliente.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldCliente.setBackground(Color.white);
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
        new CRUDEmprestimo();
    }
}
