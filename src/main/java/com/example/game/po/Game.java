package com.example.game.po;

public class Game {
    private Integer id;

    private String gameName;

    private String gameCompany;

    private String gamePublishTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public String getGameCompany() {
        return gameCompany;
    }

    public void setGameCompany(String gameCompany) {
        this.gameCompany = gameCompany == null ? null : gameCompany.trim();
    }

    public String getGamePublishTime() {
        return gamePublishTime;
    }

    public void setGamePublishTime(String gamePublishTime) {
        this.gamePublishTime = gamePublishTime == null ? null : gamePublishTime.trim();
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", gameName='" + gameName + '\'' + ", gameCompany='" + gameCompany + '\'' + ", gamePublishTime='" + gamePublishTime + '\'' + '}';
    }
}