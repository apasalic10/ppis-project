package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.dto.UserDTO;
import ba.unsa.etf.nwt.entity.Profile;
import ba.unsa.etf.nwt.entity.User;
import ba.unsa.etf.nwt.exception.ResourceNotFoundException;
import ba.unsa.etf.nwt.repository.ProfileRepository;
import ba.unsa.etf.nwt.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, ObjectMapper objectMapper, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    public Profile getUserProfile(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return profileRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Transactional
    public Profile updateProfile(Profile profile, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Profile patchedProfile = applyPatchToProfile(patch, profile);
        return profileRepository.save(patchedProfile);
    }

    private Profile applyPatchToProfile(JsonPatch patch, Profile targetProfile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.convertValue(targetProfile, JsonNode.class);

            // Log the current state of the Profile
            System.out.println("Before patch: " + jsonNode.toPrettyString());

            // Apply the patch
            JsonNode patched = patch.apply(jsonNode);

            // Log the patched state
            System.out.println("After patch: " + patched.toPrettyString());

            return mapper.treeToValue(patched, Profile.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException("Error applying patch: " + e.getMessage(), e);
        }
    }


}
