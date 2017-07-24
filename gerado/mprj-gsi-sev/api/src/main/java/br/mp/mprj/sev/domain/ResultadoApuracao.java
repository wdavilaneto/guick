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
 * ResultadoApuracao
 * A classe ResultadoApuracao representa o mapeamento Objeto-Relacional
 * da entidade de negocio armazenada na tabela SEVO_RESULTADO_APURACAO.
 *
Tabela que receberá a quantidade de votos por candidato depois da realização da apuração. 
Os votos somados e armazenados homomorficamente no campo urna_msg_composta da tabela sevo_urna, 
serão descriptografados e armazenados na tabela sevo_resultado_apuracao apara exibicao no painel de apuração.
 */
@Entity
@Table(name = "SEVO_RESULTADO_APURACAO")
@SequenceGenerator(name = "SEVO_SQ_REAP_DK", sequenceName = "SEVO_SQ_REAP_DK")
//@Immutable ?
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoApuracao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEVO_SQ_REAP_DK")
    @Column(name="REAP_DK" )
    private Long id;

    @NotNull
    @Column(name = "REAP_QT_VOTOS", nullable = false, length = 22)
    Long qtVotos;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "REAP_SCAD_DK", nullable = false)
    private Candidato candidato;



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
     * Getter para qtVotos.
     * @return valor do campo
     */
    public Long getQtVotos() {
        return this.qtVotos;
    }

    /**
     * Setter para qtVotos.
     * @param value valor para qtVotos
     */
    public void setQtVotos(Long value) {
        this.qtVotos = value;
    }

    /**
     * Getter para candidato.
     * @return valor do campo
     */
    public Candidato getCandidato() {
        return this.candidato;
    }

    /**
     * Setter para candidato.
     * @param value valor para candidato
     */
    public void setCandidato(Candidato value) {
        this.candidato = value;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(qtVotos)
                .append(candidato)
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

        ResultadoApuracao rhs = (ResultadoApuracao) obj;

        // For performance reasons we first eliminate @id (primary key) difference
        if (id != null && (!id.equals(rhs.id)) ) {
            return false;
        }
        // so lets check all properties
        return new EqualsBuilder()
                .append(qtVotos, rhs.qtVotos)
                .append(candidato, rhs.candidato)
                .isEquals();

    }

}
