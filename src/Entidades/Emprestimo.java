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
 * @author Maria Emanuelli
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
    @Column(name = "data_retirada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRetirada;
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emprestimo")
    private List<ItensEmprestimo> itensEmprestimoList;

    public Emprestimo() {
    }

    public Emprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Emprestimo(Integer idEmprestimo, Date dataRetirada) {
        this.idEmprestimo = idEmprestimo;
        this.dataRetirada = dataRetirada;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
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
