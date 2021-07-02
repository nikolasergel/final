package by.sergel.entity;

public abstract class AbstractEntity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    public int hashCode(){
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
