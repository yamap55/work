package sample.springboot;

public class Hoge {

    private String name;

    public Hoge(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hoge [name=" + name + "]";
    }
}
