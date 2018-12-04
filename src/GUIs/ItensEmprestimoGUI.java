package GUIs;

import DAOs.DAOItensEmprestimo;
import Entidades.ItensEmprestimo;
import static Entidades.ItensEmprestimo_.dataDevolucao;
import GUIs.GUIListagem.ItensEmprestimoGUIListagem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import tools.CopiaImagem;
import tools.JanelaPesquisar;
import tools.ManipulaArquivo;
import tools.MinhaJOptionPane;

public class ItensEmprestimoGUI extends JFrame {

    ImageIcon iconeCreate;
    ImageIcon iconeRetrieve;
    ImageIcon iconeUpdate;
    ImageIcon iconeDelete;
    ImageIcon iconeSave;
    ImageIcon iconeCancel;
    ImageIcon iconeListar;

    JButton btnCreate;
    JButton btnBuscar;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSave;
    JButton btnCancel;
    JButton btnList;
    JLabel labelIdItensEmprestimo = new JLabel("IdItensEmprestimo");
    JTextField textFieldIdItensEmprestimo = new JTextField();
    JLabel labelIdEmprestimo = new JLabel("IdEmprestimo");
    JTextField textFieldIdEmprestimo = new JTextField();
    JLabel labelIdLivro = new JLabel("IdLivro");
    JTextField textFieldIdLivro = new JTextField();
    JLabel labelDataDevolucao = new JLabel("DataDevolucao");
    JTextField textFieldDataDevolucao = new JTextField();
    JLabel labelFoto = new JLabel("");
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";
    JPanel pnCentro = new JPanel();
    JPanel pnLeste = new JPanel();

    DAOItensEmprestimo cl = new DAOItensEmprestimo();
    ItensEmprestimo itensEmprestimo = new ItensEmprestimo();

    Image imagemAux;
    String origem;
    String destino = "src/fotos/";

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnBuscar.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnBuscar.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idItensEmprestimo, boolean idEmprestimo, boolean idLivro, boolean dataDevolucao ) {

        if (idEmprestimo) {
            textFieldIdEmprestimo.requestFocus();
            textFieldIdEmprestimo.selectAll();
        }
        textFieldIdEmprestimo.setEnabled(idEmprestimo);

        textFieldIdEmprestimo.setEditable(idEmprestimo);
        textFieldIdLivro.setEditable(idLivro);
        textFieldDataDevolucao.setEditable(dataDevolucao);
    }

    public void zerarAtributos(int img) {
        if (img == 0) {
            origem = "/fotos/0.png";
        } else {
            origem = "/fotos/0a.png";
        }
        ImageIcon icone = new ImageIcon(getClass().getResource(origem));
        imagemAux = icone.getImage();
        icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
        labelFoto.setIcon(icone);
        textFieldIdLivro.setText("");
        textFieldDataDevolucao.setText("");
    }

    public ItensEmprestimoGUI() {
        try {
            iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
            iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
            iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
            iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
            iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
            iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
            iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));

            btnCreate = new JButton(iconeCreate);
            btnBuscar = new JButton(iconeRetrieve);
            btnUpdate = new JButton(iconeUpdate);
            btnDelete = new JButton(iconeDelete);
            btnSave = new JButton(iconeSave);
            btnCancel = new JButton(iconeCancel);
            btnList = new JButton(iconeListar);
        } catch (Exception e) {
            System.out.println("Não achou alguma imagem para os botões, confira o caminho e se existe a package icones");
        }
        setTitle("Cadastro de ItensEmprestimo");
        setSize(800, 600);//tamanho da janela
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnBuscar.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(btnBuscar);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos

        pnLeste.setLayout(new GridLayout(1, 1));
        pnLeste.add(labelFoto);
        pnLeste.setBackground(Color.red);
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(4, 2));
        centro.add(labelIdItensEmprestimo);
        centro.add(textFieldIdItensEmprestimo);
        centro.add(labelIdEmprestimo);
        centro.add(textFieldIdEmprestimo);
        centro.add(labelIdLivro);
        centro.add(textFieldIdLivro);
        centro.add(labelDataDevolucao);
        centro.add(textFieldDataDevolucao);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(pnLeste, BorderLayout.EAST);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldIdEmprestimo.requestFocus();
        textFieldIdEmprestimo.selectAll();
        textFieldIdEmprestimo.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");

        try {
            origem = "/fotos/0.png";
            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
            labelFoto.setIcon(icone);

        } catch (Exception e) {
            System.out.println("erro ao carregar a imagem");
        }

        // Relacionamento
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textFieldIdEmprestimo.setText(textFieldIdEmprestimo.getText().trim());//caso tenham sido digitados espaços
                if (textFieldIdEmprestimo.getText().equals("")) {
                    MinhaJOptionPane myJOptionPane
                            = new MinhaJOptionPane(
                                    new Point(getBounds().x + (int) (getBounds().getWidth() / 2),
                                            getBounds().y + (int) (getBounds().getHeight() / 2)),
                                    "Deve ser informado um valor para esse campo");
                    if (myJOptionPane.getValorRetornado()) {
                        textFieldIdEmprestimo.requestFocus();
                        textFieldIdEmprestimo.selectAll();
                    }
                } else {
                    itensEmprestimo = new ItensEmprestimo();
                    itensEmprestimo.setDataDevolucao((Date) dataDevolucao);
                    itensEmprestimo = cl.obter(itensEmprestimo);
                    if (itensEmprestimo != null) { //se encontrou na lista

                        textFieldIdLivro.setText(String.valueOf(itensEmprestimo.getLivroId()));
                        textFieldDataDevolucao.setText(String.valueOf(itensEmprestimo.getDataDevolucao()));
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false);
                        labelAviso.setText("Encontrou - selecione [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";

//para ajustar o tamanho de uma imagem
                        try {
                            String aux = String.valueOf(itensEmprestimo.getIdItensEmprestimo()).trim();
                            origem = "/fotos/" + aux + ".png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));

                            labelFoto.setIcon(icone);

                        } catch (Exception e) {
                            System.out.println("nao achou " + origem);
                            origem = "/fotos/0.png";
                            ImageIcon icone = new ImageIcon(getClass().getResource(origem));
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(300, 300, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);
                        }
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos(0);
                        labelAviso.setText("Não cadastrado");
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos(1);
                habilitarAtributos(false, true, true, true);
                textFieldIdLivro.requestFocus();
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
                habilitarAtributos(false, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + itensEmprestimo.getIdItensEmprestimo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    String aux = String.valueOf(itensEmprestimo.getIdItensEmprestimo()).trim();
                    origem = "src/fotos/" + aux + ".png";
                    System.out.println(origem);
                    try {
                        File f = new File(origem);
                        if (f.exists()) {
                            f.delete();
                        }
                    } catch (Exception e) {
                        System.out.println("foto não deletada");
                    }
                    cl.remover(itensEmprestimo);
                    zerarAtributos(0);
                    textFieldIdEmprestimo.requestFocus();
                    textFieldIdEmprestimo.selectAll();
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    itensEmprestimo = new ItensEmprestimo();
                    itensEmprestimo.setIdItensEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    itensEmprestimo.setIdEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    itensEmprestimo.setIdLivro(Integer.valueOf(textFieldIdLivro.getText()));
                    itensEmprestimo.setDataDevolucao(Integer.valueOf(textFieldDataDevolucao.getText()));
                    cl.inserir(itensEmprestimo);
                    habilitarAtributos(true, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    ItensEmprestimo original = itensEmprestimo;
                    itensEmprestimo.setIdItensEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    itensEmprestimo.setIdEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    itensEmprestimo.setIdLivro(Integer.valueOf(textFieldIdLivro.getText()));
                    itensEmprestimo.setDataDevolucao(Integer.valueOf(textFieldDataDevolucao.getText()));
                    cl.atualizar(original, itensEmprestimo);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro atualizado...");
                }
                destino = destino + itensEmprestimo.getIdItensEmprestimo() + ".png";
                CopiaImagem.copiar(origem, destino);
                destino = "src/fotos/";
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos(0);
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ItensEmprestimoGUIListagem listagem = new ItensEmprestimoGUIListagem(cl.list(), getBounds().x, getBounds().y);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                // Sai do sistema  
                System.exit(0);
            }
        });

        textFieldIdEmprestimo.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdEmprestimo.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdEmprestimo.setBackground(Color.white);
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

        

        setVisible(true);//faz a janela ficar visível
        textFieldIdEmprestimo.requestFocus();

    } //fim do construtor 

}//fim
