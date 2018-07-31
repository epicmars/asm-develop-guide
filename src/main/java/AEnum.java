public enum  AEnum {
    INSTANCE("instance"),
    TWO("two"),
    ;

    private String name;

    private AEnum(String name) {
        this.name = name;
    }
}
