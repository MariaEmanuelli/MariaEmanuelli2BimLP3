package GUIs;

public class Atributo {

    private int idVenda;
    private int idFilme;
    private int quantidade;
    private double preco;

    public Atributo() {
    }

    public Atributo(int idVenda, int idFilme, int quantidade, double preco) {
        this.idVenda = idVenda;
        this.idFilme = idFilme;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Atributo{" + "idVenda=" + idVenda + ", idFilme=" + idFilme + ", quantidade=" + quantidade + ", preco=" + preco + '}';
    }
}