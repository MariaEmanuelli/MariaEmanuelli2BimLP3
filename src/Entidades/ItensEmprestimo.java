/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "itens_emprestimo")
@NamedQueries({
    @NamedQuery(name = "ItensEmprestimo.findAll", query = "SELECT i FROM ItensEmprestimo i")})
public class ItensEmprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "data_devolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    @Id
    @Basic(optional = false)
    @Column(name = "id_itens_emprestimo")
    private Integer idItensEmprestimo;
    @JoinColumn(name = "emprestimo_id_emprestimo", referencedColumnName = "id_emprestimo")
    @ManyToOne(optional = false)
    private Emprestimo emprestimoIdEmprestimo;
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Livro livroId;

    public ItensEmprestimo() {
    }

    public ItensEmprestimo(Integer idItensEmprestimo) {
        this.idItensEmprestimo = idItensEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getIdItensEmprestimo() {
        return idItensEmprestimo;
    }

    public void setIdItensEmprestimo(Integer idItensEmprestimo) {
        this.idItensEmprestimo = idItensEmprestimo;
    }

    public Emprestimo getEmprestimoIdEmprestimo() {
        return emprestimoIdEmprestimo;
    }

    public void setEmprestimoIdEmprestimo(Emprestimo emprestimoIdEmprestimo) {
        this.emprestimoIdEmprestimo = emprestimoIdEmprestimo;
    }

    public Livro getLivroId() {
        return livroId;
    }

    public void setLivroId(Livro livroId) {
        this.livroId = livroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItensEmprestimo != null ? idItensEmprestimo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensEmprestimo)) {
            return false;
        }
        ItensEmprestimo other = (ItensEmprestimo) object;
        if ((this.idItensEmprestimo == null && other.idItensEmprestimo != null) || (this.idItensEmprestimo != null && !this.idItensEmprestimo.equals(other.idItensEmprestimo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ItensEmprestimo[ idItensEmprestimo=" + idItensEmprestimo + " ]";
    }
    
}
