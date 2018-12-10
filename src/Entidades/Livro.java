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
 * @author maria
 */
@Entity
@Table(name = "livro")
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_livro")
    private Integer idLivro;
    @Basic(optional = false)
    @Column(name = "nome_livro")
    private String nomeLivro;
    @Basic(optional = false)
    @Column(name = "edicao_livro")
    private String edicaoLivro;
    @Basic(optional = false)
    @Column(name = "ano_publicacao_livro")
    @Temporal(TemporalType.DATE)
    private Date anoPublicacaoLivro;
    @Basic(optional = false)
    @Column(name = "quantidade_estoque_livro")
    private int quantidadeEstoqueLivro;
    @JoinColumn(name = "autor_id_autor", referencedColumnName = "id_autor")
    @ManyToOne(optional = false)
    private Autor autorIdAutor;
    @JoinColumn(name = "editora_id_editora", referencedColumnName = "id_editora")
    @ManyToOne(optional = false)
    private Editora editoraIdEditora;
    @JoinColumn(name = "genero_id_genero", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero generoIdGenero;
    @JoinColumn(name = "status_id_status", referencedColumnName = "id_status")
    @ManyToOne(optional = false)
    private Status statusIdStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livroIdLivro")
    private List<ItensEmprestimo> itensEmprestimoList;

    public Livro() {
    }

    public Livro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Livro(Integer idLivro, String nomeLivro, String edicaoLivro, Date anoPublicacaoLivro, int quantidadeEstoqueLivro) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.edicaoLivro = edicaoLivro;
        this.anoPublicacaoLivro = anoPublicacaoLivro;
        this.quantidadeEstoqueLivro = quantidadeEstoqueLivro;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getEdicaoLivro() {
        return edicaoLivro;
    }

    public void setEdicaoLivro(String edicaoLivro) {
        this.edicaoLivro = edicaoLivro;
    }

    public Date getAnoPublicacaoLivro() {
        return anoPublicacaoLivro;
    }

    public void setAnoPublicacaoLivro(Date anoPublicacaoLivro) {
        this.anoPublicacaoLivro = anoPublicacaoLivro;
    }

    public int getQuantidadeEstoqueLivro() {
        return quantidadeEstoqueLivro;
    }

    public void setQuantidadeEstoqueLivro(int quantidadeEstoqueLivro) {
        this.quantidadeEstoqueLivro = quantidadeEstoqueLivro;
    }

    public Autor getAutorIdAutor() {
        return autorIdAutor;
    }

    public void setAutorIdAutor(Autor autorIdAutor) {
        this.autorIdAutor = autorIdAutor;
    }

    public Editora getEditoraIdEditora() {
        return editoraIdEditora;
    }

    public void setEditoraIdEditora(Editora editoraIdEditora) {
        this.editoraIdEditora = editoraIdEditora;
    }

    public Genero getGeneroIdGenero() {
        return generoIdGenero;
    }

    public void setGeneroIdGenero(Genero generoIdGenero) {
        this.generoIdGenero = generoIdGenero;
    }

    public Status getStatusIdStatus() {
        return statusIdStatus;
    }

    public void setStatusIdStatus(Status statusIdStatus) {
        this.statusIdStatus = statusIdStatus;
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
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Livro[ idLivro=" + idLivro + " ]";
    }
    
}
