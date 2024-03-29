package com.gabrielbergamim.cursomc.dto;

import com.gabrielbergamim.cursomc.services.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caractéres")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "Email inválido")
    private String email;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cpfOuCnpj;
    
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String senha;

    private Integer tipo;

    private String logradouro;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String cep;

    private Integer cidadeID;
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String telefone;

    public ClienteNewDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getCidadeID() {
        return cidadeID;
    }

    public void setCidadeID(Integer cidadeID) {
        this.cidadeID = cidadeID;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
