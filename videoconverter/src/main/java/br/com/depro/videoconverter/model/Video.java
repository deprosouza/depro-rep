package br.com.depro.videoconverter.model;

import java.math.BigDecimal;

public class Video {

	private String url;
	private String nome;
	private Boolean download = Boolean.FALSE;
	private Boolean hasMp3 = Boolean.FALSE;
	private Boolean hasDownload = Boolean.FALSE;
	private BigDecimal porcentagem = BigDecimal.ZERO;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getDownload() {
		return download;
	}
	public void setDownload(Boolean download) {
		this.download = download;
	}
	public Boolean getHasMp3() {
		return hasMp3;
	}
	public void setHasMp3(Boolean hasMp3) {
		this.hasMp3 = hasMp3;
	}
	public Boolean getHasDownload() {
		return hasDownload;
	}
	public void setHasDownload(Boolean hasDownload) {
		this.hasDownload = hasDownload;
	}
	public BigDecimal getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(BigDecimal porcentagem) {
		if (porcentagem.compareTo(new BigDecimal(100)) == 0) {
			setHasDownload(true);
		}
		this.porcentagem = porcentagem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
