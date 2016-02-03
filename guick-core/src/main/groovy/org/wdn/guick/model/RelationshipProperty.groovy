package org.wdn.guick.model

import org.wdn.guick.support.PatternConverterFacade
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Classe de modelo que representa uma propriedade simples de uma classe ou entidade
 * Por propriedade simples entende-se propriedades do "tipo" ou de uma "Classe" da própria linguagem de programacao
 * Ex: String, Long, Date e etc
 * @author wdavilaneto
 */
public class RelationshipProperty extends Clazz {

    Column column;
    Boolean nullable = null;
    List<Validation> validations = new ArrayList<Validation>()

    // Bidiretional Association
    Entity entity

    @Override
    public String getType() {
        return PatternConverterFacade.getBeanType(column)
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                appendSuper(super.hashCode()).
                append(column).
                toHashCode();
    }

    private Boolean _looksLikeDeadline = null;

    public boolean looksDeadline() {
        if (_looksLikeDeadline == null) {
            _looksLikeDeadline = false;
            if (!'Date'.equals(type)) {
                return _looksLikeDeadline;
            }
            for (def word : ["deadline", "prazo", "dateDeadLine", "deadlineDate", "dataPrazo", "prazoData"]) {
                if (name.contains(word)) {
                    _looksLikeDeadline = true;
                    return _looksLikeDeadline;
                }
            }
        }
        return _looksLikeDeadline;
    }

    private Boolean _looksLikeEndDate = null;

    public boolean looksLikeEndDate() {
        if (_looksLikeEndDate == null) {
            _looksLikeEndDate = false;
            if (!'Date'.equals(type)) {
                return _looksLikeEndDate;
            }
            for (
                    def word : ["endDate", "end", "dataFim", "dataSaida", "dataEncerramento", "termino", "fim", "expirationDate", "expireDate", "dataDesligamento", "desligamento", "dataDemissao", "demissao"]) {
                if (name.contains(word)) {
                    _looksLikeEndDate = true;
                    return _looksLikeEndDate;
                }
            }
        }
        return _looksLikeEndDate;
    }

    private Boolean _looksLikeBeginDate = null;

    public boolean looksLikeBeginDate() {
        if (_looksLikeBeginDate == null) {
            _looksLikeBeginDate = false;
            if (!'Date'.equals(type)) {
                return _looksLikeBeginDate;
            }
            for (def word : ["beginDate", "begin", "dataInicio", "dataInicial", "hiringDate", "inicio"]) {
                if (name.contains(word)) {
                    _looksLikeBeginDate = true;
                    return _looksLikeBeginDate;
                }
            }
        }
        return _looksLikeBeginDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        RelationshipProperty rhs = (RelationshipProperty) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(column, rhs.column)
                .isEquals();
    }

}
