package GUIs;

import DAOs.DAOEmprestimo;
import DAOs.DAOLivro;
import DAOs.DAOItensEmprestimo;
import Entidades.Emprestimo;
import Entidades.Livro;
import Entidades.ItensEmprestimo;
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

public class CRUDItensEmprestimo extends JDialog {

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
    JLabel labelDataDevolucao = new JLabel("Data de devolução");
    JTextField textFieldDataDevolucao = new JTextField(40);
    JLabel labelLivro = new JLabel("Id do livro");
    JTextField textFieldLivro = new JTextField(0);
    JLabel labelEmprestimo = new JLabel("Id do empréstimo");
    JTextField textFieldEmprestimo = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOItensEmprestimo cl = new DAOItensEmprestimo();
    ItensEmprestimo itensEmprestimo;
    ItensEmprestimo itensEmprestimoOriginal;

    DAOEmprestimo daoEmprestimo = new DAOEmprestimo();
    DAOLivro daoLivro = new DAOLivro();

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

    private void habilitarAtributos(boolean id, boolean dataDevolucao, boolean livro, boolean emprestimo) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldDataDevolucao.setEditable(dataDevolucao);
        textFieldLivro.setEditable(livro);
        textFieldEmprestimo.setEditable(emprestimo);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldDataDevolucao.setText("");
        textFieldLivro.setText("");
        textFieldEmprestimo.setText("");
    }

    public CRUDItensEmprestimo() {
        setTitle("Realizar um empréstimo");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false);
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
        centro.setLayout(new GridLayout(4, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelDataDevolucao);
        centro.add(textFieldDataDevolucao);
        centro.add(labelLivro);
        centro.add(textFieldLivro);
        centro.add(labelEmprestimo);
        centro.add(textFieldEmprestimo);
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
        textFieldLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoLivro.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldLivro.getLocationOnScreen();
                    lc.x = lc.x + textFieldLivro.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldLivro.setText(selectedItem);
                    }
                }
            }
        });
        textFieldEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoEmprestimo.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldEmprestimo.getLocationOnScreen();
                    lc.x = lc.x + textFieldEmprestimo.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldEmprestimo.setText(selectedItem);
                    }
                }
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                itensEmprestimo = new ItensEmprestimo();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços
                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    itensEmprestimo.setIdItensEmprestimo(Integer.parseInt(textFieldId.getText()));
                    itensEmprestimo = cl.obter(Integer.valueOf(textFieldId.getText()));
                    if (itensEmprestimo != null) { //se encontrou na lista
                        textFieldDataDevolucao.setText(sdf.format(itensEmprestimo.getDataDevolucaoItensEmprestimo()));

                        Livro l = daoLivro.obter(itensEmprestimo.getLivroIdLivro().getIdLivro());
                        textFieldLivro.setText(l.getIdLivro() + "-" + l.getNomeLivro());
                        
                        Emprestimo e = daoEmprestimo.obter(itensEmprestimo.getEmprestimoIdEmprestimo().getIdEmprestimo());
                        textFieldEmprestimo.setText(e.getIdEmprestimo() + "-" + e.getClienteIdCliente());
                        
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        itensEmprestimoOriginal = itensEmprestimo;
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
                habilitarAtributos(false, true, true, true);
                textFieldDataDevolucao.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    itensEmprestimo = new ItensEmprestimo();
                    itensEmprestimo.setIdItensEmprestimo(Integer.valueOf(textFieldId.getText()));
                    try {
                        itensEmprestimo.setDataDevolucaoItensEmprestimo(sdf.parse(textFieldDataDevolucao.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDItensEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String[] auxL = textFieldLivro.getText().split("-");
                    Livro livro = new DAOLivro().obter(Integer.valueOf(auxL[0]));
                    itensEmprestimo.setLivroIdLivro(livro);
                    
                    String[] auxE = textFieldEmprestimo.getText().split("-");
                    Emprestimo emprestimo = new DAOEmprestimo().obter(Integer.valueOf(auxE[0]));
                    itensEmprestimo.setEmprestimoIdEmprestimo(emprestimo);
                    
                    cl.inserir(itensEmprestimo);
                    habilitarAtributos(true, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    itensEmprestimo = new ItensEmprestimo();
                    itensEmprestimo.setIdItensEmprestimo(Integer.valueOf(textFieldId.getText()));
                    try {
                        itensEmprestimo.setDataDevolucaoItensEmprestimo(sdf.parse(textFieldDataDevolucao.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDItensEmprestimo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String[] auxL = textFieldLivro.getText().split("-");
                    Livro livro = new DAOLivro().obter(Integer.valueOf(auxL[0]));
                    
                    String[] auxE = textFieldEmprestimo.getText().split("-");
                    Emprestimo emprestimo = new DAOEmprestimo().obter(Integer.valueOf(auxE[0]));
                    
                    itensEmprestimo.setLivroIdLivro(livro);
                    cl.atualizar(itensEmprestimo);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false);
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
                habilitarAtributos(true, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemItensEmprestimo guiListagem = new ListagemItensEmprestimo(cl.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true);
            }
        });
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + itensEmprestimo.getIdItensEmprestimo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(itensEmprestimo);
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
        textFieldDataDevolucao.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataDevolucao.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataDevolucao.setBackground(Color.white);
            }
        });
        textFieldLivro.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldLivro.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldLivro.setBackground(Color.white);
            }
        });
        textFieldEmprestimo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldEmprestimo.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldEmprestimo.setBackground(Color.white);
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
        new CRUDItensEmprestimo();
    }
}
