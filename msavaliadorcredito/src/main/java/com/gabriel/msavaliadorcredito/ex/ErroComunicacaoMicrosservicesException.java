package com.gabriel.msavaliadorcredito.ex;

import lombok.Getter;

public class ErroComunicacaoMicrosservicesException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer status;
	
	public ErroComunicacaoMicrosservicesException( String msg, Integer status) {
		super(msg);
		this.status = status;
		
	}
}
