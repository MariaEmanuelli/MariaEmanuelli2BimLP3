package DAOs;

import Entidades.ItensEmprestimo;
import java.util.List;

public class DAOItensEmprestimo extends DAOGenerico<ItensEmprestimo> {

    public DAOItensEmprestimo() {
        super(ItensEmprestimo.class);
    }

    public int autoIdItensEmprestimo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idItensEmprestimo) FROM ItensEmprestimo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

//    public List<ItensEmprestimo> listByNome(String nome) {
//        return em.createQuery("SELECT e FROM ItensEmprestimo e WHERE e.nomeItensEmprestimo LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
//    }
    public List<ItensEmprestimo> listById(int id) {
        return em.createQuery("SELECT e FROM ItensEmprestimo e WHERE e.idItensEmprestimo = :id").setParameter("id", id).getResultList();
    }

//    public List<ItensEmprestimo> listInOrderNome() {
//        return em.createQuery("SELECT e FROM ItensEmprestimo e ORDER BY e.nomeItensEmprestimo").getResultList();
//    }
    public List<ItensEmprestimo> listInOrderId() {
        return em.createQuery("SELECT e FROM ItensEmprestimo e ORDER BY e.idItensEmprestimo").getResultList();
    }

//    public List<String> listInOrderNomeStrings(String qualOrdem) {
//        List<ItensEmprestimo> lf;
//        if (qualOrdem.equals("id")) {
//            lf = listInOrderId();
//        } else {
//            lf = listInOrderNome();
//        }
//
//        List<String> ls = new ArrayList<>();
//        for (int i = 0; i < lf.size(); i++) {
//            ls.add(lf.get(i).getIdItensEmprestimo() + "-" + lf.get(i).getNomeItensEmprestimo());
//        }
//        return ls;
//    }
}
