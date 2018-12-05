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
 * @author alexa
 */
@Entity
@Table(name = "itens_emprestimo")
@NamedQueries({
    @NamedQuery(name = "ItensEmprestimo.findAll", query = "SELECT i FROM ItensEmprestimo i")})
public class ItensEmprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_itens_emprestimo")
    private Integer idItensEmprestimo;
    @Basic(optional = false)
    @Column(name = "data_devolucao_itens_emprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucaoItensEmprestimo;
    @JoinColumn(name = "emprestimo_id_emprestimo", referencedColumnName = "id_emprestimo")
    @ManyToOne(optional = false)
    private Emprestimo emprestimoIdEmprestimo;
    @JoinColumn(name = "livro_id_livro", referencedColumnName = "id_livro")
    @ManyToOne(optional = false)
    private Livro livroIdLivro;

    public ItensEmprestimo() {
    }

    public ItensEmprestimo(Integer idItensEmprestimo) {
        this.idItensEmprestimo = idItensEmprestimo;
    }

    public ItensEmprestimo(Integer idItensEmprestimo, Date dataDevolucaoItensEmprestimo) {
        this.idItensEmprestimo = idItensEmprestimo;
        this.dataDevolucaoItensEmprestimo = dataDevolucaoItensEmprestimo;
    }

    public Integer getIdItensEmprestimo() {
        return idItensEmprestimo;
    }

    public void setIdItensEmprestimo(Integer idItensEmprestimo) {
        this.idItensEmprestimo = idItensEmprestimo;
    }

    public Date getDataDevolucaoItensEmprestimo() {
        return dataDevolucaoItensEmprestimo;
    }

    public void setDataDevolucaoItensEmprestimo(Date dataDevolucaoItensEmprestimo) {
        this.dataDevolucaoItensEmprestimo = dataDevolucaoItensEmprestimo;
    }

    public Emprestimo getEmprestimoIdEmprestimo() {
        return emprestimoIdEmprestimo;
    }

    public void setEmprestimoIdEmprestimo(Emprestimo emprestimoIdEmprestimo) {
        this.emprestimoIdEmprestimo = emprestimoIdEmprestimo;
    }

    public Livro getLivroIdLivro() {
        return livroIdLivro;
    }

    public void setLivroIdLivro(Livro livroIdLivro) {
        this.livroIdLivro = livroIdLivro;
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
