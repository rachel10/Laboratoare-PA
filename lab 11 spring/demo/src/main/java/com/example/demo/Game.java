package com.example.demo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String info;

    private Date date;

    private String result;

    @JoinColumn(name="id_player1",referencedColumnName = "id")
    @OneToOne
    Player idPlayer1;


    @JoinColumn(name="id_player2",referencedColumnName = "id")
    @OneToOne
    Player idPlayer2;
}
