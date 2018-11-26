/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Maria Emanuelli
 */
@Embeddable
public class ItensEmprestimoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "livro_id")
    private int livroId;
    @Basic(optional = false)
    @Column(name = "emprestimo_id_emprestimo")
    private int emprestimoIdEmprestimo;

    public ItensEmprestimoPK() {
    }

    public ItensEmprestimoPK(int livroId, int emprestimoIdEmprestimo) {
        this.livroId = livroId;
        this.emprestimoIdEmprestimo = emprestimoIdEmprestimo;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public int getEmprestimoIdEmprestimo() {
        return emprestimoIdEmprestimo;
    }

    public void setEmprestimoIdEmprestimo(int emprestimoIdEmprestimo) {
        this.emprestimoIdEmprestimo = emprestimoIdEmprestimo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) livroId;
        hash += (int) emprestimoIdEmprestimo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensEmprestimoPK)) {
            return false;
        }
        ItensEmprestimoPK other = (ItensEmprestimoPK) object;
        if (this.livroId != other.livroId) {
            return false;
        }
        if (this.emprestimoIdEmprestimo != other.emprestimoIdEmprestimo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ItensEmprestimoPK[ livroId=" + livroId + ", emprestimoIdEmprestimo=" + emprestimoIdEmprestimo + " ]";
    }
    
}
