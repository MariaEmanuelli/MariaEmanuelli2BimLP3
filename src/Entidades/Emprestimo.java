/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Maria Emanuelli
 */
@Entity
@Table(name = "emprestimo")
@NamedQueries({
    @NamedQuery(name = "Emprestimo.findAll", query = "SELECT e FROM Emprestimo e")})
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmprestimoPK emprestimoPK;
    @Column(name = "data_retirada")
    @Temporal(TemporalType.DATE)
    private Date dataRetirada;
    @Column(name = "data_devolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "id_livro", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Livro livro;

    public Emprestimo() {
    }

    public Emprestimo(EmprestimoPK emprestimoPK) {
        this.emprestimoPK = emprestimoPK;
    }

    public Emprestimo(int idCliente, int idLivro) {
        this.emprestimoPK = new EmprestimoPK(idCliente, idLivro);
    }

    public EmprestimoPK getEmprestimoPK() {
        return emprestimoPK;
    }

    public void setEmprestimoPK(EmprestimoPK emprestimoPK) {
        this.emprestimoPK = emprestimoPK;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emprestimoPK != null ? emprestimoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        if ((this.emprestimoPK == null && other.emprestimoPK != null) || (this.emprestimoPK != null && !this.emprestimoPK.equals(other.emprestimoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Emprestimo[ emprestimoPK=" + emprestimoPK + " ]";
    }
    
}
