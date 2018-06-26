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
public class EmprestimoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @Column(name = "id_livro")
    private int idLivro;

    public EmprestimoPK() {
    }

    public EmprestimoPK(int idCliente, int idLivro) {
        this.idCliente = idCliente;
        this.idLivro = idLivro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCliente;
        hash += (int) idLivro;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmprestimoPK)) {
            return false;
        }
        EmprestimoPK other = (EmprestimoPK) object;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.idLivro != other.idLivro) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.EmprestimoPK[ idCliente=" + idCliente + ", idLivro=" + idLivro + " ]";
    }
    
}
