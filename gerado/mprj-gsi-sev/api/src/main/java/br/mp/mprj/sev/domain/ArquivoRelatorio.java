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
 * ArquivoRelatorio
 * A classe ArquivoRelatorio representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_ARQUIVO_RELATORIO.
 *armazenar os relatórios em PDF gerados durante apuração para futuro downloads.
 */
@Entity
@Table(name = "SEVO_ARQUIVO_RELATORIO")
@SequenceGenerator(name = "SEVO_SQ_SARR_DK", sequenceName = "SEVO_SQ_SARR_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArquivoRelatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SARR_DK")
    @Column(name="SARR_DK" )
    private Long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SARR_DT_CRIACAO", nullable = false, length = 7)
    Date dataCriacao;

    @Column(name = "SARR_ARQUIVO")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] arquivo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SARR_SELE_DK", nullable = false)
    private Eleicao eleicao;

    @ManyToOne
    @JoinColumn(name = "SARR_SEVT_DK")
    private Evento evento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "SARR_TRET_DK", nullable = false)
    private TipoRelatorio tipoRelatorio;



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
     * Getter para dataCriacao.
     * @return valor do campo
     */
    public Date getDataCriacao() {
        if (this.dataCriacao != null) {
            return (Date) this.dataCriacao.clone();
        }
        return null;
    }

    /**
     * Setter para dataCriacao.
     * @param value valor para dataCriacao
     */
    public void setDataCriacao(Date value) {
        if (value != null) {
            this.dataCriacao = (Date) value.clone();
        } else {
            this.dataCriacao = null;
        }
    }

    /**
     * Getter para arquivo.
     * @return valor do campo
     */
    public byte[] getArquivo() {
        if (this.arquivo != null) {
            return (byte[]) this.arquivo.clone();
        }
        return null;
    }

    /**
     * Setter para arquivo.
     * @param value valor para arquivo
     */
    public void setArquivo(byte[] value) {
        if (value != null) {
            this.arquivo = (byte[]) value.clone();
        } else {
            this.arquivo = null;
        }
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
     * Getter para evento.
     * @return valor do campo
     */
    public Evento getEvento() {
        return this.evento;
    }

    /**
     * Setter para evento.
     * @param value valor para evento
     */
    public void setEvento(Evento value) {
        this.evento = value;
    }

    /**
     * Getter para tipoRelatorio.
     * @return valor do campo
     */
    public TipoRelatorio getTipoRelatorio() {
        return this.tipoRelatorio;
    }

    /**
     * Setter para tipoRelatorio.
     * @param value valor para tipoRelatorio
     */
    public void setTipoRelatorio(TipoRelatorio value) {
        this.tipoRelatorio = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(dataCriacao)
                .append(arquivo)
                .append(eleicao)
                .append(evento)
                .append(tipoRelatorio)
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

        ArquivoRelatorio rhs = (ArquivoRelatorio) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(dataCriacao, rhs.dataCriacao)
                .append(arquivo, rhs.arquivo)
                .append(eleicao, rhs.eleicao)
                .append(evento, rhs.evento)
                .append(tipoRelatorio, rhs.tipoRelatorio)
                .isEquals();

    }

}
