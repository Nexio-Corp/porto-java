package com.fiap.beans.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fiap.beans.user.Usuario;
import com.fiap.beans.user.bike.Acessorio;
import com.fiap.beans.user.bike.Bike;
import com.fiap.beans.user.bike.Marca;
import com.fiap.beans.user.bike.ModeloBike;
import com.fiap.beans.user.bike.Modificacao;
import com.fiap.beans.user.bike.data.BikeDao;
import com.fiap.beans.vistoria.Vistoria;

public class App {
    public static void main(String[] args) throws Exception {
        Marca feitoEmCasa = new Marca("Feito em casa");
        Marca caloi = new Marca("Caloi");
        Acessorio lacinhoAcessorio = new Acessorio("Lacinho", "Um lancinho no guidão da bike", 5, feitoEmCasa);
        ModeloBike caloi10 = new ModeloBike("Caloi 10", caloi, 10, "Infantil");
        Modificacao rodaAro10 = new Modificacao("Roda aro 10", "Roda aro 10 para bicicletas infantis", 10, feitoEmCasa);
        Usuario user = Usuario.cadastroComEmailSenha("cliente@email.com", "123456");

        user.cadastroDadosCliente("Cliente", "12345678910", "12345678", "Rua do Cliente",
                new Date(System.currentTimeMillis()), "123456789");

        user.getCliente().cadastroBike(caloi10, new ArrayList<Modificacao>(), new ArrayList<Acessorio>(), 10,
                new Date(System.currentTimeMillis()), "Notas", "Utilização", "123456789", false, "123456789");

        user.getCliente().getBikes().get(0).addModificacao(rodaAro10).addAcessorio(lacinhoAcessorio);

        Vistoria vistoria = new Vistoria(user.getCliente().getBikes().get(0), new String[] { "link1", "link2" });

        System.out.println(user);
        vistoria.validarVistoria();
        if (vistoria.isAprovada()) {
            System.out.println("Vistoria aprovada!");
        } else {
            System.out.println("Vistoria reprovada\nMotivo: " + vistoria.getMotivoRejeicao());

            BikeDao dao = new BikeDao();

            try {
                dao.inserir(caloi);
            } catch (SQLException erro) {
                System.out.println("Erro ao inserir no banco de dados: " + erro.getMessage());
            }

        }

    }
}
