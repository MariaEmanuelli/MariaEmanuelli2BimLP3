package daos;

import DAOs.DAOGenerico;
import static DAOs.DAOGenerico.em;
import Entidades.Livro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOLivro extends DAOGenerico<Livro> {

    public DAOLivro() {
        super(Livro.class);
    }

    public int autoIdLivro() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idLivro) FROM Livro e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Livro> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Livro e WHERE e.nomeLivro LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Livro> listById(int id) {
        return em.createQuery("SELECT e FROM Livro e WHERE e.idLivro = :id").setParameter("id", id).getResultList();
    }

    public List<Livro> listInOrderNome() {
        return em.createQuery("SELECT e FROM Livro e ORDER BY e.nomeLivro").getResultList();
    }

    public List<Livro> listInOrderId() {
        return em.createQuery("SELECT e FROM Livro e ORDER BY e.idLivro").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Livro> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getId() + "-" + lf.get(i).getNome());
        }
        return ls;
    }
    public String converteDateString(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false); 
        String dt= null;
        try {
            dt=sdf.format(data);
        } catch (Exception err) {
            System.out.println("Erro na data!!");
        }
        return dt;}
    public Date stringDate(String dt){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false); 
        Date data=null;
        try {
            data=sdf.parse(dt);
        } catch (Exception e) {
            System.out.println("Erro na data!");
        }
        return data;
    }
}

    
