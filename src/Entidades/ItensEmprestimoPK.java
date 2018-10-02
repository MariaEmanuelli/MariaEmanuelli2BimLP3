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
 * @author alexa
 */
@Embeddable
public class ItensEmprestimoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_livro")
    private int idLivro;
    @Basic(optional = false)
    @Column(name = "idEmprestimo")
    private int idEmprestimo;

    public ItensEmprestimoPK() {
    }

    public ItensEmprestimoPK(int idLivro, int idEmprestimo) {
        this.idLivro = idLivro;
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLivro;
        hash += (int) idEmprestimo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensEmprestimoPK)) {
            return false;
        }
        ItensEmprestimoPK other = (ItensEmprestimoPK) object;
        if (this.idLivro != other.idLivro) {
            return false;
        }
        if (this.idEmprestimo != other.idEmprestimo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ItensEmprestimoPK[ idLivro=" + idLivro + ", idEmprestimo=" + idEmprestimo + " ]";
    }
    
}
