package br.com.depro.mugetsu.carga.anime.model;

public enum GeneroANN {

	action 				(GeneroEnum.ACAO),
	fantasy 			(GeneroEnum.FANTASIA), 	
	romance 			(GeneroEnum.ROMANCE), 
	science_fiction		(GeneroEnum.FICC), 	
	comedy				(GeneroEnum.COMEDIA), 	
	magic 		        (GeneroEnum.MAGIA), 		
	slice_of_life      	(GeneroEnum.SLL), 	
	drama	  	        (GeneroEnum.DRAMA), 
	tournament	        (GeneroEnum.TORNEIO), 	
	erotica		        (GeneroEnum.HEN), 		
	adventure	        (GeneroEnum.AVENTURA), 		
	supernatural        (GeneroEnum.SUPERN), 		
	psychological 		(GeneroEnum.PSY), 	
	horror 		        (GeneroEnum.HOR), 	
	mystery 	        (GeneroEnum.MISTERIO),
	military 	        (GeneroEnum.MILITAR),
	police	 	        (GeneroEnum.POLICE),
	sports				(GeneroEnum.ESPORTE),
	mecha	 	        (GeneroEnum.MECHA),
	yaoi				(GeneroEnum.YAOI),
	yuri				(GeneroEnum.YURI),
	shounen_ai			(GeneroEnum.SHOUNENAI),
	shoujo_ai			(GeneroEnum.SHOUJOAI),
	music				(GeneroEnum.MUSICA),
	historical			(GeneroEnum.HIS);
	
	private GeneroEnum genero;
	
	GeneroANN(GeneroEnum genero) {
		this.setGenero(genero);
	}

	/**
	 * @return the genero
	 */
	public GeneroEnum getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
	}
}
