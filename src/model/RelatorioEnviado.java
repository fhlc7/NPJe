package model;

import java.io.InputStream;
import java.util.Arrays;

public class RelatorioEnviado {

	private long id;
	private String nomeArquivo;
	private InputStream arquivoInput;
	private byte[] arquivoOutput;
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public InputStream getArquivoInput() {
		return arquivoInput;
	}

	public void setArquivoInput(InputStream arquivoInput) {
		this.arquivoInput = arquivoInput;
	}

	public byte[] getArquivoOutput() {
		return arquivoOutput;
	}

	public void setArquivoOutput(byte[] arquivoOutput) {
		this.arquivoOutput = arquivoOutput;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
