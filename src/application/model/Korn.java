package application.model;

import java.time.LocalDate;

public class Korn {

    private String sort;
    private LocalDate høstdag;
    //Tvunget association --> 1 Mark
    private Mark mark;

    public Korn(LocalDate høstdag, String sort, Mark mark) {
        this.høstdag = høstdag;
        this.sort = sort;
        this.mark = mark;
    }

    public String getSort() {
        return sort;
    }

    public LocalDate getHøstdag() {
        return høstdag;
    }

    public Mark getMark() {
        return mark;
    }
}
