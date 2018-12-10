/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "endereco")
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_endereco")
    private Integer idEndereco;
    @Basic(optional = false)
    @Column(name = "nome_endereco")
    private String nomeEndereco;
    @Basic(optional = false)
    @Column(name = "cep_endereco")
    private String cepEndereco;
    @Basic(optional = false)
    @Column(name = "numero_endereco")
    private int numeroEndereco;
    @JoinColumn(name = "editora_id_editora", referencedColumnName = "id_editora")
    @ManyToOne(optional = false)
    private Editora editoraIdEditora;

    public Endereco() {
    }

    public Endereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco(Integer idEndereco, String nomeEndereco, String cepEndereco, int numeroEndereco) {
        this.idEndereco = idEndereco;
        this.nomeEndereco = nomeEndereco;
        this.cepEndereco = cepEndereco;
        this.numeroEndereco = numeroEndereco;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNomeEndereco() {
        return nomeEndereco;
    }

    public void setNomeEndereco(String nomeEndereco) {
        this.nomeEndereco = nomeEndereco;
    }

    public String getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(String cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public Editora getEditoraIdEditora() {
        return editoraIdEditora;
    }

    public void setEditoraIdEditora(Editora editoraIdEditora) {
        this.editoraIdEditora = editoraIdEditora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEndereco != null ? idEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.idEndereco == null && other.idEndereco != null) || (this.idEndereco != null && !this.idEndereco.equals(other.idEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Endereco[ idEndereco=" + idEndereco + " ]";
    }
    
}
