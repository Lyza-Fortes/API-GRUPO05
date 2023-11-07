package br.com.api.g5.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_email")
public class Email {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_email")
    private Integer id;

    @NotNull(message = "É necessário passar um e-mail")
    @Column(name = "email_solicitante")
    private String email;

    @NotNull(message = "Campo assunto não pode ser nulo")
    @Column(name = "assunto")
    private String assunto;

    @NotNull(message = "Campo mensagem não pode ser nulo")
    @Column(name = "mensagem")
    private String mensagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Email() {
    }

    public Email(Integer id, @NotNull(message = "Campo assunto não pode ser nulo") String assunto,
            @NotNull(message = "Campo mensagem não pode ser nulo") String mensagem) {
        this.id = id;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }
}
