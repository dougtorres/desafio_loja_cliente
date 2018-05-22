/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafioclientejava.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.desafioclientejava.model.Cliente;
import br.com.desafioclientejava.model.Loja;

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
	
	public List<Cliente> listar_clientes(int cnpj) throws Exception {
		Gson gs = new Gson();
		String json = http.sendGet(url + "loja/cliente/list/"+cnpj, "GET");
		Type clientesType = new TypeToken<List<Cliente>>() {
		}.getType();
		List<Cliente> clientes = gs.fromJson(json, clientesType);
		if(clientes == null)
			throw new Exception("Nenhum cliente cadastrado!");
		return clientes;
	}

	public boolean cadastrar_loja(String nome, int cnpj) throws Exception {

		Gson gs = new Gson();
		Loja nova = new Loja(nome, cnpj);
		String json = gs.toJson(nova);
		if ((http.sendPost(url + "loja/insert", json, "POST")).equals("true"))
			return true;
		return false;
	}

	public boolean cadastrar_cliente(String nome, String cpf, String email, String tel, Loja loja) throws Exception {
		Gson gs = new Gson();
		if(buscar_cliente(loja.getCnpj(), cpf) != null)
			throw new Exception("Cliente já cadastrado!");
		Cliente novo = new Cliente(nome, cpf, email, tel);
		String json = gs.toJson(novo);
		if ((http.sendPost(url + "loja/cliente/insert/" + loja.getCnpj(), json, "POST")).equals("true"))
			return true;
		throw new Exception("Cliente não cadastrado!");
	}

	public void alterar_loja(int cnpj, String novoNome, int novoCnpj) throws Exception {
		Gson gs = new Gson();
		Loja aux = buscar_loja(cnpj);
		aux.setName(novoNome);
		aux.setCnpj(cnpj);
		String json = gs.toJson(aux);
		http.sendPost(url + "loja/update/" + Integer.toString(cnpj), json, "PUT");
	}
	
	public void alterar_cliente(String cpf, String novoNome, String novoEmail, String novoTel, Loja loja) throws Exception {
		Gson gs = new Gson();
		Cliente cliente = buscar_cliente(loja.getCnpj(), cpf);
		cliente.setNome(novoNome);
		cliente.setEmail(novoEmail);
		cliente.setTel(novoTel);
		String json = gs.toJson(cliente);
		http.sendPost(url + "loja/cliente/update/" + Integer.toString(loja.getCnpj()), json, "PUT");
	}

	public Loja buscar_loja(int cnpj) throws Exception {
		Gson gs = new Gson();
		String json = http.sendGet(url + "loja/find/" + Integer.toString(cnpj), "GET");
		Loja aux = gs.fromJson(json, Loja.class);
		if (aux != null)
			return aux;
		else
			throw new Exception("Loja não encontrada!");
	}
	
	public Cliente buscar_cliente(int cnpj, String cpf) throws Exception {
		Gson gs = new Gson();
		String json = http.sendGet(url + "loja/cliente/find/" + Integer.toString(cnpj)+"/"+cpf, "GET");
		Cliente aux = gs.fromJson(json, Cliente.class);
		if (aux != null)
			return aux;
		else
			return null;
	}

	public void remover_loja(int cnpj) throws Exception {
		if (buscar_loja(cnpj) != null)
			http.sendGet(url + "loja/delete/" + Integer.toString(cnpj), "DELETE");
	}
	
	public void remover_cliente(int cnpj, String cpf) throws Exception {
		if (buscar_cliente(cnpj, cpf) != null)
			http.sendGet(url + "loja/cliente/delete/" +cpf, "DELETE");
	}

}
