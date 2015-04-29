package mini.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ash
 */
public class User {

    private int id;
    private String name;
    private long money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("model_p_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", money=" + money + "]";
    }
    
}
