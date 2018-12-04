package GUIs;

import DAOs.DAOEmprestimo;
import Entidades.Emprestimo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

public class EmprestimoGUI extends JFrame {

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
    JLabel labelIdEmprestimo = new JLabel("IdEmprestimo");
    JTextField textFieldIdEmprestimo = new JTextField();
    JLabel labelIdCliente = new JLabel("IdCliente");
    JTextField textFieldIdCliente = new JTextField();
    JLabel labelDataEmprestimo = new JLabel("DataEmprestimo");
    JTextField textFieldDataEmprestimo = new JTextField();
    JLabel labelFoto = new JLabel("");
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";
    JPanel pnCentro = new JPanel();
    JPanel pnLeste = new JPanel();

    DAOEmprestimo cl = new DAOEmprestimo();
    Emprestimo emprestimo = new Emprestimo();

    Image imagemAux;
    String origem;
    String destino = "src/fotos/";

    public EmprestimoGUI(Point p, Dimension dimensao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    private void habilitarAtributos(boolean idEmprestimo, boolean idCliente, boolean dataEmprestimo) {

        if (idEmprestimo) {
            textFieldIdEmprestimo.requestFocus();
            textFieldIdEmprestimo.selectAll();
        }
        textFieldIdEmprestimo.setEnabled(idEmprestimo);

        textFieldIdEmprestimo.setEditable(idEmprestimo);
        textFieldIdCliente.setEditable(idCliente);
        textFieldDataEmprestimo.setEditable(dataEmprestimo);
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
        textFieldIdCliente.setText("");
        textFieldDataEmprestimo.setText("");
    }

    public EmprestimoGUI() {
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
        setTitle("Cadastro de Emprestimo");
        setSize(800, 600);//tamanho da janela
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false);
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
        centro.setLayout(new GridLayout(3, 2));
        centro.add(labelIdEmprestimo);
        centro.add(textFieldIdEmprestimo);
        centro.add(labelIdCliente);
        centro.add(textFieldIdCliente);
        centro.add(labelDataEmprestimo);
        centro.add(textFieldDataEmprestimo);
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
                    emprestimo = new Emprestimo();
                    emprestimo.setIdEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    emprestimo = cl.obter(emprestimo);
                    if (emprestimo != null) { //se encontrou na lista

                        textFieldIdCliente.setText(String.valueOf(emprestimo.getIdCliente());
                        textFieldDataEmprestimo.setText(cl.converteDateString(emprestimo.getDataRetirada());
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false);
                        labelAviso.setText("Encontrou - selecione [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";

//para ajustar o tamanho de uma imagemF
                        try {
                            String aux = String.valueOf(emprestimo.getIdEmprestimo()).trim();
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
                habilitarAtributos(false, true, true);
                textFieldIdCliente.requestFocus();
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
                        "Confirma a exclusão do registro <ID = " + emprestimo.getIdEmprestimo() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    String aux = String.valueOf(emprestimo.getIdEmprestimo()).trim();
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
                    cl.remover(emprestimo);
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
                    emprestimo = new Emprestimo();
                    emprestimo.setIdEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    emprestimo.setIdCliente(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    emprestimo.setDataRetirada(cl.stringDate(textFieldDataEmprestimo.getText()));
                    cl.inserir(emprestimo);
                    habilitarAtributos(true, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    Emprestimo original = emprestimo;
                    emprestimo.setIdEmprestimo(Integer.valueOf(textFieldIdEmprestimo.getText()));
                    emprestimo.setIdCliente(Integer.valueOf(textFieldIdCliente.getText()));
                    emprestimo.setDataEmprestimo(cl.stringDate(textFieldDataEmprestimo.getText()));
                    cl.atualizar(original, emprestimo);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro atualizado...");
                }
                destino = destino + emprestimo.getIdEmprestimo() + ".png";
                CopiaImagem.copiar(origem, destino);
                destino = "src/fotos/";
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos(0);
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                EmprestimoGUIListagem listagem = new EmprestimoGUIListagem(cl.list(), getBounds().x, getBounds().y);
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

        textFieldIdCliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdCliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdCliente.setBackground(Color.white);
            }
        });

        textFieldDataEmprestimo.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataEmprestimo.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataEmprestimo.setBackground(Color.white);
            }
        });

        setVisible(true);//faz a janela ficar visível
        textFieldIdEmprestimo.requestFocus();

    } //fim do construtor 

}//fim
