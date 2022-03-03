package com.danaherdigital.che_hx.schedular.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersistResDTO {

	private boolean isPersisted;
	private boolean isDeadLock=false;

}
