package com.joshcummings.codeplay.terracotta.dto;

public class UserDto {
    private final String username;
    private final String password;
    private final Integer age;

    public UserDto(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDto{");
        sb.append("age=").append(age);
        sb.append(", password='").append("**********").append('\'');
        sb.append(", username='").append("**********").append('\'');
        sb.append('}');
        return sb.toString();
    }
}
