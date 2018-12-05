package GUIs;

import DAOs.DAOAutor;
import DAOs.DAOCliente;
import DAOs.DAOEditora;
import DAOs.DAOGenero;
import DAOs.DAOLivro;
import DAOs.DAOStatus;
import Entidades.Autor;
import Entidades.Cliente;
import Entidades.Editora;
import Entidades.Genero;
import Entidades.Livro;
import Entidades.Status;
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


public class CRUDLivro extends JDialog {

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
    JLabel labelEdicao = new JLabel("Edição");
    JTextField textFieldEdicao = new JTextField(0);
    JLabel labelAno = new JLabel("Ano de publicação");
    JTextField textFieldAno = new JTextField(0);
    JLabel labelQuantidade = new JLabel("Quantidade");
    JTextField textFieldQuantidade = new JTextField(0);
    JLabel labelGenero = new JLabel("Id do Gênero");
    JTextField textFieldGenero = new JTextField(0);
    JLabel labelEditora = new JLabel("Id da Editora");
    JTextField textFieldEditora = new JTextField(0);
    JLabel labelAutor = new JLabel("Id do Autor");
    JTextField textFieldAutor = new JTextField(0);
    JLabel labelStatus = new JLabel("Id do Status");
    JTextField textFieldStatus = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOLivro cl = new DAOLivro();
    Livro livro;
    Livro livroOriginal;

    DAOGenero daoGenero = new DAOGenero();
    DAOEditora daoEditora = new DAOEditora();
    DAOAutor daoAutor = new DAOAutor();
    DAOStatus daoStatus = new DAOStatus();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

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

    private void habilitarAtributos(boolean id, boolean nome, boolean edicao, boolean ano, boolean quantidade, boolean genero, boolean editora, boolean autor, boolean status) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldNome.setEditable(nome);
        textFieldEdicao.setEditable(edicao);
        textFieldAno.setEditable(ano);
        textFieldQuantidade.setEditable(quantidade);
        textFieldGenero.setEditable(genero);
        textFieldEditora.setEditable(editora);
        textFieldAutor.setEditable(autor);
        textFieldStatus.setEditable(status);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldNome.setText("");
        textFieldEdicao.setText("");
        textFieldAno.setText("");
        textFieldQuantidade.setText("");
        textFieldGenero.setText("");
        textFieldEditora.setText("");
        textFieldAutor.setText("");
        textFieldStatus.setText("");
    }

    public CRUDLivro() {
        setTitle("Cadastrar um livro");
        setSize(600, 400);//tamanho da janela
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
        centro.setLayout(new GridLayout(9, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelNome);
        centro.add(textFieldNome);
        centro.add(labelEdicao);
        centro.add(textFieldEdicao);
        centro.add(labelAno);
        centro.add(textFieldAno);
        centro.add(labelQuantidade);
        centro.add(textFieldQuantidade);
        centro.add(labelGenero);
        centro.add(textFieldGenero);
        centro.add(labelEditora);
        centro.add(textFieldEditora);
        centro.add(labelAutor);
        centro.add(textFieldAutor);
        centro.add(labelStatus);
        centro.add(textFieldStatus);
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
        textFieldGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoGenero.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldGenero.getLocationOnScreen();
                    lc.x = lc.x + textFieldGenero.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldGenero.setText(selectedItem);
                    }
                }
            }
        });
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
        textFieldAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoAutor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldAutor.getLocationOnScreen();
                    lc.x = lc.x + textFieldAutor.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldAutor.setText(selectedItem);
                    }
                }
            }
        });
        textFieldStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoStatus.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldStatus.getLocationOnScreen();
                    lc.x = lc.x + textFieldStatus.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        textFieldStatus.setText(selectedItem);
                    }
                }
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                livro = new Livro();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços
                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    livro.setIdLivro(Integer.parseInt(textFieldId.getText()));
                    livro = cl.obter(Integer.valueOf(textFieldId.getText()));
                    if (livro != null) { //se encontrou na lista
                        textFieldNome.setText(livro.getNomeLivro());
                        textFieldEdicao.setText(livro.getEdicaoLivro());
                        textFieldAno.setText(sdf.format(livro.getAnoPublicacaoLivro()));
                        textFieldQuantidade.setText(Integer.toString(livro.getQuantidadeEstoqueLivro()));

                        Genero g = daoGenero.obter(livro.getGeneroIdGenero().getIdGenero());
                        textFieldGenero.setText(g.getIdGenero() + "-" + g.getNomeGenero());

                        Editora e = daoEditora.obter(livro.getEditoraIdEditora().getIdEditora());
                        textFieldEditora.setText(g.getIdGenero() + "-" + g.getNomeGenero());

                        Autor a = daoAutor.obter(livro.getAutorIdAutor().getIdAutor());
                        textFieldAutor.setText(a.getIdAutor() + "-" + a.getNomeAutor());

                        Status s = daoStatus.obter(livro.getStatusIdStatus().getIdStatus());
                        textFieldStatus.setText(s.getIdStatus + "-" + s.getNomeStatus());

                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false, false, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        livroOriginal = livro;
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
                habilitarAtributos(false, true, true, true, true, true, true, true, true);
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
                    livro = new Livro();
                    livro.setIdLivro(Integer.valueOf(textFieldId.getText()));
                    livro.setNomeLivro(textFieldNome.getText());
                    livro.setEdicaoLivro(textFieldEdicao.getText());;
                    try {
                        livro.setAnoPublicacaoLivro(sdf.parse(textFieldAno.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDLivro.class.getName()).log(Level.SEVERE, null, ex);
                    }
;
                    livro.setQuantidadeEstoqueLivro(Integer.valueOf(textFieldQuantidade.getText()));

                    String[] auxG = textFieldGenero.getText().split("-");
                    Genero genero = new DAOGenero().obter(Integer.valueOf(auxG[0]));
                    livro.setGeneroIdGenero(genero);

                    String[] auxE = textFieldEditora.getText().split("-");
                    Editora editora = new DAOEditora().obter(Integer.valueOf(auxG[0]));
                    livro.setEditoraIdEditora(editora);

                    String[] auxA = textFieldAutor.getText().split("-");
                    Autor autor = new DAOAutor().obter(Integer.valueOf(auxG[0]));
                    livro.setAutorIdAutor(autor);

                    String[] auxS = textFieldStatus.getText().split("-");
                    Status status = new DAOStatus().obter(Integer.valueOf(auxS[0]));
                    livro.setStatusIdStatus(status);

                    cl.inserir(livro);
                    habilitarAtributos(true, false, false, false, false, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    livro = new Livro();
                    livro.setIdLivro(Integer.valueOf(textFieldId.getText()));
                    livro.setNomeLivro(textFieldNome.getText());
                    livro.setEdicaoLivro(textFieldEdicao.getText());;
                    try {
                        livro.setAnoPublicacaoLivro(sdf.parse(textFieldAno.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDLivro.class.getName()).log(Level.SEVERE, null, ex);
                    }
;
                    livro.setQuantidadeEstoqueLivro(Integer.valueOf(textFieldQuantidade.getText()));

                    String[] auxG = textFieldGenero.getText().split("-");
                    Genero genero = new DAOGenero().obter(Integer.valueOf(auxG[0]));
                    livro.setGeneroIdGenero(genero);

                    String[] auxE = textFieldEditora.getText().split("-");
                    Editora editora = new DAOEditora().obter(Integer.valueOf(auxG[0]));
                    livro.setEditoraIdEditora(editora);

                    String[] auxA = textFieldAutor.getText().split("-");
                    Autor autor = new DAOAutor().obter(Integer.valueOf(auxG[0]));
                    livro.setAutorIdAutor(autor);

                    String[] auxS = textFieldStatus.getText().split("-");
                    Status status = new DAOStatus().obter(Integer.valueOf(auxS[0]));
                    livro.setStatusIdStatus(status);
                    
                    cl.atualizar(livro);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false, false, false, false, false);
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
                habilitarAtributos(true, false, false, false, false, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemLivro guiListagem = new ListagemLivro(cl.list());
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
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + livro.getIdLivro() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(livro);
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
        textFieldEdicao.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEdicao.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEdicao.setBackground(Color.white);
            }
        });
        textFieldAno.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldAno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldAno.setBackground(Color.white);
            }
        });
        textFieldQuantidade.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldQuantidade.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldQuantidade.setBackground(Color.white);
            }
        });
        textFieldGenero.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldGenero.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldGenero.setBackground(Color.white);
            }
        });
        textFieldEditora.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEditora.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEditora.setBackground(Color.white);
            }
        });
        textFieldAutor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldAutor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldAutor.setBackground(Color.white);
            }
        });
        textFieldStatus.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldStatus.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldStatus.setBackground(Color.white);
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
        new CRUDLivro();
    }
}
