package GUIs;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * ImplementaÃ§Ã£o de Table Model para exibir os Atributo.
 *
 * @author Eric Yuzo
 */
public class AtributoTableModel extends AbstractTableModel {

    /* Lista de Atributo que representam as linhas. */
    private List<Atributo> linhas;

    /* Array de Strings com o nome das colunas. */
    private String[] colunas = new String[]{
        "   IdVenda", "IdFilme", "Quantidade", "Preço"};


    /* Cria um AtributoTableModel vazio. */
    public AtributoTableModel() {
        linhas = new ArrayList<>();
    }

    /* Cria um AtributoTableModel carregado com
     * a lista de sÃ³cios especificada. */
    public AtributoTableModel(List<Atributo> listaDeAtributos) {
        linhas = new ArrayList<>(listaDeAtributos);
    }


    /* Retorna a quantidade de colunas. */
    @Override
    public int getColumnCount() {
        // EstÃ¡ retornando o tamanho do array "colunas".
        // Mas como o array Ã© fixo, vai sempre retornar 4.
        return colunas.length;
    }

    /* Retorna a quantidade de linhas. */
    @Override
    public int getRowCount() {
        // Retorna o tamanho da lista de sÃ³cios.
        return linhas.size();
    }

    /* Retorna o nome da coluna no Ã­ndice especificado.
     * Este mÃ©todo Ã© usado pela JTable para saber o texto do cabeÃ§alho. */
    @Override
    public String getColumnName(int columnIndex) {
        // Retorna o conteÃºdo do Array que possui o nome das colunas
        // no Ã­ndice especificado.
        return colunas[columnIndex];
    }

    ;

	/* Retorna a classe dos elementos da coluna especificada.
	 * Este mÃ©todo Ã© usado pela JTable na hora de definir o editor da cÃ©lula. */
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        // Retorna a classe referente a coluna especificada.
        // Aqui Ã© feito um switch para verificar qual Ã© a coluna
        // e retornar o tipo adequado. As colunas sÃ£o as mesmas
        // que foram especificadas no array "colunas".
        switch (columnIndex) {
            case 0: // Primeira coluna Ã© o nome, que Ã© uma String.
                return Integer.class;
            case 1: // Segunda coluna Ã© o tipo
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return Double.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /* Retorna o valor da cÃ©lula especificada
     * pelos Ã­ndices da linha e da coluna. */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Atributo atributo = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0: // Primeira coluna Ã© o nome.
                return atributo.getIdVenda();
            case 1: // Segunda coluna Ã© o telefone.
                return atributo.getIdFilme();
            case 2: // Terceira coluna Ã© a data de cadastro.
                return atributo.getQuantidade();
            case 3:
                return atributo.getPreco();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    /* Retorna um valor booleano que define se a cÃ©lula em questÃ£o
     * pode ser editada ou nÃ£o.
     * Este mÃ©todo Ã© utilizado pela JTable na hora de definir o editor da cÃ©lula.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    ////////////////////////////////////////////////////////////
    // Os mÃ©todos declarados atÃ© aqui foram as implementaÃ§Ãµes //
    // de TableModel, que sÃ£o continuamente utilizados        //
    // pela JTable para definir seu comportamento,            //
    // por isso o nome Table Model (Modelo da Tabela).        //
    //                                                        //
    // A partir de agora, os mÃ©todos criados serÃ£o            //
    // particulares desta classe. Eles serÃ£o Ãºteis            //
    // em algumas situaÃ§Ãµes.                                  //
    ////////////////////////////////////////////////////////////
    /* Retorna a linha especificada. */
    public Atributo getAtributo(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addAtributo(Atributo atributo) {
        // Adiciona o registro.
        linhas.add(atributo);

        // Pega a quantidade de registros e subtrai um para achar
        // o Ãºltimo Ã­ndice. Ã‰ preciso subtrair um, pois os Ã­ndices
        // comeÃ§am pelo zero.
        int ultimoIndice = getRowCount() - 1;

        // Reporta a mudanÃ§a. O JTable recebe a notificaÃ§Ã£o
        // e se redesenha permitindo que visualizemos a atualizaÃ§Ã£o.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void setAtributo(int indiceLinha, Atributo a) {
        linhas.set(indiceLinha, a);
        // redesenha permitindo que visualizemos a atualizaÃ§Ã£o.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void removeAtributo(int indiceLinha) {
        // Remove a linha especificada. 
        linhas.remove(indiceLinha);
        // Reporta a mudanÃ§a. O JTable recebe a notificaÃ§Ã£o
        // e se redesenha permitindo que visualizemos a atualizaÃ§Ã£o.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeAtributos(List<Atributo> atributos) {
        // Pega o tamanho antigo da tabela.
        int tamanhoAntigo = getRowCount();
        
        // Adiciona os registros.
        linhas.addAll(atributos);

        // Reporta a mudanÃ§a. O JTable recebe a notificaÃ§Ã£o
        // e se redesenha permitindo que visualizemos a atualizaÃ§Ã£o.
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {
        // Remove todos os elementos da lista de sÃ³cios.
        linhas.clear();
        // Reporta a mudanÃ§a. O JTable recebe a notificaÃ§Ã£o
        // e se redesenha permitindo que visualizemos a atualizaÃ§Ã£o.
        fireTableDataChanged();
    }

    /* Verifica se este table model estÃ¡ vazio. */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public List<String> getListaAtributosString() {
        List<String> lista = new ArrayList();
        for (int i = 0; i < linhas.size(); i++) {
            lista.add(linhas.get(i).toString());
        }
        return lista;
    }

}
