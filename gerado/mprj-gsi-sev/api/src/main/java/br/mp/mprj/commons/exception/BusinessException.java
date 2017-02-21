/**
 *  Guick Generate class: https://github.com/wdavilaneto/guick
 *  Author: service-wdavilaneto@redhat.com
 *  This source is free under The Apache Software License, Version 2.0
 *  license url http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package br.mp.mprj.commons.exception;


public class BusinessException extends RuntimeException {
    private String message;
    private String[] messageParams;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, String... messageParams) {
        this.message = message;
        this.messageParams = messageParams;
    }

    public String getMessage() {
        return message;
    }

    public String[] getMessageParams() {
        return messageParams;
    }
}