package DAOs;

import Entidades.Endereco;
import java.util.ArrayList;
import java.util.List;

public class DAOEndereco extends DAOGenerico<Endereco> {

    public DAOEndereco() {
        super(Endereco.class);
    }

    public int autoIdEndereco() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idEndereco) FROM Endereco e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Endereco> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Endereco e WHERE e.nomeEndereco LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Endereco> listById(int id) {
        return em.createQuery("SELECT e FROM Endereco e WHERE e.idEndereco = :id").setParameter("id", id).getResultList();
    }

    public List<Endereco> listInOrderNome() {
        return em.createQuery("SELECT e FROM Endereco e ORDER BY e.nomeEndereco").getResultList();
    }

    public List<Endereco> listInOrderId() {
        return em.createQuery("SELECT e FROM Endereco e ORDER BY e.idEndereco").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Endereco> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdEndereco() + "-" + lf.get(i).getNomeEndereco());
        }
        return ls;
    }
}
