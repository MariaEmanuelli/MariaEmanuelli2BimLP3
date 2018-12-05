/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @Id
    @Basic(optional = false)
    @Column(name = "id_emprestimo")
    private Integer idEmprestimo;
    @Basic(optional = false)
    @Column(name = "data_emprestimo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmprestimo;
    @JoinColumn(name = "cliente_id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emprestimoIdEmprestimo")
    private List<ItensEmprestimo> itensEmprestimoList;

    public Emprestimo() {
    }

    public Emprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Emprestimo(Integer idEmprestimo, Date dataEmprestimo) {
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Cliente getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(Cliente clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
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
        hash += (idEmprestimo != null ? idEmprestimo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        if ((this.idEmprestimo == null && other.idEmprestimo != null) || (this.idEmprestimo != null && !this.idEmprestimo.equals(other.idEmprestimo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Emprestimo[ idEmprestimo=" + idEmprestimo + " ]";
    }
    
}
