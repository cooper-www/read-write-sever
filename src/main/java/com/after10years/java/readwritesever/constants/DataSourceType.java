package com.after10years.java.readwritesever.constants;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum DataSourceType {
    read("read", "从库"),
    write("write", "主库");
    private String type;

    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
    @Getter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
