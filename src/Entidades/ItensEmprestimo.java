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
 * @author alexa
 */
@Entity
@Table(name = "itens_emprestimo")
@NamedQueries({
    @NamedQuery(name = "ItensEmprestimo.findAll", query = "SELECT i FROM ItensEmprestimo i")})
public class ItensEmprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItensEmprestimoPK itensEmprestimoPK;
    @Column(name = "data_emprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;
    @JoinColumn(name = "id_livro", referencedColumnName = "id_livro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Livro livro;
    @JoinColumn(name = "id_emprestimo", referencedColumnName = "id_emprestimo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Emprestimo emprestimo;

    public ItensEmprestimo() {
    }

    public ItensEmprestimo(ItensEmprestimoPK itensEmprestimoPK) {
        this.itensEmprestimoPK = itensEmprestimoPK;
    }

    public ItensEmprestimo(int idLivro, int idEmprestimo) {
        this.itensEmprestimoPK = new ItensEmprestimoPK(idLivro, idEmprestimo);
    }

    public ItensEmprestimoPK getItensEmprestimoPK() {
        return itensEmprestimoPK;
    }

    public void setItensEmprestimoPK(ItensEmprestimoPK itensEmprestimoPK) {
        this.itensEmprestimoPK = itensEmprestimoPK;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itensEmprestimoPK != null ? itensEmprestimoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensEmprestimo)) {
            return false;
        }
        ItensEmprestimo other = (ItensEmprestimo) object;
        if ((this.itensEmprestimoPK == null && other.itensEmprestimoPK != null) || (this.itensEmprestimoPK != null && !this.itensEmprestimoPK.equals(other.itensEmprestimoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ItensEmprestimo[ itensEmprestimoPK=" + itensEmprestimoPK + " ]";
    }
    
}
