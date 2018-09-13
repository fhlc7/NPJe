package model;

import java.io.InputStream;

public class RelatorioOriginal {

	private long id;
	private String nomeRelatorio;
	private InputStream arquivoInput;
	private byte[] arquivoOutput;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
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

}
