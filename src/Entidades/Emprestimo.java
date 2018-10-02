/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "emprestimo")
@NamedQueries({
    @NamedQuery(name = "Emprestimo.findAll", query = "SELECT e FROM Emprestimo e")})
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmprestimoPK emprestimoPK;
    @Column(name = "dataRetirada")
    private String dataRetirada;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emprestimo")
    private List<ItensEmprestimo> itensEmprestimoList;

    public Emprestimo() {
    }

    public Emprestimo(EmprestimoPK emprestimoPK) {
        this.emprestimoPK = emprestimoPK;
    }

    public Emprestimo(int idEmprestimo, int idCliente) {
        this.emprestimoPK = new EmprestimoPK(idEmprestimo, idCliente);
    }

    public EmprestimoPK getEmprestimoPK() {
        return emprestimoPK;
    }

    public void setEmprestimoPK(EmprestimoPK emprestimoPK) {
        this.emprestimoPK = emprestimoPK;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItensEmprestimo> getItensEmprestimoList() {
        return itensEmprestimoList;
    }

    public void setItensEmprestimoList(List<ItensEmprestimo> itensEmprestimoList) {
        this.itensEmprestimoList = itensEmprestimoList;
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
