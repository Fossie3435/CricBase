package uk.org.cricbase.Models;

/**
 *
 */
public class Player {
    private String id;
    private String name;
    private String nickname;
    private String uniqueName;
    private String cricInfoId;
    
    public Player() {
        
    }
    public Player(String id) {
        this.id = id;
    }
    
    public Player(String id, String name, String uniqueName) {
        this.id = id;
        this.name = name;
        this.uniqueName = uniqueName;
    }
    
    public Player(String id, String name, String uniqueName, String cricInfoId) {
        this.id = id;
        this.name = name;
        this.uniqueName = uniqueName;
        this.cricInfoId = cricInfoId;
    }
    
    public Player(String id, String name, String nickname, String uniqueName, String cricInfoId) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.uniqueName = uniqueName;
        this.cricInfoId = cricInfoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getCricInfoId() {
        return cricInfoId;
    }

    public void setCricInfoId(String cricInfoId) {
        this.cricInfoId = cricInfoId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
