package br.com.api.g5.dto;

public class PedidoResponseDTO {

    private String nomeProduto;
    private String descricaoProduto;
    private String nomeFuncionario;
    private Integer itemQuantidade;
    private Double valorTotal;
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
    public Integer getItemQuantidade() {
        return itemQuantidade;
    }
    public void setItemQuantidade(Integer itemQuantidade) {
        this.itemQuantidade = itemQuantidade;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public PedidoResponseDTO() {
    }
    public PedidoResponseDTO(String nomeProduto, String descricaoProduto, String nomeFuncionario,
            Integer itemQuantidade, Double valorTotal) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.nomeFuncionario = nomeFuncionario;
        this.itemQuantidade = itemQuantidade;
        this.valorTotal = valorTotal;
    }   
}
