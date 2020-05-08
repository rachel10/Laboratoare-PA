package com.example.demo;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/add")
    public @ResponseBody Player addNewPlayer (@RequestBody Player newPlayer){
        Player player=new Player();
        player.setUsername(newPlayer.getUsername());
        playerRepository.save(player);
        return player;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @PutMapping("/update")
    public @ResponseBody Player updatePlayer (@RequestBody Player newPlayer){
        Optional<Player> player= playerRepository.findById(newPlayer.getId());
        Player player1=player.get();
        player1.setUsername(newPlayer.getUsername());
        playerRepository.save(player1);
        return player1;
    }


    @DeleteMapping("/delete")
    public @ResponseBody String deletePlayer(@RequestBody Player player){
        playerRepository.deleteById(player.getId());
        return "Deleted";
    }

}
