package com.example.cinema.controllers;

import com.example.cinema.dto.SessionDto;
import com.example.cinema.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@Tag(name = "Sessions API", description = "API для управління сеансами кінотеатру")
public class SessionRestController {

    private final SessionService sessionService;

    @Autowired
    public SessionRestController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    @Operation(summary = "Отримати всі сеанси")
    public ResponseEntity<List<SessionDto>> getAllSessions() {
        return ResponseEntity.ok(sessionService.findAllSessions());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Отримати сеанс за ID")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable long id) {
        return ResponseEntity.ok(sessionService.findSessionById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Видалити сеанс за ID")
    public ResponseEntity<Void> deleteSession(@PathVariable long id) {
        sessionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}