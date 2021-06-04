package hiber.model;


import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @Column(name="id")
    private long id;

    @Column(name="model")
    private String model;

    @Column(name="series")
    private int series;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Car(long id, String model, int series) {
        this.id = id;
        this.model = model;
        this.series = series;
    }

    public Car() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
