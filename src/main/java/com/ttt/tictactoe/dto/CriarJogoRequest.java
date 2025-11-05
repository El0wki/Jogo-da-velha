package com.ttt.tictactoe.dto;

import com.ttt.tictactoe.model.ModoDeJogo;

public class CriarJogoRequest {

    private ModoDeJogo modo;
    private boolean humanoInicia;

    public ModoDeJogo getModo() {
        return modo;
    }

    public void setModo(ModoDeJogo modo) {
        this.modo = modo;
    }

    public boolean isHumanoInicia() {
        return humanoInicia;
    }

    public void setHumanoInicia(boolean humanoInicia) {
        this.humanoInicia = humanoInicia;
    }
}
