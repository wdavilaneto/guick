/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.sev.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import javax.validation.constraints.*;

import br.mp.mprj.commons.json.JsonDateDeserializer;
import br.mp.mprj.commons.json.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.format.annotation.DateTimeFormat;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * Candidato
 * A classe Candidato representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_CANDIDATO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_CANDIDATO")
@SequenceGenerator(name = "SEVO_SQ_SCAD_DK", sequenceName = "SEVO_SQ_SCAD_DK")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SCAD_DK")
    @Column(name="SCAD_DK" )
    private Long id;

    @Size(max = 8)
    @Column(name = "SCAD_CDMATRICULA", length = 8)
    String cdmatricula;

    @Size(max = 145)
    @Column(name = "SCAD_NM_CANDIDATO", length = 145)
    String nome;

    @Column(name = "SCAD_FOTO")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] foto;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SCAD_DT_NASCIMENTO", length = 7)
    Date dataNascimento;

    @Size(max = 200)
    @Column(name = "SCAD_EMAIL", length = 200)
    String email;

    @Size(max = 145)
    @Column(name = "SCAD_NM_COMPLETO", length = 145)
    String nomeCompleto;

    @Column(name = "SCAD_CRITERIO_DESEMPATE", length = 22)
    Long criterioDesempate;

    @Size(max = 200)
    @Column(name = "SCAD_LOGIN", length = 200)
    String login;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidato")
    private List<ResultadoApuracao> resultadoApuracaoCollection;
    @ManyToOne
    @JoinColumn(name = "SCAD_CAPD_DK")
    private CandidatoPadrao candidatoPadrao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SCAD_SELE_DK", nullable = false)
    private Eleicao eleicao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SEVO_VOTO_APURADO", joinColumns = {
            @JoinColumn(name = "VOAP_SCAD_DK", nullable = false, updatable = false)
            }, inverseJoinColumns = {
            @JoinColumn(name = "VOAP_SCED_DK", nullable = false, updatable = false)
            })
    @JsonIgnore
    List<Cedula> cedulaCollection; 


    /**
     * Getter para id.
     * @return valor do campo
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter para id.
     * @param value valor para id
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Getter para cdmatricula.
     * @return valor do campo
     */
    public String getCdmatricula() {
        return this.cdmatricula;
    }

    /**
     * Setter para cdmatricula.
     * @param value valor para cdmatricula
     */
    public void setCdmatricula(String value) {
        this.cdmatricula = value;
    }

    /**
     * Getter para nome.
     * @return valor do campo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Setter para nome.
     * @param value valor para nome
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Getter para foto.
     * @return valor do campo
     */
    public byte[] getFoto() {
        if (this.foto != null) {
            return (byte[]) this.foto.clone();
        }
        return null;
    }

    /**
     * Setter para foto.
     * @param value valor para foto
     */
    public void setFoto(byte[] value) {
        if (value != null) {
            this.foto = (byte[]) value.clone();
        } else {
            this.foto = null;
        }
    }

    /**
     * Getter para dataNascimento.
     * @return valor do campo
     */
    public Date getDataNascimento() {
        if (this.dataNascimento != null) {
            return (Date) this.dataNascimento.clone();
        }
        return null;
    }

    /**
     * Setter para dataNascimento.
     * @param value valor para dataNascimento
     */
    public void setDataNascimento(Date value) {
        if (value != null) {
            this.dataNascimento = (Date) value.clone();
        } else {
            this.dataNascimento = null;
        }
    }

    /**
     * Getter para email.
     * @return valor do campo
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter para email.
     * @param value valor para email
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Getter para nomeCompleto.
     * @return valor do campo
     */
    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    /**
     * Setter para nomeCompleto.
     * @param value valor para nomeCompleto
     */
    public void setNomeCompleto(String value) {
        this.nomeCompleto = value;
    }

    /**
     * Getter para criterioDesempate.
     * @return valor do campo
     */
    public Long getCriterioDesempate() {
        return this.criterioDesempate;
    }

    /**
     * Setter para criterioDesempate.
     * @param value valor para criterioDesempate
     */
    public void setCriterioDesempate(Long value) {
        this.criterioDesempate = value;
    }

    /**
     * Getter para login.
     * @return valor do campo
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Setter para login.
     * @param value valor para login
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Getter para candidatoPadrao.
     * @return valor do campo
     */
    public CandidatoPadrao getCandidatoPadrao() {
        return this.candidatoPadrao;
    }

    /**
     * Setter para candidatoPadrao.
     * @param value valor para candidatoPadrao
     */
    public void setCandidatoPadrao(CandidatoPadrao value) {
        this.candidatoPadrao = value;
    }

    /**
     * Getter para eleicao.
     * @return valor do campo
     */
    public Eleicao getEleicao() {
        return this.eleicao;
    }

    /**
     * Setter para eleicao.
     * @param value valor para eleicao
     */
    public void setEleicao(Eleicao value) {
        this.eleicao = value;
    }

    /**
     * Getter para resultadoApuracaoCollection.
     * @return valor do campo
     */
    public List<ResultadoApuracao> getResultadoApuracaoCollection() {
        return this.resultadoApuracaoCollection;
    }

    /**
     * Setter para resultadoApuracaoCollection.
     * @param value valor para resultadoApuracaoCollection
     */
    public void setResultadoApuracaoCollection(List<ResultadoApuracao> value) {
        this.resultadoApuracaoCollection = value;
    }

    /**
     * Getter para cedulaCollection.
     * @return valor do campo
     */
    public List<Cedula> getCedulaCollection() {
        return this.cedulaCollection;
    }

    /**
     * Setter para cedulaCollection.
     * @param value valor para cedulaCollection
     */
    public void setCedulaCollection(List<Cedula> value) {
        this.cedulaCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(cdmatricula)
                .append(nome)
                .append(foto)
                .append(dataNascimento)
                .append(email)
                .append(nomeCompleto)
                .append(criterioDesempate)
                .append(login)
                .append(candidatoPadrao)
                .append(eleicao)
               .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Candidato rhs = (Candidato) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(cdmatricula, rhs.cdmatricula)
                .append(nome, rhs.nome)
                .append(foto, rhs.foto)
                .append(dataNascimento, rhs.dataNascimento)
                .append(email, rhs.email)
                .append(nomeCompleto, rhs.nomeCompleto)
                .append(criterioDesempate, rhs.criterioDesempate)
                .append(login, rhs.login)
                .append(candidatoPadrao, rhs.candidatoPadrao)
                .append(eleicao, rhs.eleicao)
                .isEquals();

    }

}
