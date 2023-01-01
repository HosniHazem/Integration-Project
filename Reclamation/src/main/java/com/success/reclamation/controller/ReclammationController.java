package com.success.reclamation.controller;

import com.success.reclamation.Repository.RecRepo;
import com.success.reclamation.model.Reclammation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apis")
public class ReclammationController {
    @Autowired
    RecRepo recRepo;
    @RequestMapping("/rec/getAll")
    public List<Reclammation> getAllReclamatiion(){
        return recRepo.findAll();
    }
    @GetMapping("/rec/{recId}")
    public Optional<Reclammation> findProduct(@PathVariable Long recId) {
        return recRepo.findById(recId);
    }
    @PostMapping("/rec/create")
    public ResponseEntity<String> createOrder(@RequestBody Reclammation rec) {
        recRepo.save(rec);
        return ResponseEntity.ok("Reclammation created successfully");
    }
    @DeleteMapping("/rec/delete/{recId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("recId") Long recId ){





        recRepo.deleteById(recId);

        return ResponseEntity.ok(" Reclamation Deleted successfully");

    }
}
