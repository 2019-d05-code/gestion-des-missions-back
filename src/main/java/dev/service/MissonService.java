package dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.repository.MissionRepo;

@Service
public class MissonService {

	@Autowired
	private MissionRepo missionRepo;
}
