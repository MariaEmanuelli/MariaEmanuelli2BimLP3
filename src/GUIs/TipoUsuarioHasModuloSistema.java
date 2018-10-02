/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import DAOs.DAOGenero;
import DAOs.DAOCliente;
import Entidades.ModuloSistema;
import Entidades.TipoUsuario;
import java.util.List;

/**
 *
 * @author alexa
 */
public class TipoUsuarioHasModuloSistema {

    public static void main(String[] args) {
        ModuloSistema moduloSistema = new ModuloSistema();
        TipoUsuario tipoUsuario = new TipoUsuario();
        DAOGenero daoModuloSistema = new DAOGenero();
        DAOCliente daoTipoUsuario = new DAOCliente();

        moduloSistema = daoModuloSistema.obter(2);
        List<TipoUsuario> ltu = moduloSistema.getTipoUsuarioList();
        System.out.println("tamanho de ltu: " + ltu.size());

        ltu.remove(daoTipoUsuario.obter(1));
        //ltu.add(daoTipoUsuario.obter(1));
        moduloSistema.setTipoUsuarioList(ltu);
        daoModuloSistema.atualizar(moduloSistema);

    }

}
