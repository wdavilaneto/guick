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
 * Eleicao
 * A classe Eleicao representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_ELEICAO.
 *${entity.table.comment}
 */
@Entity
@Table(name = "SEVO_ELEICAO")
@SequenceGenerator(name = "SEVO_SQ_SELE_DK", sequenceName = "SEVO_SQ_SELE_DK")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Eleicao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_SELE_DK")
    @Column(name="SELE_DK" )
    private Long id;

    @NotNull
    @Size(max = 145)
    @Column(name = "SELE_NM_ELEICAO", nullable = false, length = 145)
    String nome;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SELE_DT_INICIO", nullable = false, length = 7)
    Date dataInicio;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SELE_DT_FIM", nullable = false, length = 7)
    Date dataFim;

    @NotNull
    @Column(name = "SELE_QTDE_MAX_VOTOS", nullable = false, length = 22)
    Long qtdeMaxVotos;

    @Size(max = 250)
    @Column(name = "SELE_DS_ELEICAO", length = 250)
    String descricao;

    @Size(max = 500)
    @Column(name = "SELE_DS_ORIENTACAO", length = 500)
    String descricaoOrientacao;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SELE_DT_INICIO_APURACAO", length = 7)
    Date dataInicioApuracao;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SELE_DT_FIM_APURACAO", length = 7)
    Date dataFimApuracao;

    @NotNull
    @Size(max = 1)
    @Column(name = "SELE_IN_NATUREZA", nullable = false, length = 1)
    String natureza;

    @Size(max = 300)
    @Column(name = "SELE_CRITERIO_DESEMPATE", length = 300)
    String criterioDesempate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SELE_DT_INICIO_PREV", nullable = false, length = 7)
    Date dataInicioPrev;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "SELE_DT_FIM_PREV", nullable = false, length = 7)
    Date dataFimPrev;

    @NotNull
    @Size(max = 4000)
    @Column(name = "SELE_TX_EMAIL_CONVITE", nullable = false, length = 4000)
    String textoEmailConvite;

    @NotNull
    @Column(name = "SELE_VERSAO", nullable = false, precision = 12, scale = 0, length = 22)
    Long versao;

    @NotNull
    @Size(max = 1)
    @Column(name = "SELE_IN_STATUS_APURACAO", nullable = false, length = 1)
    String statusApuracao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<ArquivoRelatorio> arquivoRelatorioCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<Candidato> candidatoCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<EleicaoParametro> eleicaoParametroCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<Eleitor> eleitorCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<Evento> eventoCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<IntegranteComissao> integranteComissaoCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<TipoFiltroPessoa> tipoFiltroPessoaCollection;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleicao")
    private List<Urna> urnaCollection;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SELE_SSIE_DK", nullable = false)
    private SituacaoEleicao situacaoEleicao;



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
     * Getter para dataInicio.
     * @return valor do campo
     */
    public Date getDataInicio() {
        if (this.dataInicio != null) {
            return (Date) this.dataInicio.clone();
        }
        return null;
    }

    /**
     * Setter para dataInicio.
     * @param value valor para dataInicio
     */
    public void setDataInicio(Date value) {
        if (value != null) {
            this.dataInicio = (Date) value.clone();
        } else {
            this.dataInicio = null;
        }
    }

    /**
     * Getter para dataFim.
     * @return valor do campo
     */
    public Date getDataFim() {
        if (this.dataFim != null) {
            return (Date) this.dataFim.clone();
        }
        return null;
    }

    /**
     * Setter para dataFim.
     * @param value valor para dataFim
     */
    public void setDataFim(Date value) {
        if (value != null) {
            this.dataFim = (Date) value.clone();
        } else {
            this.dataFim = null;
        }
    }

    /**
     * Getter para qtdeMaxVotos.
     * @return valor do campo
     */
    public Long getQtdeMaxVotos() {
        return this.qtdeMaxVotos;
    }

    /**
     * Setter para qtdeMaxVotos.
     * @param value valor para qtdeMaxVotos
     */
    public void setQtdeMaxVotos(Long value) {
        this.qtdeMaxVotos = value;
    }

    /**
     * Getter para descricao.
     * @return valor do campo
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Setter para descricao.
     * @param value valor para descricao
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Getter para descricaoOrientacao.
     * @return valor do campo
     */
    public String getDescricaoOrientacao() {
        return this.descricaoOrientacao;
    }

    /**
     * Setter para descricaoOrientacao.
     * @param value valor para descricaoOrientacao
     */
    public void setDescricaoOrientacao(String value) {
        this.descricaoOrientacao = value;
    }

    /**
     * Getter para dataInicioApuracao.
     * @return valor do campo
     */
    public Date getDataInicioApuracao() {
        if (this.dataInicioApuracao != null) {
            return (Date) this.dataInicioApuracao.clone();
        }
        return null;
    }

    /**
     * Setter para dataInicioApuracao.
     * @param value valor para dataInicioApuracao
     */
    public void setDataInicioApuracao(Date value) {
        if (value != null) {
            this.dataInicioApuracao = (Date) value.clone();
        } else {
            this.dataInicioApuracao = null;
        }
    }

    /**
     * Getter para dataFimApuracao.
     * @return valor do campo
     */
    public Date getDataFimApuracao() {
        if (this.dataFimApuracao != null) {
            return (Date) this.dataFimApuracao.clone();
        }
        return null;
    }

    /**
     * Setter para dataFimApuracao.
     * @param value valor para dataFimApuracao
     */
    public void setDataFimApuracao(Date value) {
        if (value != null) {
            this.dataFimApuracao = (Date) value.clone();
        } else {
            this.dataFimApuracao = null;
        }
    }

    /**
     * Getter para natureza.
     * @return valor do campo
     */
    public String getNatureza() {
        return this.natureza;
    }

    /**
     * Setter para natureza.
     * @param value valor para natureza
     */
    public void setNatureza(String value) {
        this.natureza = value;
    }

    /**
     * Getter para criterioDesempate.
     * @return valor do campo
     */
    public String getCriterioDesempate() {
        return this.criterioDesempate;
    }

    /**
     * Setter para criterioDesempate.
     * @param value valor para criterioDesempate
     */
    public void setCriterioDesempate(String value) {
        this.criterioDesempate = value;
    }

    /**
     * Getter para dataInicioPrev.
     * @return valor do campo
     */
    public Date getDataInicioPrev() {
        if (this.dataInicioPrev != null) {
            return (Date) this.dataInicioPrev.clone();
        }
        return null;
    }

    /**
     * Setter para dataInicioPrev.
     * @param value valor para dataInicioPrev
     */
    public void setDataInicioPrev(Date value) {
        if (value != null) {
            this.dataInicioPrev = (Date) value.clone();
        } else {
            this.dataInicioPrev = null;
        }
    }

    /**
     * Getter para dataFimPrev.
     * @return valor do campo
     */
    public Date getDataFimPrev() {
        if (this.dataFimPrev != null) {
            return (Date) this.dataFimPrev.clone();
        }
        return null;
    }

    /**
     * Setter para dataFimPrev.
     * @param value valor para dataFimPrev
     */
    public void setDataFimPrev(Date value) {
        if (value != null) {
            this.dataFimPrev = (Date) value.clone();
        } else {
            this.dataFimPrev = null;
        }
    }

    /**
     * Getter para textoEmailConvite.
     * @return valor do campo
     */
    public String getTextoEmailConvite() {
        return this.textoEmailConvite;
    }

    /**
     * Setter para textoEmailConvite.
     * @param value valor para textoEmailConvite
     */
    public void setTextoEmailConvite(String value) {
        this.textoEmailConvite = value;
    }

    /**
     * Getter para versao.
     * @return valor do campo
     */
    public Long getVersao() {
        return this.versao;
    }

    /**
     * Setter para versao.
     * @param value valor para versao
     */
    public void setVersao(Long value) {
        this.versao = value;
    }

    /**
     * Getter para statusApuracao.
     * @return valor do campo
     */
    public String getStatusApuracao() {
        return this.statusApuracao;
    }

    /**
     * Setter para statusApuracao.
     * @param value valor para statusApuracao
     */
    public void setStatusApuracao(String value) {
        this.statusApuracao = value;
    }

    /**
     * Getter para situacaoEleicao.
     * @return valor do campo
     */
    public SituacaoEleicao getSituacaoEleicao() {
        return this.situacaoEleicao;
    }

    /**
     * Setter para situacaoEleicao.
     * @param value valor para situacaoEleicao
     */
    public void setSituacaoEleicao(SituacaoEleicao value) {
        this.situacaoEleicao = value;
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
     * Getter para candidatoCollection.
     * @return valor do campo
     */
    public List<Candidato> getCandidatoCollection() {
        return this.candidatoCollection;
    }

    /**
     * Setter para candidatoCollection.
     * @param value valor para candidatoCollection
     */
    public void setCandidatoCollection(List<Candidato> value) {
        this.candidatoCollection = value;
    }

    /**
     * Getter para eleicaoParametroCollection.
     * @return valor do campo
     */
    public List<EleicaoParametro> getEleicaoParametroCollection() {
        return this.eleicaoParametroCollection;
    }

    /**
     * Setter para eleicaoParametroCollection.
     * @param value valor para eleicaoParametroCollection
     */
    public void setEleicaoParametroCollection(List<EleicaoParametro> value) {
        this.eleicaoParametroCollection = value;
    }

    /**
     * Getter para eleitorCollection.
     * @return valor do campo
     */
    public List<Eleitor> getEleitorCollection() {
        return this.eleitorCollection;
    }

    /**
     * Setter para eleitorCollection.
     * @param value valor para eleitorCollection
     */
    public void setEleitorCollection(List<Eleitor> value) {
        this.eleitorCollection = value;
    }

    /**
     * Getter para eventoCollection.
     * @return valor do campo
     */
    public List<Evento> getEventoCollection() {
        return this.eventoCollection;
    }

    /**
     * Setter para eventoCollection.
     * @param value valor para eventoCollection
     */
    public void setEventoCollection(List<Evento> value) {
        this.eventoCollection = value;
    }

    /**
     * Getter para integranteComissaoCollection.
     * @return valor do campo
     */
    public List<IntegranteComissao> getIntegranteComissaoCollection() {
        return this.integranteComissaoCollection;
    }

    /**
     * Setter para integranteComissaoCollection.
     * @param value valor para integranteComissaoCollection
     */
    public void setIntegranteComissaoCollection(List<IntegranteComissao> value) {
        this.integranteComissaoCollection = value;
    }

    /**
     * Getter para tipoFiltroPessoaCollection.
     * @return valor do campo
     */
    public List<TipoFiltroPessoa> getTipoFiltroPessoaCollection() {
        return this.tipoFiltroPessoaCollection;
    }

    /**
     * Setter para tipoFiltroPessoaCollection.
     * @param value valor para tipoFiltroPessoaCollection
     */
    public void setTipoFiltroPessoaCollection(List<TipoFiltroPessoa> value) {
        this.tipoFiltroPessoaCollection = value;
    }

    /**
     * Getter para urnaCollection.
     * @return valor do campo
     */
    public List<Urna> getUrnaCollection() {
        return this.urnaCollection;
    }

    /**
     * Setter para urnaCollection.
     * @param value valor para urnaCollection
     */
    public void setUrnaCollection(List<Urna> value) {
        this.urnaCollection = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(nome)
                .append(dataInicio)
                .append(dataFim)
                .append(qtdeMaxVotos)
                .append(descricao)
                .append(descricaoOrientacao)
                .append(dataInicioApuracao)
                .append(dataFimApuracao)
                .append(natureza)
                .append(criterioDesempate)
                .append(dataInicioPrev)
                .append(dataFimPrev)
                .append(textoEmailConvite)
                .append(versao)
                .append(statusApuracao)
                .append(situacaoEleicao)
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

        Eleicao rhs = (Eleicao) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(nome, rhs.nome)
                .append(dataInicio, rhs.dataInicio)
                .append(dataFim, rhs.dataFim)
                .append(qtdeMaxVotos, rhs.qtdeMaxVotos)
                .append(descricao, rhs.descricao)
                .append(descricaoOrientacao, rhs.descricaoOrientacao)
                .append(dataInicioApuracao, rhs.dataInicioApuracao)
                .append(dataFimApuracao, rhs.dataFimApuracao)
                .append(natureza, rhs.natureza)
                .append(criterioDesempate, rhs.criterioDesempate)
                .append(dataInicioPrev, rhs.dataInicioPrev)
                .append(dataFimPrev, rhs.dataFimPrev)
                .append(textoEmailConvite, rhs.textoEmailConvite)
                .append(versao, rhs.versao)
                .append(statusApuracao, rhs.statusApuracao)
                .append(situacaoEleicao, rhs.situacaoEleicao)
                .isEquals();

    }

}
