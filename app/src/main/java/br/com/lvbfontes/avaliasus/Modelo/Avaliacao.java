package br.com.lvbfontes.avaliasus.Modelo;

public class Avaliacao {

    private String avaliacaoId;
    private String codigoEstabelecimento;
    private String codigoUsuario;
    private String dataCriacao;
    private double avaliacao;

    public Avaliacao(String avaliacaoId, String codigoEstabelecimento, String codigoUsuario, String dataCriacao, double avaliacao) {
        this.avaliacaoId = avaliacaoId;
        this.codigoEstabelecimento = codigoEstabelecimento;
        this.codigoUsuario = codigoUsuario;
        this.dataCriacao = dataCriacao;
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "avaliacaoId='" + avaliacaoId + '\'' +
                ", codigoEstabelecimento='" + codigoEstabelecimento + '\'' +
                ", codigoUsuario='" + codigoUsuario + '\'' +
                ", dataCriacao='" + dataCriacao + '\'' +
                ", avaliacao=" + avaliacao +
                '}';
    }

    public String getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(String avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public String getCodigoEstabelecimento() {
        return codigoEstabelecimento;
    }

    public void setCodigoEstabelecimento(String codigoEstabelecimento) {
        this.codigoEstabelecimento = codigoEstabelecimento;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
