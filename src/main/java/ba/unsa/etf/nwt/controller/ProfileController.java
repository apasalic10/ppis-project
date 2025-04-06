package ba.unsa.etf.nwt.controller;

import ba.unsa.etf.nwt.dto.UserDTO;
import ba.unsa.etf.nwt.entity.Profile;
import ba.unsa.etf.nwt.entity.User;
import ba.unsa.etf.nwt.exception.ResourceNotFoundException;
import ba.unsa.etf.nwt.service.ProfileService;
import ba.unsa.etf.nwt.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    public ProfileController(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @PatchMapping(path = "/{userId}", consumes = "application/json-patch+json")
    public ResponseEntity<Profile> updateProfile(@PathVariable long userId, @RequestBody JsonPatch patch) {
        try {
            UserDTO user = userService.getById(userId);
            Profile profile = profileService.getUserProfile(user);
            Profile updatedProfile = profileService.updateProfile(profile, patch);
            return ResponseEntity.ok(updatedProfile);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
