package com.g3.user.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
// import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "tb_user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="O campo NOME deve ser preenchido.")
    @Column(name = "user_name")
    private String name;

    //@CPF
    @NotBlank(message="O campo CPF deve ser preenchido.")
    @Column(name = "user_cpf", length = 11)
    private String cpf;

    @NotBlank(message="O campo TELEFONE deve ser preenchido.")
    @Column(name = "user_phone", length = 11)
    private String phone;

    @NotNull(message="O campo DATA DE NASCIMENTO deve ser preenchido.")
    @Column(name = "user_birthday")
    private LocalDate birthday;

    @Email(message="O campo EMAIL deve ser preenchido corretamente.")
    @NotBlank(message="O campo EMAIL deve ser preenchido.")
    @Column(name = "user_email")
    private String email;
}
