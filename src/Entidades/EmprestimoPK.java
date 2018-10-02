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
public class EmprestimoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEmprestimo")
    private int idEmprestimo;
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private int idCliente;

    public EmprestimoPK() {
    }

    public EmprestimoPK(int idEmprestimo, int idCliente) {
        this.idEmprestimo = idEmprestimo;
        this.idCliente = idCliente;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmprestimo;
        hash += (int) idCliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmprestimoPK)) {
            return false;
        }
        EmprestimoPK other = (EmprestimoPK) object;
        if (this.idEmprestimo != other.idEmprestimo) {
            return false;
        }
        if (this.idCliente != other.idCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.EmprestimoPK[ idEmprestimo=" + idEmprestimo + ", idCliente=" + idCliente + " ]";
    }
    
}
