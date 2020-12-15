package com.dam.H2;


import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Depart {
	@NonNull
	@EqualsAndHashCode.Include
	private String dept_no;
	private String dname;
	private String dloc;
	@Singular
	private List<Emple> empleados;
	
}

