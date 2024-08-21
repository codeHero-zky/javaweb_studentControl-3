package test.pojo;

import java.util.Objects;

public class user {
    private String userId;
    private String userPwd;

    public user(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }
    public user(){};

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userPwd, user.userPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPwd);
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
