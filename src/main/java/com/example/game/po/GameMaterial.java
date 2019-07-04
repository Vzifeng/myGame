package com.example.game.po;

public class GameMaterial {
    private Integer id;

    private String gameMaterial;

    private Integer materialNum;

    private Integer userId;

    private Integer gameId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameMaterial() {
        return gameMaterial;
    }

    public void setGameMaterial(String gameMaterial) {
        this.gameMaterial = gameMaterial == null ? null : gameMaterial.trim();
    }

    public Integer getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Integer materialNum) {
        this.materialNum = materialNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}