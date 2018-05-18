package br.com.desafioclientejava.model;

import java.util.List;

public class Loja {

    protected int id;
    protected String name;
    protected int cnpj;

    protected List<Cliente> clientes;

    protected List<Vendedor> vendedores;

    protected List<Venda> vendas;

    protected List<Categoria> categorias;

    protected List<Produto> estoque;

    public Loja(String name, int cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public Loja() {

    }

    /*public Cliente localizarCliente(String cpf) {

		for (Cliente cli : clientes) {

			if (cli.getCpf().equals(cpf))
				return cli;
		}

		return null;

	}
     */
    public void vender_Produto(Venda vend) {

        vendas.add(vend);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setClientes(List<Cliente> cli) {
        this.clientes = cli;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Produto> getEstoque() {
        return estoque;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void adicionarCliente(Cliente c) {
        clientes.add(c);
    }

    public void adicionarVendedor(Vendedor v) {
        vendedores.add(v);
    }

    public void removerCliente(Cliente c) {
        clientes.remove(c);
    }

    public void removerVendedor(Vendedor v) {
        vendedores.remove(v);
    }

    public void adicionarCategoria(Categoria c) {
        categorias.add(c);
    }

    public void removerCategorias(Categoria c) {
        categorias.remove(c);
    }

    public void adicionarProduto(Produto c) {
        estoque.add(c);
    }

    public void adicionarProdutoTransferido(Produto novo) {
        for (Produto produto : getEstoque()) {
            if (produto.getNome() == novo.getNome()) {
                estoque.remove(produto);
                produto.addUnidade(novo.getQuantidade());
                novo = produto;
            }
            if (produto.getCod_prod() == novo.getCod_prod()) {
                novo.nextCod_Prod();
            }
        }
        estoque.add(novo);
    }

    public void removerProduto(Produto c) {
        estoque.remove(c);
    }

    public int getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addVenda(Venda venda) {
        int aux = venda.getCod_venda();
        venda.setCod_venda(aux++);
        this.vendas.add(venda);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }

}
