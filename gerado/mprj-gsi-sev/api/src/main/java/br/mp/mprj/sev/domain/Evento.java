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
 * Evento
 * A classe Evento representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_EVENTO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_EVENTO")
@SequenceGenerator(name = "SEVO_SQ_SEVT_DK", sequenceName = "SEVO_SQ_SEVT_DK")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SEVT_DK")
    @Column(name="SEVT_DK" )
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SEVT_DT_REGISTRO", nullable = false, length = 7)
    Date dataRegistro;

    @NotNull
    @Size(max = 250)
    @Column(name = "SEVT_OCORRENCIA", nullable = false, length = 250)
    String ocorrencia;

    @Size(max = 250)
    @Column(name = "SEVT_JUSTIFICATIVA", length = 250)
    String justificativa;

    @NotNull
    @Size(max = 200)
    @Column(name = "SEVT_LOGIN", nullable = false, length = 200)
    String login;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<ArquivoRelatorio> arquivoRelatorioCollection;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SEVT_SELE_DK", nullable = false)
    private Eleicao eleicao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SEVT_TEVT_DK", nullable = false)
    private TipoEvento tipoEvento;



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
     * Getter para dataRegistro.
     * @return valor do campo
     */
    public Date getDataRegistro() {
        if (this.dataRegistro != null) {
            return (Date) this.dataRegistro.clone();
        }
        return null;
    }

    /**
     * Setter para dataRegistro.
     * @param value valor para dataRegistro
     */
    public void setDataRegistro(Date value) {
        if (value != null) {
            this.dataRegistro = (Date) value.clone();
        } else {
            this.dataRegistro = null;
        }
    }

    /**
     * Getter para ocorrencia.
     * @return valor do campo
     */
    public String getOcorrencia() {
        return this.ocorrencia;
    }

    /**
     * Setter para ocorrencia.
     * @param value valor para ocorrencia
     */
    public void setOcorrencia(String value) {
        this.ocorrencia = value;
    }

    /**
     * Getter para justificativa.
     * @return valor do campo
     */
    public String getJustificativa() {
        return this.justificativa;
    }

    /**
     * Setter para justificativa.
     * @param value valor para justificativa
     */
    public void setJustificativa(String value) {
        this.justificativa = value;
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
     * Getter para tipoEvento.
     * @return valor do campo
     */
    public TipoEvento getTipoEvento() {
        return this.tipoEvento;
    }

    /**
     * Setter para tipoEvento.
     * @param value valor para tipoEvento
     */
    public void setTipoEvento(TipoEvento value) {
        this.tipoEvento = value;
    }

    /**
     * Getter para arquivoRelatorioCollection.
     * @return valor do campo
     */
    public List<ArquivoRelatorio> getArquivoRelatorioCollection() {
        return this.arquivoRelatorioCollection;
    }

    /**
     * Setter para arquivoRelatorioCollection.
     * @param value valor para arquivoRelatorioCollection
     */
    public void setArquivoRelatorioCollection(List<ArquivoRelatorio> value) {
        this.arquivoRelatorioCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(dataRegistro)
                .append(ocorrencia)
                .append(justificativa)
                .append(login)
                .append(eleicao)
                .append(tipoEvento)
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

        Evento rhs = (Evento) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(dataRegistro, rhs.dataRegistro)
                .append(ocorrencia, rhs.ocorrencia)
                .append(justificativa, rhs.justificativa)
                .append(login, rhs.login)
                .append(eleicao, rhs.eleicao)
                .append(tipoEvento, rhs.tipoEvento)
                .isEquals();

    }

}
