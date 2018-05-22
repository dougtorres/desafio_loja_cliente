/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafioclientejava.util;

import br.com.desafioclientejava.model.Loja;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class DesafioAPI {

    public static Http http = new Http();
    private final String url = "http://localhost:8080/DesafioConductorWS/webresources/";

    public List<Loja> listar_lojas() throws Exception {
        Gson gs = new Gson();
        String json = http.sendGet(url + "loja/list", "GET");
        Type lojasType = new TypeToken<List<Loja>>() {
        }.getType();
        List<Loja> lojas = gs.fromJson(json, lojasType);
        return lojas;
    }
    
    public boolean cadastrar_loja(String nome, int cnpj) throws Exception {

        Gson gs = new Gson();
        Loja nova = new Loja(nome,cnpj);
        String json = gs.toJson(nova);
        if((http.sendPost(url + "loja/insert", json, "POST")).equals("true"))
                return true;
        return false;
    }
    
    public void alterar_loja(int cnpj, String novoNome, int novoCnpj) throws Exception {
        Gson gs = new Gson();
        String json = http.sendGet(url+"find/"+Integer.toString(cnpj), "GET");
        Loja aux = gs.fromJson(json, Loja.class);
        aux.setName(novoNome);
        aux.setCnpj(cnpj);
        json = gs.toJson(aux);
        http.sendPost(url + "loja/update/"+Integer.toString(cnpj), json, "PUT");
    }
    
    public Loja buscar_loja(int cnpj) throws Exception {
        Gson gs = new Gson();
        String json = http.sendGet(url+"find/"+Integer.toString(cnpj), "GET");
        Loja aux = gs.fromJson(json, Loja.class);
        if(aux == null)
            throw new Exception("CNPJ não encontrado!");
        return aux;
    }
    
    public void remover_loja(int cnpj) throws Exception {
       if(buscar_loja(cnpj)!=null)
        http.sendGet(url + "loja/delete/"+Integer.toString(cnpj), "DELETE");           
    }

}
