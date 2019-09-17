package br.com.lvbfontes.avaliasus.Modelo;

import java.text.DecimalFormat;

public class unidadeSus {

    //private String unidadeId;
    private String nomeUnidade;
    private double latitude;
    private double longitude;
    private String endereco;
    private int numero;
    private String bairro;
    private String cidade;
    private String userId;
    private String nomeUser;

    public unidadeSus(String nomeUnidade, double latitude, double longitude, String endereco, int numero, String bairro, String cidade, String userId, String nomeUser) {
        this.nomeUnidade = nomeUnidade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.userId = userId;
        this.nomeUser = nomeUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    @Override
    public String toString() {
        return "unidadeSus{" +
                "nomeUnidade='" + nomeUnidade + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", endereco='" + endereco + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", userId='" + userId + '\'' +
                ", nomeUser='" + nomeUser + '\'' +
                '}';
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
